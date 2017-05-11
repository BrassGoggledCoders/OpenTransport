package xyz.brassgoggledcoders.opentransport.modules.vanilla.interactions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.actions.ActionType;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.actions.BlockActivationAction;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.actions.IActionListener;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.player.IPlayerWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EnderChestAction extends BlockActivationAction {
    @Override
    public boolean actionOccurred(@Nullable EntityPlayer entityPlayer, @Nullable EnumHand hand, @Nullable ItemStack itemStack,
                                  @Nonnull IHolderEntity holderEntity, @Nonnull IBlockWrapper blockWrapper) {
        if (entityPlayer != null && !entityPlayer.getEntityWorld().isRemote && !entityPlayer.isSneaking()) {
            if(entityPlayer instanceof IPlayerWrapper) {
                InventoryEnderChest inventoryenderchest = entityPlayer.getInventoryEnderChest();
                ((IPlayerWrapper) entityPlayer).getEntityPlayer().displayGUIChest(inventoryenderchest);
            }
            return true;
        }

        return super.actionOccurred(entityPlayer, hand, itemStack, holderEntity, blockWrapper);
    }
}
