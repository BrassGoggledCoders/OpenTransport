package xyz.brassgoggledcoders.opentransport.api.wrappers.block.actions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface IActionListener {
    boolean hasActionForType(@Nonnull ActionType actionType);

    boolean actionOccurred(@Nullable EntityPlayer entityPlayer, @Nullable EnumHand hand, @Nullable ItemStack itemStack,
                           @Nonnull IHolderEntity holderEntity, @Nonnull IBlockWrapper blockWrapper);
}
