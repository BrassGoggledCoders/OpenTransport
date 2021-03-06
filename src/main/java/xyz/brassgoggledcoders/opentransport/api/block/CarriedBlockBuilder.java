package xyz.brassgoggledcoders.opentransport.api.block;

import com.teamacronymcoders.base.util.ItemStackUtils;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.Optional;
import java.util.function.Function;

public class CarriedBlockBuilder {
    private IBlockState blockState;
    private ResourceLocation registryName;
    private Function<CarriedBlockInstance, ItemStack> itemStackFunction;

    private CarriedBlockBuilder(IBlockState blockState) {
        this.blockState = blockState;
        this.itemStackFunction = carriedBlockInstance ->
                ItemStackUtils.getItemStackFromBlockState(carriedBlockInstance.getCarriedBlock().getBlockState());
    }

    public static CarriedBlockBuilder create(Block block) {
        return create(block.getDefaultState());
    }

    public static CarriedBlockBuilder create(ResourceLocation blockRegistryName) {
        Block block = ForgeRegistries.BLOCKS.getValue(blockRegistryName);
        return create(Optional.ofNullable(block)
                .map(Block::getDefaultState)
                .orElseThrow(() -> new IllegalArgumentException("No Block Found with name: " + blockRegistryName)));
    }


    public static CarriedBlockBuilder create(IBlockState blockState) {
        return new CarriedBlockBuilder(blockState);
    }

    public CarriedBlockBuilder withRegistryName(ResourceLocation registryName) {
        this.registryName = registryName;
        return this;
    }

    public CarriedBlockBuilder withItemStackFunction(Function<CarriedBlockInstance, ItemStack> itemStackFunction) {
        this.itemStackFunction = itemStackFunction;
        return this;
    }

    public CarriedBlock build() {
        CarriedBlock carriedBlock = new CarriedBlock(blockState, itemStackFunction);
        Optional.ofNullable(registryName).ifPresent(carriedBlock::setRegistryName);
        return carriedBlock;
    }
}

