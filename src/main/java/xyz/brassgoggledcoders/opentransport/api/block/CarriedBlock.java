package xyz.brassgoggledcoders.opentransport.api.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.function.Function;

public class CarriedBlock extends IForgeRegistryEntry.Impl<CarriedBlock> {
    private IBlockState blockState;
    private Function<CarriedBlockInstance, ItemStack> itemStackFunction;

    public CarriedBlock(IBlockState blockState, Function<CarriedBlockInstance, ItemStack> itemStackFunction) {
        this.blockState = blockState;
        this.itemStackFunction = itemStackFunction;
    }

    public Block getBlock() {
        return blockState.getBlock();
    }

    public IBlockState getBlockState() {
        return blockState;
    }

    public EnumBlockRenderType getRenderType() {
        return blockState.getRenderType();
    }

    public CarriedBlockInstance getNewInstance(World world) {
        return new CarriedBlockInstance(this, world);
    }

    public ItemStack getItemStackFor(CarriedBlockInstance carriedBlockInstance) {
        return itemStackFunction.apply(carriedBlockInstance);
    }
}
