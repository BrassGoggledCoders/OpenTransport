package xyz.brassgoggledcoders.opentransport.interfaces;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IBlockContainer;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IGuiInterface;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

public class BaseInterface implements IGuiInterface {
	@Override
	public Gui getGUI(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockContainer blockContainer) {
		return null;
	}

	@Override
	public Container getContainer(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockContainer blockContainer) {
		return null;
	}
}
