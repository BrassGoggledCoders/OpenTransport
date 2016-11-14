package xyz.brassgoggledcoders.opentransport.actions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.ActionType;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

public class BlockPlacedByAction extends BaseAction {
    @Override
    public boolean actionOccurred(ActionType actionType, EntityPlayer entityPlayer, EnumHand hand, ItemStack itemStack,
                                  IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        if(actionType == ActionType.PLACED) {
            blockWrapper.getBlock().onBlockPlacedBy(getWorldWrapper(holderEntity),
                    BlockPos.ORIGIN, blockWrapper.getBlockState(), entityPlayer, itemStack);
        }
        return actionType == ActionType.PLACED;
    }
}
