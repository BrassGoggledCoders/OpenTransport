package xyz.brassgoggledcoders.opentransport.modules.vanilla;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IInteraction;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

public class EnderChestInteraction implements IInteraction {
    @Override
    public boolean interact(EntityPlayer entityPlayer, EnumHand hand, ItemStack itemStack, IHolderEntity holderEntity,
                            IBlockWrapper blockContainer) {
        InventoryEnderChest inventoryenderchest = entityPlayer.getInventoryEnderChest();

        if (!entityPlayer.worldObj.isRemote && !entityPlayer.isSneaking()) {
            entityPlayer.displayGUIChest(inventoryenderchest);
            return true;
        }
        return false;
    }
}
