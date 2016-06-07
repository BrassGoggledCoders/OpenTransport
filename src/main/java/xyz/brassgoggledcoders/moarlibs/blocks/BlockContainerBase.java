package xyz.brassgoggledcoders.moarlibs.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import xyz.brassgoggledcoders.moarlibs.api.IBlockContainer;
import xyz.brassgoggledcoders.moarlibs.api.ITileContainer;
import xyz.brassgoggledcoders.moarlibs.renderers.RenderType;

public class BlockContainerBase implements IBlockContainer
{
	Block block;
	IBlockState blockState;
	String unlocalizedName;
	ITileContainer tileContainer;
	RenderType renderType = RenderType.VMC;

	public BlockContainerBase(Block block)
	{
		this.block = block;
		this.blockState = block.getDefaultState();
		this.unlocalizedName = block.getUnlocalizedName();
	}

	@Override
	public Block getBlock()
	{
		return block;
	}

	@Override
	public IBlockState getBlockState()
	{
		return blockState;
	}

	@Override
	public String getUnlocalizedName()
	{
		return unlocalizedName;
	}

	@Override
	public RenderType getRenderType()
	{
		return renderType;
	}

	@Override
	public ITileContainer getTileContainer()
	{
		return tileContainer;
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tagCompound)
	{
		if(tileContainer != null)
		{
			tileContainer.writeToNBT(tagCompound);
		}
		return tagCompound;
	}

	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		if(tileContainer != null)
		{
			tileContainer.readFromNBT(tagCompound);
		}
	}
}
