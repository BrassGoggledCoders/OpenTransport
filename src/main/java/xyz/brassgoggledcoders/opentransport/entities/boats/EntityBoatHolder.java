package xyz.brassgoggledcoders.opentransport.entities.boats;

import com.google.common.base.Optional;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IBlockContainer;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.items.boats.ItemBoatHolder;
import xyz.brassgoggledcoders.opentransport.registries.BlockContainerRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityBoatHolder extends EntityBoatBase implements IHolderEntity<EntityBoatHolder>
{
	IBlockContainer blockContainer;
	private static final DataParameter<String> BLOCK_CONTAINER_NAME =
			EntityDataManager.createKey(EntityBoat.class, DataSerializers.STRING);
	private static final DataParameter<Optional<ItemStack>> ITEM_BOAT =
			EntityDataManager.createKey(EntityBoat.class, DataSerializers.OPTIONAL_ITEM_STACK);

	public EntityBoatHolder(World world)
	{
		super(world);
	}

	@Override
	protected void entityInit()
	{
		super.entityInit();
		this.dataManager.register(BLOCK_CONTAINER_NAME, "");
		this.dataManager.register(ITEM_BOAT, Optional.<ItemStack>absent());
	}

	@Override
	@Nonnull
	public Item getItemBoat()
	{
		Optional<ItemStack> itemStackBoat = this.dataManager.get(ITEM_BOAT);
		if(itemStackBoat.isPresent())
		{
			return itemStackBoat.get().getItem();
		}
		return Items.BOAT;
	}

	@Override
	public EntityBoatHolder getEntity()
	{
		return this;
	}

	@Override
	public IBlockContainer getBlockContainer()
	{
		if(this.blockContainer == null)
		{
			String containerName = this.dataManager.get(BLOCK_CONTAINER_NAME);
			this.blockContainer = BlockContainerRegistry.getBlockContainer(containerName);
			if(this.blockContainer !=  null) {
				this.blockContainer.setHolder(this);
			}
		}
		return this.blockContainer;
	}

	@Override
	public void setBlockContainer(IBlockContainer blockContainer)
	{
		this.dataManager.set(BLOCK_CONTAINER_NAME, blockContainer.getUnlocalizedName());
		this.blockContainer = blockContainer;
		this.blockContainer.setHolder(this);
	}

	public void setItemBoat(@Nonnull ItemStack itemBoatStack)
	{
		if(itemBoatStack.getItem() instanceof ItemBoatHolder)
		{
			this.dataManager.set(ITEM_BOAT, Optional.of(itemBoatStack));
		}
	}

	@Override
	public boolean processInitialInteract(@Nonnull EntityPlayer entityPlayer, @Nullable ItemStack stack, EnumHand hand)
	{
		return this.getBlockContainer() != null && this.getBlockContainer().onInteract(entityPlayer);
	}

	@Override
	@Nonnull
	public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound)
	{
		super.writeToNBT(nbtTagCompound);

		Optional<ItemStack> itemStackBoat = this.dataManager.get(ITEM_BOAT);
		if(itemStackBoat.isPresent())
		{
			NBTTagCompound itemBoat = new NBTTagCompound();
			nbtTagCompound.setTag("ITEM_BOAT", itemStackBoat.get().writeToNBT(itemBoat));
		}
		nbtTagCompound.setString("CONTAINER_NAME", this.dataManager.get(BLOCK_CONTAINER_NAME));
		if(blockContainer != null)
		{
			NBTTagCompound containerTag = new NBTTagCompound();
			containerTag = blockContainer.writeToNBT(containerTag);
			nbtTagCompound.setTag("CONTAINER", containerTag);
		}
		return nbtTagCompound;
	}

	@Override
	public void readFromNBT(NBTTagCompound nbtTagCompound)
	{
		super.readFromNBT(nbtTagCompound);
		this.dataManager.set(BLOCK_CONTAINER_NAME, nbtTagCompound.getString("CONTAINER_NAME"));
		this.setBlockContainer(BlockContainerRegistry.getBlockContainer(nbtTagCompound.getString("CONTAINER_NAME")));
		this.setItemBoat(ItemStack.loadItemStackFromNBT(nbtTagCompound.getCompoundTag("ITEM_BOAT")));
		blockContainer.setHolder(this);
		blockContainer.readFromNBT(nbtTagCompound.getCompoundTag("CONTAINER"));
	}
}
