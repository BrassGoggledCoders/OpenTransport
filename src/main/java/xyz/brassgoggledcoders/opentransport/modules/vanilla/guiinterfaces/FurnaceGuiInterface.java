package xyz.brassgoggledcoders.opentransport.modules.vanilla.guiinterfaces;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.IInventory;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.guiinterfaces.IGuiInterface;

import javax.annotation.Nonnull;

public class FurnaceGuiInterface implements IGuiInterface {
    @Override
    public Gui getGUI(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        return new GuiFurnace(entityPlayer.inventory, (IInventory) holderEntity.getBlockWrapper().getTileEntity());
    }

    @Override
    public Container getContainer(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        return new ContainerFurnaceEntity(holderEntity, entityPlayer);
    }

    public class ContainerFurnaceEntity extends ContainerFurnace {
        public IHolderEntity holderEntity;

        public ContainerFurnaceEntity(IHolderEntity holderEntity, EntityPlayer entityPlayer) {
            super(entityPlayer.inventory, (IInventory) holderEntity.getBlockWrapper().getTileEntity());
            this.holderEntity = holderEntity;
        }

        @Override
        public boolean canInteractWith(@Nonnull EntityPlayer entityPlayer) {
            return holderEntity.isUseableByPlayer(entityPlayer);
        }
    }
}
