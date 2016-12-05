package xyz.brassgoggledcoders.opentransport.api.wrappers.player;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntityCommandBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.FoodStats;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.LockCode;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import xyz.brassgoggledcoders.opentransport.api.OpenTransportAPI;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class EntityPlayerSPWrapper extends EntityPlayerSP {
    protected EntityPlayerSP entityPlayer;
    protected IHolderEntity holderEntity;
    protected IBlockWrapper blockWrapper;

    public EntityPlayerSPWrapper(EntityPlayerSP entityPlayer, IHolderEntity holderEntity) {
        super(Minecraft.getMinecraft(), entityPlayer.worldObj, entityPlayer.connection,
                entityPlayer.getStatFileWriter());
        this.entityPlayer = entityPlayer;
        this.holderEntity = holderEntity;
        this.blockWrapper = holderEntity.getBlockWrapper();
        this.worldObj = entityPlayer.worldObj;
    }

    private void openGui() {
        OpenTransportAPI.getModWrapper().openGui(this.holderEntity, this.getEntityPlayer(), this.getEntityWorld());
    }

    @Override
    public void addChatComponentMessage(@Nullable ITextComponent chatComponent) {
        this.getEntityPlayer().addChatComponentMessage(chatComponent);
    }

    @Override
    public boolean canCommandSenderUseCommand(int p_70003_1_, String p_70003_2_) {
        return false;
    }

    @Override
    public double getDistanceSq(double x, double y, double z) {
        if (this.getEntity() != null) {
            return this.entityPlayer.getDistanceSq(this.getEntity().posX, this.getEntity().posY, this.getEntity().posZ);
        } else {
            return 64;
        }
    }

    @Override
    public void openGui(@Nonnull Object mod, int id, @Nonnull World world, int posX, int posY, int posZ) {
        this.openGui();
    }

    @Override
    public void setItemStackToSlot(@Nonnull EntityEquipmentSlot slot, @Nullable ItemStack stack) {
        this.getEntityPlayer().setItemStackToSlot(slot, stack);
    }

    @Override
    public ItemStack getHeldItem(EnumHand hand) {
        return this.getEntityPlayer().getHeldItem(hand);
    }

    @Override
    @Nullable
    public ItemStack getItemStackFromSlot(@Nonnull EntityEquipmentSlot slot) {
        return this.getEntityPlayer().getItemStackFromSlot(slot);
    }

    @Override
    @Nonnull
    public Iterable<ItemStack> getHeldEquipment() {
        return this.getEntityPlayer().getHeldEquipment();
    }

    @Override
    public boolean canOpen(LockCode code) {
        return this.getEntityPlayer().canOpen(code);
    }

    @Override
    public boolean sendCommandFeedback() {
        return this.getEntityPlayer().sendCommandFeedback();
    }

    @Override
    public boolean replaceItemInInventory(int slot, @Nonnull ItemStack itemStack) {
        return this.getEntityPlayer().replaceItemInInventory(slot, itemStack);
    }

    @Override
    public boolean isSneaking() {
        return this.getEntityPlayer().isSneaking();
    }

    @Override
    public boolean isSpectator() {
        return this.getEntityPlayer().isSpectator();
    }

    @Override
    public void displayGUIChest(@Nonnull IInventory iInventory) {
        this.openGui();
    }

    @Override
    public void displayGuiCommandBlock(@Nullable TileEntityCommandBlock commandBlock) {
        this.openGui();
    }

    @Override
    public void displayGui(IInteractionObject guiOwner) {
        this.openGui();
    }

    @Override
    @Nonnull
    public InventoryEnderChest getInventoryEnderChest() {
        return this.getEntityPlayer().getInventoryEnderChest();
    }

    @Override
    @Nonnull
    public EnumHandSide getPrimaryHand() {
        return this.getEntityPlayer().getPrimaryHand();
    }

    @Override
    public void setPrimaryHand(EnumHandSide hand) {
        this.getEntityPlayer().setPrimaryHand(hand);
    }

    @Override
    public boolean hasAchievement(Achievement achievement) {
        return this.getEntityPlayer().hasAchievement(achievement);
    }

    @Override
    public void addStat(@Nonnull StatBase stat) {
        this.getEntityPlayer().addStat(stat);
    }

    @Override
    public void addStat(StatBase stat, int amount) {
        this.getEntityPlayer().addStat(stat, amount);
    }

    @Override
    public void takeStat(StatBase stat) {
        this.getEntityPlayer().takeStat(stat);
    }

    @Override
    @Nonnull
    public UUID getPersistentID() {
        return this.getEntityPlayer().getPersistentID();
    }

    @Override
    public void addChatMessage(@Nonnull ITextComponent component) {
        this.getEntityPlayer().addChatMessage(component);
    }

    @Override
    @Nonnull
    public FoodStats getFoodStats() {
        return this.getEntityPlayer().getFoodStats();
    }

    @Override
    public boolean canEat(boolean ignoreHunger) {
        return this.getEntityPlayer().canEat(ignoreHunger);
    }

    @Override
    @Nonnull
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nonnull EnumFacing facing) {
        return this.getEntityPlayer().getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nonnull EnumFacing facing) {
        return this.getEntityPlayer().hasCapability(capability, facing);
    }

    public EntityPlayer getEntityPlayer() {
        return entityPlayer;
    }

    public Entity getEntity() {
        return this.holderEntity.getEntity();
    }
}
