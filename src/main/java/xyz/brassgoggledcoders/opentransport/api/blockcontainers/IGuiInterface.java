package xyz.brassgoggledcoders.opentransport.api.blockcontainers;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

public interface IGuiInterface {
	Gui getGUI(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockContainer blockContainer);

	Container getContainer(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockContainer blockContainer);
}
