package xyz.brassgoggledcoders.opentransport.modules.loaders.blocks;

import com.teamacronymcoders.base.blocks.BlockSidedBase;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.opentransport.modules.loaders.tiles.TileLoaderBase;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class BlockLoaderBase<LOADER extends TileLoaderBase> extends BlockSidedBase<LOADER> {
    private Supplier<LOADER> loaderSupplier;

    public BlockLoaderBase(String name, Supplier<LOADER> loaderSupplier) {
        super(Material.IRON, "loader." + name);
        this.loaderSupplier = loaderSupplier;
    }

    @Nonnull
    @Override
    public TileEntity createTileEntity(@Nonnull World world, @Nonnull IBlockState blockState) {
        return loaderSupplier.get();
    }

    @Override
    public Class<? extends TileEntity> getTileEntityClass() {
        return loaderSupplier.get().getClass();
    }
}
