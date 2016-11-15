package xyz.brassgoggledcoders.opentransport.modules.immersiveengineering.interfaces;

import blusunrize.immersiveengineering.client.gui.GuiCrate;
import blusunrize.immersiveengineering.common.blocks.wooden.TileEntityWoodenCrate;
import blusunrize.immersiveengineering.common.gui.ContainerCrate;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IGuiInterface;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

import javax.annotation.Nonnull;

public class CrateInterface implements IGuiInterface {
    @Override
    public Gui getGUI(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        GuiCrate guiCrate =
                new GuiCrate(entityPlayer.inventory, (TileEntityWoodenCrate) blockWrapper.getTileEntity());
        guiCrate.inventorySlots = getContainer(entityPlayer, holderEntity, blockWrapper);
        return guiCrate;
    }

    @Override
    public Container getContainer(EntityPlayer entityPlayer, IHolderEntity holderEntity,
                                  IBlockWrapper blockWrapper) {
        return new ContainerCrateEntity(entityPlayer.inventory, holderEntity,
                (TileEntityWoodenCrate) blockWrapper.getTileEntity());
    }

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
}
