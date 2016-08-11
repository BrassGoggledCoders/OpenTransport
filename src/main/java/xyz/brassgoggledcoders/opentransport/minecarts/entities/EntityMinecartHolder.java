package xyz.brassgoggledcoders.opentransport.minecarts.entities;

import com.google.common.base.Optional;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
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
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.client.guis.IOpenableGUI;
import xyz.brassgoggledcoders.boilerplate.entity.minecarts.EntityMinecartBase;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IBlockContainer;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.minecarts.items.ItemMinecartHolder;
import xyz.brassgoggledcoders.opentransport.registries.BlockContainerRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityMinecartHolder extends EntityMinecartBase implements IHolderEntity<EntityMinecartHolder>, IOpenableGUI {
	private static final DataParameter<String> BLOCK_CONTAINER_NAME =
			EntityDataManager.createKey(EntityMinecartHolder.class, DataSerializers.STRING);
	private static final DataParameter<Optional<ItemStack>> ITEM_CART =
			EntityDataManager.createKey(EntityMinecartHolder.class, DataSerializers.OPTIONAL_ITEM_STACK);

	private	IBlockContainer blockContainer;

	public EntityMinecartHolder(World world) {
		super(world);
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		this.dataManager.register(BLOCK_CONTAINER_NAME, "");
		this.dataManager.register(ITEM_CART, Optional.absent());
	}

	@Nonnull
	@Override
	public ItemMinecart getItem() {
		Optional<ItemStack> itemStackCart = this.dataManager.get(ITEM_CART);
		if(itemStackCart.isPresent()) {
			return (ItemMinecart)itemStackCart.get().getItem();
		}
		return (ItemMinecart)Items.MINECART;
	}

	public void setItemCart(@Nonnull ItemStack itemCartStack) {
		if(itemCartStack.getItem() instanceof ItemMinecartHolder) {
			this.dataManager.set(ITEM_CART, Optional.of(itemCartStack));
		}
	}

	@Override
	public EntityMinecartHolder getEntity() {
		return this;
	}

	@Override
	public IBlockContainer getBlockContainer() {
		if(this.blockContainer == null) {
			String containerName = this.dataManager.get(BLOCK_CONTAINER_NAME);
			this.blockContainer = BlockContainerRegistry.getBlockContainer(containerName);
			if(this.blockContainer != null) {
				this.blockContainer.setHolder(this);
			}
		}
		return this.blockContainer;
	}

	@Override
	public void setBlockContainer(IBlockContainer blockContainer) {
		this.dataManager.set(BLOCK_CONTAINER_NAME, blockContainer.getUnlocalizedName());
		this.blockContainer = blockContainer;
		this.blockContainer.setHolder(this);
	}

	@Override
	public boolean processInitialInteract(@Nonnull EntityPlayer entityPlayer, @Nullable ItemStack itemStack,
			EnumHand hand) {
		return this.getBlockContainer() != null && this.getBlockContainer().onInteract(entityPlayer, hand, itemStack);
	}

	@Override
	@Nonnull
	public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
		super.writeToNBT(nbtTagCompound);
		nbtTagCompound.setString("CONTAINER_NAME", this.dataManager.get(BLOCK_CONTAINER_NAME));
		if(blockContainer != null) {
			NBTTagCompound containerTag = new NBTTagCompound();
			containerTag = blockContainer.writeToNBT(containerTag);
			nbtTagCompound.setTag("CONTAINER", containerTag);
		}
		return nbtTagCompound;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound) {
		super.readFromNBT(nbtTagCompound);
		this.dataManager.set(BLOCK_CONTAINER_NAME, nbtTagCompound.getString("CONTAINER_NAME"));
		this.setBlockContainer(BlockContainerRegistry.getBlockContainer(nbtTagCompound.getString("CONTAINER_NAME")));
		blockContainer.setHolder(this);
		blockContainer.readFromNBT(nbtTagCompound.getCompoundTag("CONTAINER"));
	}

	@Override
	public Gui getClientGuiElement(int ID, EntityPlayer player, World world, BlockPos blockPos) {
		return this.getBlockContainer().getInterface().getGUI(player, this, this.getBlockContainer());
	}

	@Override
	public Container getServerGuiElement(int ID, EntityPlayer player, World world, BlockPos blockPos) {
		return this.getBlockContainer().getInterface().getContainer(player, this, this.getBlockContainer());
	}
}
