package xyz.brassgoggledcoders.opentransport.modules.immersiveengineering;

import blusunrize.immersiveengineering.common.blocks.wooden.TileEntityWoodenCrate;
import blusunrize.immersiveengineering.common.gui.ContainerCrate;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

import javax.annotation.Nonnull;

public class ContainerCrateEntity extends ContainerCrate {
	private IHolderEntity holderEntity;

	public ContainerCrateEntity(InventoryPlayer inventoryPlayer, IHolderEntity holderEntity,
			TileEntityWoodenCrate crate) {
		super(inventoryPlayer, crate);
		this.holderEntity = holderEntity;
	}

	@Override
	public boolean canInteractWith(@Nonnull EntityPlayer entityPlayer) {
		return holderEntity.isUseableByPlayer(entityPlayer);
	}
}
