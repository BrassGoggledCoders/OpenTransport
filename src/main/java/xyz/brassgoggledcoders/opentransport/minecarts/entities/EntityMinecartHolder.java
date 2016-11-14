package xyz.brassgoggledcoders.opentransport.minecarts.entities;

import com.google.common.base.Optional;
import com.teamacronymcoders.base.client.gui.IHasGui;
import com.teamacronymcoders.base.entity.EntityMinecartBase;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityMinecartEmpty;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.minecarts.items.ItemMinecartHolder;
import xyz.brassgoggledcoders.opentransport.registries.BlockWrapperRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityMinecartHolder extends EntityMinecartBase
        implements IHolderEntity<EntityMinecartHolder>, IHasGui {
    private static final DataParameter<String> BLOCK_CONTAINER_NAME =
            EntityDataManager.createKey(EntityMinecartHolder.class, DataSerializers.STRING);
    private static final DataParameter<Optional<ItemStack>> ITEM_CART =
            EntityDataManager.createKey(EntityMinecartHolder.class, DataSerializers.OPTIONAL_ITEM_STACK);

    private IBlockWrapper blockWrapper;
    private boolean isPowered = false;
    private int poweredReset = 0;

    public EntityMinecartHolder(World world) {
        super(world);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(BLOCK_CONTAINER_NAME, "");
        this.dataManager.register(ITEM_CART, Optional.absent());
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if(poweredReset > 0) {
            poweredReset--;
            if(poweredReset == 0) {
                isPowered = false;
            }
        }
    }

    @Nonnull
    @Override
    public ItemMinecart getItem() {
        Optional<ItemStack> itemStackCart = this.dataManager.get(ITEM_CART);
        if (itemStackCart.isPresent()) {
            return (ItemMinecart) itemStackCart.get().getItem();
        }
        return (ItemMinecart) Items.MINECART;
    }

    public void setItemCart(@Nonnull ItemStack itemCartStack) {
        if (itemCartStack.getItem() instanceof ItemMinecartHolder) {
            this.dataManager.set(ITEM_CART, Optional.of(itemCartStack));
        }
    }

    @Override
    public EntityMinecartHolder getEntity() {
        return this;
    }

    @Override
    public IBlockWrapper getBlockWrapper() {
        if (this.blockWrapper == null) {
            String containerName = this.dataManager.get(BLOCK_CONTAINER_NAME);
            this.blockWrapper = BlockWrapperRegistry.getBlockWrapper(containerName);
            if (this.blockWrapper != null) {
                this.blockWrapper.setHolder(this);
            }
        }
        return this.blockWrapper;
    }

    @Override
    public void setBlockWrapper(IBlockWrapper blockWrapper) {
        this.dataManager.set(BLOCK_CONTAINER_NAME, blockWrapper.getUnlocalizedName());
        this.blockWrapper = blockWrapper;
        this.blockWrapper.setHolder(this);
    }

    @Override
    public Entity getEmptyEntity() {
        EntityMinecartEmpty entityMinecartEmpty = new EntityMinecartEmpty(this.worldObj);
        entityMinecartEmpty.setRollingAmplitude(this.getRollingAmplitude());
        entityMinecartEmpty.setRollingDirection(this.getRollingDirection());
        entityMinecartEmpty.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
        return entityMinecartEmpty;
    }

    @Override
    public boolean getRedstonePower() {
        return this.isPowered;
    }

    public void setRedstonePower(boolean isPowered) {
        if(this.isPowered != isPowered) {
            this.isPowered = isPowered;
            this.getBlockWrapper().getWorldWrapper().notifyBlocks();
        }
    }

    @Override
    public void onActivatorRailPass(int x, int y, int z, boolean isPowered) {
        if(isPowered) {
            this.setRedstonePower(true);
        }
    }

    @Override
    protected void moveAlongTrack(BlockPos pos, IBlockState state) {
        super.moveAlongTrack(pos, state);
        if (state.getBlock() != Blocks.ACTIVATOR_RAIL) {
            this.setRedstonePower(false);
        }
    }

    @Override
    public boolean processInitialInteract(@Nonnull EntityPlayer entityPlayer, @Nullable ItemStack itemStack,
                                          EnumHand hand) {
        return this.getBlockWrapper() != null && this.getBlockWrapper().onInteract(entityPlayer, hand, itemStack);
    }

    @Override
    @Nonnull
    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        super.writeToNBT(nbtTagCompound);
        nbtTagCompound.setString("CONTAINER_NAME", this.dataManager.get(BLOCK_CONTAINER_NAME));
        if (blockWrapper != null) {
            NBTTagCompound containerTag = new NBTTagCompound();
            containerTag = blockWrapper.writeToNBT(containerTag);
            nbtTagCompound.setTag("CONTAINER", containerTag);
        }
        Optional<ItemStack> itemStackCart = this.dataManager.get(ITEM_CART);
        if (itemStackCart.isPresent()) {
            NBTTagCompound itemBoat = new NBTTagCompound();
            nbtTagCompound.setTag("ITEM_BOAT", itemStackCart.get().writeToNBT(itemBoat));
        }

        return nbtTagCompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        this.dataManager.set(BLOCK_CONTAINER_NAME, nbtTagCompound.getString("CONTAINER_NAME"));
        this.setBlockWrapper(BlockWrapperRegistry.getBlockWrapper(nbtTagCompound.getString("CONTAINER_NAME")));
        blockWrapper.setHolder(this);
        blockWrapper.readFromNBT(nbtTagCompound.getCompoundTag("CONTAINER"));
        this.setItemCart(ItemStack.loadItemStackFromNBT(nbtTagCompound.getCompoundTag("ITEM_BOAT")));
    }

    protected BlockPos getRailPosition()
    {
        int x = MathHelper.floor_double(this.posX);
        int y = MathHelper.floor_double(this.posY);
        int z = MathHelper.floor_double(this.posZ);

        if (BlockRailBase.isRailBlock(this.worldObj, new BlockPos(x, y - 1, z))) y--;
        return new BlockPos(x, y, z);
    }

    @Override
    public Gui getClientGuiElement(int ID, EntityPlayer player, World world, BlockPos blockPos) {
        return this.getBlockWrapper().getInterface().getGUI(player, this, this.getBlockWrapper());
    }

    @Override
    public Container getServerGuiElement(int ID, EntityPlayer player, World world, BlockPos blockPos) {
        return this.getBlockWrapper().getInterface().getContainer(player, this, this.getBlockWrapper());
    }
}
