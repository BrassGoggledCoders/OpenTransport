package xyz.brassgoggledcoders.opentransport.modules.boats.navigation.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.blocks.BlockTEBase;
import xyz.brassgoggledcoders.opentransport.modules.boats.navigation.tiles.TileEntityBuoy;

import javax.annotation.Nonnull;

public class BlockBuoy extends BlockTEBase {
	private static final PropertyBool BOTTOM = PropertyBool.create("bottom");

	public BlockBuoy() {
		super(Material.IRON, "buoy");
		this.setDefaultState(this.getDefaultState().withProperty(BOTTOM, true));
	}

	@Override
	@Nonnull
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, BOTTOM);
	}

	@Override
	public boolean canPlaceBlockAt(World world ,@Nonnull BlockPos blockPos) {
		boolean canPlaceBottom = world.getBlockState(blockPos).getBlock().isReplaceable(world, blockPos);
		blockPos = blockPos.up();
		boolean canPlaceTop = world.getBlockState(blockPos).getBlock().isReplaceable(world, blockPos);
		return canPlaceBottom && canPlaceTop;
	}

	@Override
	public void breakBlock(@Nonnull World world, @Nonnull BlockPos pos, @Nonnull IBlockState state) {
		BlockPos secondBlockPos = pos.down();
		if(state.getValue(BOTTOM)) {
			secondBlockPos = pos.up();
		}
		world.setBlockState(secondBlockPos, Blocks.AIR.getDefaultState());
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		world.setBlockState(pos.up(), this.getDefaultState().withProperty(BOTTOM, false));
	}

	@Override
	public boolean isSideSolid(IBlockState blockState, @Nonnull IBlockAccess blockAccess,@Nonnull BlockPos blockPos,
			EnumFacing facing) {
		return false;
	}

	@Override
	public boolean isFullBlock(IBlockState blockState) {
		return false;
	}

	@Override
	public boolean isOpaqueCube(IBlockState blockState) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState blockState) {
		return false;
	}

	@Override
	public int getMetaFromState(IBlockState blockState) {
		return blockState.getValue(BOTTOM) ? 0 : 1;
	}

	@Override
	@Nonnull
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(BOTTOM, meta == 0);
	}

	@Override
	@Nonnull
	public TileEntity createNewTileEntity(@Nonnull World world, int meta) {
		return new TileEntityBuoy(meta == 0);
	}

	@Override
	@Nonnull
	public TileEntity createTileEntity(@Nonnull World world,@Nonnull IBlockState blockState) {
		return new TileEntityBuoy(blockState.getValue(BOTTOM));
	}

	@Override
	public Class<? extends TileEntity> getTileEntityClass() {
		return TileEntityBuoy.class;
	}
}
