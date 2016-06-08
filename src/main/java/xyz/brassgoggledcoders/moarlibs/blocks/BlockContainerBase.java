package xyz.brassgoggledcoders.moarlibs.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import xyz.brassgoggledcoders.moarlibs.MoarLibs;
import xyz.brassgoggledcoders.moarlibs.api.IBlockContainer;
import xyz.brassgoggledcoders.moarlibs.api.IHolderEntity;
import xyz.brassgoggledcoders.moarlibs.api.IInteraction;
import xyz.brassgoggledcoders.moarlibs.api.ITileContainer;
import xyz.brassgoggledcoders.moarlibs.renderers.RenderType;

public class BlockContainerBase implements IBlockContainer
{
	Block block;
	IBlockState blockState;
	String unlocalizedName;
	ITileContainer tileContainer;
	IInteraction clickInteraction;
	RenderType renderType = RenderType.VMC;

	public BlockContainerBase(Block block)
	{
		this.block = block;
		this.blockState = block.getDefaultState();
		this.unlocalizedName = block.getUnlocalizedName();
	}

	public BlockContainerBase setBlock(Block block)
	{
		this.block = block;
		this.blockState = block.getDefaultState();
		return this;
	}

	public BlockContainerBase setBlockState(IBlockState blockState)
	{
		this.block = blockState.getBlock();
		this.blockState = blockState;
		return this;
	}

	public BlockContainerBase setUnlocalizedName(String name)
	{
		this.unlocalizedName = name;
		return this;
	}

	public BlockContainerBase setClickInteraction(IInteraction interaction)
	{
		this.clickInteraction = interaction;
		return this;
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
	public IInteraction getClickInteraction()
	{
		return clickInteraction;
	}

	@Override
	public void onInteract(EntityPlayer entityPlayer, IHolderEntity entity)
	{
		EntityPlayer entityPlayerWrapper = MoarLibs.proxy.getEntityPlayerWrapper(entityPlayer, entity);
		if(getClickInteraction() != null)
		{
			this.clickInteraction.interact(entityPlayerWrapper, this);
		}
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
