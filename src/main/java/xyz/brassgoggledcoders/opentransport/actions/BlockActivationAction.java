package xyz.brassgoggledcoders.opentransport.actions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.ActionType;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

public class BlockActivationAction extends BaseAction {
    @Override
    public boolean actionOccurred(ActionType actionType, EntityPlayer entityPlayer, EnumHand hand, ItemStack itemStack,
                                  IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        return actionType == ActionType.INTERACTION && blockWrapper.getBlock().onBlockActivated(
                this.getWorldWrapper(holderEntity), BlockPos.ORIGIN, blockWrapper.getBlockState(), entityPlayer, hand,
                itemStack, EnumFacing.UP, 0F, 0F, 0F);
    }
}
