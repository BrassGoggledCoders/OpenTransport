package xyz.brassgoggledcoders.opentransport.api.wrappers.block.actions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockPlacedByAction extends ActionBase {
    public BlockPlacedByAction() {
        super(ActionType.PLACED);
    }

    @Override
    public boolean actionOccurred(@Nullable EntityPlayer entityPlayer, @Nullable EnumHand hand, @Nullable ItemStack itemStack,
                                  @Nonnull IHolderEntity holderEntity, @Nonnull IBlockWrapper blockWrapper) {
        blockWrapper.getBlock().onBlockPlacedBy(getWorldWrapper(holderEntity),
                    BlockPos.ORIGIN, blockWrapper.getBlockState(), entityPlayer, itemStack);
        return true;
    }
}
