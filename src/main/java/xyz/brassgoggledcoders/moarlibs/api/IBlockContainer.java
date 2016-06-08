package xyz.brassgoggledcoders.moarlibs.api;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import xyz.brassgoggledcoders.moarlibs.renderers.RenderType;

public interface IBlockContainer
{
	Block getBlock();

	IBlockState getBlockState();

	String getUnlocalizedName();

	RenderType getRenderType();

	IInteraction getClickInteraction();

	boolean onInteract(EntityPlayer entityPlayer, IHolderEntity entity);

	ITileContainer getTileContainer();

	NBTTagCompound writeToNBT(NBTTagCompound tagCompound);

	void readFromNBT(NBTTagCompound tagCompound);
}
