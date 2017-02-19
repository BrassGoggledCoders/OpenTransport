package xyz.brassgoggledcoders.opentransport.api.wrappers.block.actions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;

public class BlockBreakAction implements IActionListener {
    @Override
    public boolean actionOccurred(ActionType actionType, EntityPlayer entityPlayer, EnumHand hand, ItemStack itemStack, IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        if(actionType == ActionType.BROKEN) {
            blockWrapper.getBlock().breakBlock(blockWrapper.getWorldWrapper(), BlockPos.ORIGIN, blockWrapper.getBlockState());
        }
        return true;
    }
}
