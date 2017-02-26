package xyz.brassgoggledcoders.opentransport.boats.entities;

import com.google.common.base.Optional;
import com.teamacronymcoders.base.guisystem.IHasGui;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import xyz.brassgoggledcoders.opentransport.api.OpenTransportAPI;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.wrappers.world.WorldHarnessEntity;
import xyz.brassgoggledcoders.opentransport.boats.items.ItemBoatHolder;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityBoatHolder extends EntityBoatBase implements IHolderEntity<EntityBoatHolder>, IHasGui {
    private static final DataParameter<String> BLOCK_WRAPPER_NAME =
            EntityDataManager.createKey(EntityBoatHolder.class, DataSerializers.STRING);
    private static final DataParameter<Optional<ItemStack>> ITEM_BOAT =
            EntityDataManager.createKey(EntityBoatHolder.class, DataSerializers.OPTIONAL_ITEM_STACK);
    IBlockWrapper blockWrapper;

    public EntityBoatHolder(World world) {
        super(world);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(BLOCK_WRAPPER_NAME, "");
        this.dataManager.register(ITEM_BOAT, Optional.<ItemStack>absent());
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        this.getBlockWrapper().onUpdate();
    }

    @Override
    public ItemStack getBoatItemStack() {
        return ItemBoatHolder.getItemStackForBlockWrapper(this.getBoatType(), this.getBlockWrapper());
    }

    @Override
    @Nonnull
    public Item getItemBoat() {
        Optional<ItemStack> itemStackBoat = this.dataManager.get(ITEM_BOAT);
        if (itemStackBoat.isPresent()) {
            return itemStackBoat.get().getItem();
        }
        return Items.BOAT;
    }

    public void setItemBoat(@Nonnull ItemStack itemBoatStack) {
        if (itemBoatStack.getItem() instanceof ItemBoatHolder) {
            this.dataManager.set(ITEM_BOAT, Optional.of(itemBoatStack));
        }
    }

    @Override
    public EntityBoatHolder getEntity() {
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
        this.blockWrapper.setWorldHarness(new WorldHarnessEntity(this));
    }

    @Override
    public Entity getEmptyEntity() {
        EntityBoat entityBoat = new EntityBoat(this.getEntityWorld());
        entityBoat.setBoatType(this.getBoatType());
        entityBoat.setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
        return entityBoat;
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

        Optional<ItemStack> itemStackBoat = this.dataManager.get(ITEM_BOAT);
        if (itemStackBoat.isPresent()) {
            NBTTagCompound itemBoat = new NBTTagCompound();
            nbtTagCompound.setTag("ITEM_BOAT", itemStackBoat.get().writeToNBT(itemBoat));
        }
        nbtTagCompound.setString("WRAPPER_NAME", this.dataManager.get(BLOCK_WRAPPER_NAME));
        if (blockWrapper != null) {
            NBTTagCompound containerTag = new NBTTagCompound();
            containerTag = blockWrapper.writeToNBT(containerTag);
            nbtTagCompound.setTag("CONTAINER", containerTag);
        }
        return nbtTagCompound;
    }

    @Override
    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        super.readFromNBT(nbtTagCompound);
        String wrapperName = nbtTagCompound.getString("WRAPPER_NAME");
        this.dataManager.set(BLOCK_WRAPPER_NAME, wrapperName);
        this.setBlockWrapper(OpenTransportAPI.getBlockWrapperRegistry().getBlockWrapper(wrapperName));
        this.setItemBoat(ItemStack.loadItemStackFromNBT(nbtTagCompound.getCompoundTag("ITEM_BOAT")));
        blockWrapper.setHolder(this);
        blockWrapper.readFromNBT(nbtTagCompound.getCompoundTag("CONTAINER"));
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

    @Override
    public Gui getGui(EntityPlayer entityPlayer, World world, BlockPos blockPos) {
        return this.getBlockWrapper().getInterface().getGUI(entityPlayer, this, this.getBlockWrapper());
    }

    @Override
    public Container getContainer(EntityPlayer entityPlayer, World world, BlockPos blockPos) {
        return this.getBlockWrapper().getInterface().getContainer(entityPlayer, this, this.getBlockWrapper());
    }
}

