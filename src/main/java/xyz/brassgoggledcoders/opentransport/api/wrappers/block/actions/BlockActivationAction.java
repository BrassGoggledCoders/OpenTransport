package xyz.brassgoggledcoders.opentransport.api.wrappers.block.actions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockActivationAction extends ActionBase {
    public BlockActivationAction() {
        super(ActionType.INTERACTION);
    }

    @Override
    public boolean actionOccurred(@Nullable EntityPlayer entityPlayer, @Nullable EnumHand hand, @Nullable ItemStack itemStack,
                                  @Nonnull IHolderEntity holderEntity, @Nonnull IBlockWrapper blockWrapper) {
        return blockWrapper.getBlock().onBlockActivated(this.getWorldWrapper(holderEntity), BlockPos.ORIGIN,
                blockWrapper.getBlockState(), entityPlayer, hand, EnumFacing.UP, 0F, 0F, 0F);
    }
}
