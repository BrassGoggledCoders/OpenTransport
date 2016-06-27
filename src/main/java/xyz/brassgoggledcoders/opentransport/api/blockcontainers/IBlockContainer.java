package xyz.brassgoggledcoders.opentransport.api.blockcontainers;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.renderers.RenderType;

public interface IBlockContainer
{
	Block getBlock();

	IBlockState getBlockState();

	String getUnlocalizedName();

	RenderType getRenderType();

	IInteraction getClickInteraction();

	IGuiInterface getInterface();

	boolean onInteract(EntityPlayer entityPlayer);

	void tick();

	void markDirty();

	void setHolder(IHolderEntity entity);

	boolean hasTileEntity();

	TileEntity getTileEntity();

	NBTTagCompound writeToNBT(NBTTagCompound tagCompound);

	void readFromNBT(NBTTagCompound tagCompound);

	IBlockContainer copy();
}
