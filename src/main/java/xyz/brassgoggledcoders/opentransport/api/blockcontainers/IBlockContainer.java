package xyz.brassgoggledcoders.opentransport.api.blockcontainers;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.renderers.RenderType;

import javax.annotation.Nonnull;

public interface IBlockContainer
{
	@Nonnull
	Block getBlock();

	@Nonnull
	IBlockState getBlockState();

	@Nonnull
	String getUnlocalizedName();

	@Nonnull
	RenderType getRenderType();

	@Nonnull
	IInteraction getClickInteraction();

	IGuiInterface getInterface();

	boolean onInteract(EntityPlayer entityPlayer, EnumHand hand, ItemStack itemStack);

	void tick();

	void markDirty();

	void setHolder(IHolderEntity entity);

	boolean hasTileEntity();

	TileEntity getTileEntity();

	NBTTagCompound writeToNBT(NBTTagCompound tagCompound);

	void readFromNBT(NBTTagCompound tagCompound);

	IBlockContainer copy();
}
