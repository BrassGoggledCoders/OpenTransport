package xyz.brassgoggledcoders.opentransport.modules.ironchest;

import cpw.mods.ironchest.BlockIronChest;
import cpw.mods.ironchest.IronChestType;
import cpw.mods.ironchest.TileEntityIronChest;
import cpw.mods.ironchest.client.GUIChest;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IGuiInterface;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

public class IronChestInterface implements IGuiInterface {
	@Override
	public Gui getGUI(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockWrapper blockContainer) {
		GUIChest guiChest = GUIChest.GUI.buildGUI(this.getType(blockContainer), entityPlayer.inventory,
				(TileEntityIronChest) blockContainer.getTileEntity());
		guiChest.inventorySlots = getContainer(entityPlayer, holderEntity, blockContainer);
		return guiChest;
	}

	@Override
	public Container getContainer(EntityPlayer entityPlayer, IHolderEntity holderEntity,
			IBlockWrapper blockContainer) {
		TileEntityIronChest ironChest = (TileEntityIronChest) blockContainer.getTileEntity();
		return new ContainerIronChestEntity(entityPlayer.inventory, holderEntity, ironChest, getType(blockContainer));
	}

	private IronChestType getType(IBlockWrapper blockContainer) {
		return blockContainer.getBlockState().getValue(BlockIronChest.VARIANT_PROP);
	}
}
