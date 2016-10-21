package xyz.brassgoggledcoders.opentransport.modules.immersiveengineering;

import blusunrize.immersiveengineering.client.gui.GuiCrate;
import blusunrize.immersiveengineering.common.blocks.wooden.TileEntityWoodenCrate;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IGuiInterface;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

public class CrateInterface implements IGuiInterface {
	@Override
	public Gui getGUI(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockWrapper blockContainer) {
		GuiCrate guiCrate =
				new GuiCrate(entityPlayer.inventory, (TileEntityWoodenCrate) blockContainer.getTileEntity());
		guiCrate.inventorySlots = getContainer(entityPlayer, holderEntity, blockContainer);
		return guiCrate;
	}

	@Override
	public Container getContainer(EntityPlayer entityPlayer, IHolderEntity holderEntity,
			IBlockWrapper blockContainer) {
		return new ContainerCrateEntity(entityPlayer.inventory, holderEntity,
				(TileEntityWoodenCrate) blockContainer.getTileEntity());
	}
}
