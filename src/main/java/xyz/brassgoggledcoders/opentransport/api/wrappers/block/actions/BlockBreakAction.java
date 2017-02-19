package xyz.brassgoggledcoders.opentransport.api.wrappers.block.actions;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockBreakAction extends ActionBase {
    public BlockBreakAction() {
        super(ActionType.BROKEN);
    }

    @Override
    public boolean actionOccurred(@Nullable EntityPlayer entityPlayer, @Nullable EnumHand hand, @Nullable ItemStack itemStack,
                                  @Nonnull IHolderEntity holderEntity, @Nonnull IBlockWrapper blockWrapper) {
        blockWrapper.getBlock().breakBlock(blockWrapper.getWorldWrapper(), BlockPos.ORIGIN, blockWrapper.getBlockState());
        return true;
    }
}
