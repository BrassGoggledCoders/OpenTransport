package xyz.brassgoggledcoders.opentransport.api.blockcontainers;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public interface IGuiInterface {
	Gui getGUI(EntityPlayer entityPlayer, IBlockContainer blockContainer);

	Container getContainer(EntityPlayer entityPlayer, IBlockContainer blockContainer);
}
