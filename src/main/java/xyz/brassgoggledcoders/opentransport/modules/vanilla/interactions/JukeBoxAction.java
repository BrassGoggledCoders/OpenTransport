package xyz.brassgoggledcoders.opentransport.modules.vanilla.interactions;

import com.teamacronymcoders.base.util.ItemStackUtils;
import net.minecraft.block.BlockJukebox;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.actions.BlockActivationAction;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class JukeBoxAction extends BlockActivationAction {
    @Override
    public boolean actionOccurred(@Nullable EntityPlayer entityPlayer, @Nullable EnumHand hand, @Nullable ItemStack itemStack,
                                  @Nonnull IHolderEntity holderEntity, @Nonnull IBlockWrapper blockWrapper) {
        boolean didSomething = false;
        if(itemStack != null && entityPlayer != null) {
            if (!blockWrapper.getWorldWrapper().isRemote && ItemStackUtils.isItemInstanceOf(itemStack, ItemRecord.class)) {
                ItemRecord itemRecord = (ItemRecord) itemStack.getItem();
                if (blockWrapper.getBlock() == Blocks.JUKEBOX && !blockWrapper.getBlockState().getValue(BlockJukebox.HAS_RECORD)) {
                    ((BlockJukebox) Blocks.JUKEBOX).insertRecord(blockWrapper.getWorldWrapper(), BlockPos.ORIGIN, blockWrapper.getBlockState(), itemStack);
                    blockWrapper.getWorldWrapper().playEvent(null, 1010, BlockPos.ORIGIN, Item.getIdFromItem(itemRecord));
                    itemStack.shrink(1);
                    entityPlayer.addStat(StatList.RECORD_PLAYED);
                    didSomething = true;
                }
            }
        }

        return didSomething;
    }
}
