package xyz.brassgoggledcoders.opentransport.api.wrappers.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
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

public class EntityPlayerMPWrapper extends EntityPlayerMP implements IPlayerWrapper {
    protected EntityPlayerMP entityPlayer;
    protected IHolderEntity holderEntity;
    protected IBlockWrapper blockWrapper;

    public static EntityPlayerMP currentlySetting;

    public EntityPlayerMPWrapper(EntityPlayerMP entityPlayer, IHolderEntity holderEntity) {
        super(setPlayer(entityPlayer), entityPlayer.getServerWorld(), entityPlayer.getGameProfile(),
                entityPlayer.interactionManager);
        this.entityPlayer = entityPlayer;
        this.holderEntity = holderEntity;
        this.blockWrapper = holderEntity.getBlockWrapper();
        this.connection = entityPlayer.connection;
        this.capabilities = entityPlayer.capabilities;
        this.openContainer = entityPlayer.openContainer;
        this.interactionManager.player = entityPlayer;
    }

    private static MinecraftServer setPlayer(EntityPlayerMP entityPlayer) {
        currentlySetting = entityPlayer;
        return entityPlayer.getServer();
    }

    private void openGui() {
        OpenTransportAPI.getModWrapper().openGui(this.holderEntity, this.getEntityPlayer(), this.getEntityWorld());
    }

    @Override
    public void sendStatusMessage(@Nonnull ITextComponent chatComponent, boolean actionBar) {
        this.getEntityPlayer().sendStatusMessage(chatComponent, actionBar);
    }

    @Override
    public boolean canUseCommand(int permLevel, @Nonnull String commandName) {
        return this.getEntityPlayer().canUseCommand(permLevel, commandName);
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
    public void openGui(@Nonnull Object mod, int id, @Nonnull World world, int posX, int posY, int poxZ) {
        this.openGui();
    }

    @Override
    public void setItemStackToSlot(@Nonnull EntityEquipmentSlot slot, @Nonnull ItemStack stack) {
        this.getEntityPlayer().setItemStackToSlot(slot, stack);
    }

    @Override
    @Nonnull
    public ItemStack getHeldItem(@Nonnull EnumHand hand) {
        return this.getEntityPlayer().getHeldItem(hand);
    }

    @Override
    @Nonnull
    public ItemStack getItemStackFromSlot(@Nonnull EntityEquipmentSlot slot) {
        return this.getEntityPlayer().getItemStackFromSlot(slot);
    }

    @Override
    @Nonnull
    public Iterable<ItemStack> getHeldEquipment() {
        return this.getEntityPlayer().getHeldEquipment();
    }

    @Override
    public boolean canOpen(@Nonnull LockCode code) {
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
    public void displayGuiCommandBlock(@Nonnull TileEntityCommandBlock commandBlock) {
        this.openGui();
    }

    @Override
    public void displayGui(@Nonnull IInteractionObject guiOwner) {
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
    public boolean hasAchievement(@Nonnull Achievement achievement) {
        return this.getEntityPlayer().hasAchievement(achievement);
    }

    @Override
    public void addStat(@Nonnull StatBase stat) {
        this.getEntityPlayer().addStat(stat);
    }

    @Override
    public void addStat(@Nonnull StatBase stat, int amount) {
        this.getEntityPlayer().addStat(stat, amount);
    }

    @Override
    public void takeStat(@Nonnull StatBase stat) {
        this.getEntityPlayer().takeStat(stat);
    }

    @Override
    public boolean canEat(boolean ignoreHunger) {
        return this.getEntityPlayer().canEat(ignoreHunger);
    }

    @Override
    @Nullable
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return this.getEntityPlayer().getCapability(capability, facing);
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return this.getEntityPlayer().hasCapability(capability, facing);
    }

    @Override
    public void sendContainerToPlayer(@Nonnull Container container) {
        this.getEntityPlayer().openContainer = this.openContainer;
        this.getEntityPlayer().sendContainerToPlayer(container);
    }

    @Override
    @Nonnull
    public UUID getPersistentID() {
        return this.getEntityPlayer().getPersistentID();
    }

    @Override
    @Nonnull
    public FoodStats getFoodStats() {
        return this.getEntityPlayer().getFoodStats();
    }

    @Override
    public EntityPlayerMP getEntityPlayer() {
        return entityPlayer != null ? entityPlayer : currentlySetting;
    }

    public Entity getEntity() {
        return this.holderEntity.getEntity();
    }
}
