package xyz.brassgoggledcoders.opentransport.minecarts.entities;

import com.google.common.base.Optional;
import com.teamacronymcoders.base.entity.EntityMinecartBase;
import com.teamacronymcoders.base.guisystem.IHasGui;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import xyz.brassgoggledcoders.opentransport.api.OpenTransportAPI;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.minecarts.items.ItemMinecartHolder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityMinecartHolder extends EntityMinecartBase
        implements IHolderEntity<EntityMinecartHolder>, IHasGui {
    private static final DataParameter<String> BLOCK_WRAPPER_NAME =
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
        this.dataManager.register(BLOCK_WRAPPER_NAME, "");
        this.dataManager.register(ITEM_CART, Optional.absent());
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.getBlockWrapper().onUpdate();
        if(poweredReset > 0) {
            poweredReset--;
            if(poweredReset == 0) {
                isPowered = false;
            }
        }
    }

    @Override
    public void killMinecart(DamageSource damageSource) {
        super.killMinecart(damageSource);
        this.getBlockWrapper().onBreak();
    }

    @Override
    @Nonnull
    public ItemStack getCartItem() {
        ItemStack cartItemStack = ItemMinecartHolder.getStackForBlockWrapper(this.getBlockWrapper());

        if(this.hasCustomName()) {
            cartItemStack.setStackDisplayName(this.getName());
        }

        return cartItemStack;
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
            String containerName = this.dataManager.get(BLOCK_WRAPPER_NAME);
            this.blockWrapper = OpenTransportAPI.getBlockWrapperRegistry().getBlockWrapper(containerName);
            if (this.blockWrapper != null) {
                this.blockWrapper.setHolder(this);
            }
        }
        return this.blockWrapper;
    }

    @Override
    public void setBlockWrapper(IBlockWrapper blockWrapper) {
        this.dataManager.set(BLOCK_WRAPPER_NAME, blockWrapper.getUnlocalizedName());
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
        nbtTagCompound.setString("WRAPPER_NAME", this.dataManager.get(BLOCK_WRAPPER_NAME));
        if (blockWrapper != null) {
            NBTTagCompound containerTag = new NBTTagCompound();
            containerTag = blockWrapper.writeToNBT(containerTag);
            nbtTagCompound.setTag("CONTAINER", containerTag);
        }
        Optional<ItemStack> itemStackCart = this.dataManager.get(ITEM_CART);
        if (itemStackCart.isPresent()) {
            NBTTagCompound itemCart = new NBTTagCompound();
            nbtTagCompound.setTag("ITEM_MINECART", itemStackCart.get().writeToNBT(itemCart));
        }

        return nbtTagCompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        String wrapperName = nbtTagCompound.getString("WRAPPER_NAME");
        this.dataManager.set(BLOCK_WRAPPER_NAME, wrapperName);
        this.setBlockWrapper(OpenTransportAPI.getBlockWrapperRegistry().getBlockWrapper(wrapperName));
        blockWrapper.setHolder(this);
        blockWrapper.readFromNBT(nbtTagCompound.getCompoundTag("CONTAINER"));
        this.setItemCart(ItemStack.loadItemStackFromNBT(nbtTagCompound.getCompoundTag("ITEM_MINECART")));
    }

    @Override
    public Gui getGui(EntityPlayer entityPlayer, World world, NBTTagCompound context) {
        return this.getBlockWrapper().getInterface().getGUI(entityPlayer, this, this.getBlockWrapper());
    }

    @Override
    public Container getContainer(EntityPlayer entityPlayer, World world, NBTTagCompound context) {
        return this.getBlockWrapper().getInterface().getContainer(entityPlayer, this, this.getBlockWrapper());
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return this.getBlockWrapper().hasCapability(capability, facing) || super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Nonnull
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return this.getBlockWrapper().hasCapability(capability, facing) ?
                this.getBlockWrapper().getCapability(capability, facing) : super.getCapability(capability, facing);
    }
}
