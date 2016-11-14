package xyz.brassgoggledcoders.opentransport.modules.vanilla.guiinterfaces;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IGuiInterface;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

import javax.annotation.Nonnull;

public class ChestGuiInterface implements IGuiInterface {
    @Override
    public Gui getGUI(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        return new GuiChest(entityPlayer.inventory, (IInventory) holderEntity.getBlockWrapper().getTileEntity());
    }

    @Override
    public Container getContainer(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        return new ContainerChestEntity(holderEntity, entityPlayer);
    }

    public class ContainerChestEntity extends ContainerChest {
        public IHolderEntity holderEntity;
        public ContainerChestEntity(IHolderEntity holderEntity, EntityPlayer entityPlayer) {
            super(entityPlayer.inventory, (IInventory)holderEntity.getBlockWrapper().getTileEntity(), entityPlayer);
            this.holderEntity = holderEntity;
        }

        @Override
        public boolean canInteractWith(@Nonnull EntityPlayer entityPlayer) {
            return holderEntity.isUseableByPlayer(entityPlayer);
        }
    }
}
