package xyz.brassgoggledcoders.opentransport.wrappers.player;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.LockCode;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IBlockContainer;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityPlayerMPWrapper extends EntityPlayerMP {
	protected EntityPlayerMP entityPlayer;
	protected IHolderEntity containerHolder;
	protected IBlockContainer blockContainer;

	public EntityPlayerMPWrapper(EntityPlayerMP entityPlayer, IHolderEntity containerHolder) {
		super(entityPlayer.mcServer, entityPlayer.getServerWorld(), entityPlayer.getGameProfile(),
				entityPlayer.interactionManager);
		this.entityPlayer = entityPlayer;
		this.containerHolder = containerHolder;
		this.blockContainer = containerHolder.getBlockContainer();
		this.worldObj = entityPlayer.worldObj;
		this.connection = entityPlayer.connection;
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
		if(this.getEntity() != null) {
			return this.entityPlayer.getDistanceSq(this.getEntity().posX, this.getEntity().posY, this.getEntity().posZ);
		} else {
			return 64;
		}
	}

	@Override
	public void openGui(@Nonnull Object mod, int id, @Nonnull World world, int posX, int posY, int poxZ) {
		this.getEntityPlayer()
				.openGui(OpenTransport.INSTANCE, this.getEntity().getEntityId(), this.getEntity().worldObj, posX, posY,
						poxZ);
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
	public void displayGUIChest(IInventory iInventory) {
		this.getEntityPlayer().displayGUIChest(iInventory);
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
		return this.containerHolder.getEntity();
	}
}
