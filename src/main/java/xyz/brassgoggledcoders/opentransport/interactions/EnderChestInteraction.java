package xyz.brassgoggledcoders.opentransport.interactions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IBlockContainer;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IInteraction;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

public class EnderChestInteraction implements IInteraction
{
	@Override
	public boolean interact(EntityPlayer entityPlayer, EnumHand hand, ItemStack itemStack, IHolderEntity holderEntity,
			IBlockContainer blockContainer)
	{
		InventoryEnderChest inventoryenderchest = entityPlayer.getInventoryEnderChest();

		if (!entityPlayer.worldObj.isRemote && !entityPlayer.isSneaking())
		{
			entityPlayer.displayGUIChest(inventoryenderchest);
			return true;
		}
		return false;
	}
}