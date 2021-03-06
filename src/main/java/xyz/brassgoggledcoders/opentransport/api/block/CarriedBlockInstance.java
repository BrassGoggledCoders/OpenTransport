package xyz.brassgoggledcoders.opentransport.api.block;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class CarriedBlockInstance implements ICapabilityProvider {
    private CarriedBlock carriedBlock;
    private TileEntity tileEntity;
    private ItemStack itemStack;

    public CarriedBlockInstance(CarriedBlock carriedBlock) {
        this.carriedBlock = carriedBlock;
        itemStack = carriedBlock.getItemStackFor(this);
    }

    public CarriedBlockInstance(CarriedBlock carriedBlock, World world) {
        this(carriedBlock);
        Block block = carriedBlock.getBlock();
        if (block.hasTileEntity(carriedBlock.getBlockState())) {
            this.tileEntity = block.createTileEntity(world, carriedBlock.getBlockState());
        }
    }

    public IBlockState getBlockState() {
        return this.getCarriedBlock().getBlockState();
    }

    public Optional<TileEntity> getTileEntity() {
        return Optional.ofNullable(tileEntity);
    }

    public CarriedBlock getCarriedBlock() {
        return carriedBlock;
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing) {
        return this.getTileEntity()
                .map(tile -> tile.hasCapability(capability, facing))
                .orElse(false);
    }

    @Nullable
    @Override
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return this.getTileEntity()
                .map(tile -> tile.getCapability(capability, facing))
                .orElse(null);
    }

    public void readFromNBT(NBTTagCompound nbtTagCompound) {
        this.getTileEntity().ifPresent(value -> value.readFromNBT(nbtTagCompound));
    }

    public NBTTagCompound writeToNBT(NBTTagCompound nbtTagCompound) {
        return this.getTileEntity()
                .map(tile -> tile.writeToNBT(nbtTagCompound))
                .orElse(nbtTagCompound);
    }
}
