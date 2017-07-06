package xyz.brassgoggledcoders.opentransport.modules.tinkers.interfaces;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import slimeknights.tconstruct.tools.common.client.GuiPartChest;
import slimeknights.tconstruct.tools.common.inventory.ContainerPartChest;
import slimeknights.tconstruct.tools.common.tileentity.TilePartChest;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.guiinterfaces.IGuiInterface;

import javax.annotation.Nonnull;

public class PartChestInterface implements IGuiInterface {
    @Override
    public Gui getGUI(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        return new GuiPartChest(entityPlayer.inventory, blockWrapper.getWorldWrapper(), BlockPos.ORIGIN,
                (TilePartChest) blockWrapper.getTileEntity());
    }

    @Override
    public Container getContainer(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        return new ContainerPartChestEntity(entityPlayer, holderEntity);
    }

    public class ContainerPartChestEntity extends ContainerPartChest {
        private IHolderEntity holderEntity;

        public ContainerPartChestEntity(EntityPlayer entityPlayer, IHolderEntity holderEntity) {
            super(entityPlayer.inventory, (TilePartChest) holderEntity.getBlockWrapper().getTileEntity());
            this.holderEntity = holderEntity;
        }

        public boolean canInteractWith(@Nonnull EntityPlayer entityPlayer) {
            return holderEntity.isUseableByPlayer(entityPlayer);
        }
    }
}
