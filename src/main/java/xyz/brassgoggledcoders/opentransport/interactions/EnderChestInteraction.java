package xyz.brassgoggledcoders.opentransport.interactions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import xyz.brassgoggledcoders.opentransport.api.IBlockContainer;
import xyz.brassgoggledcoders.opentransport.api.IInteraction;

public class EnderChestInteraction implements IInteraction
{
	@Override
	public boolean interact(EntityPlayer entityPlayer, IBlockContainer blockContainer)
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
