package xyz.brassgoggledcoders.opentransport.modules.tinkers.interfaces;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import slimeknights.tconstruct.tools.common.client.GuiPatternChest;
import slimeknights.tconstruct.tools.common.inventory.ContainerPatternChest;
import slimeknights.tconstruct.tools.common.tileentity.TilePatternChest;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.guiinterfaces.IGuiInterface;

import javax.annotation.Nonnull;

public class PatternChestInterface implements IGuiInterface {
    @Override
    public Gui getGUI(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        return new GuiPatternChest(entityPlayer.inventory, blockWrapper.getWorldWrapper(), BlockPos.ORIGIN,
                (TilePatternChest) blockWrapper.getTileEntity());
    }

    @Override
    public Container getContainer(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        return new ContainerPatternChestEntity(entityPlayer, holderEntity);
    }

    public class ContainerPatternChestEntity extends ContainerPatternChest {
        private IHolderEntity holderEntity;

        public ContainerPatternChestEntity(EntityPlayer entityPlayer, IHolderEntity holderEntity) {
            super(entityPlayer.inventory, (TilePatternChest) holderEntity.getBlockWrapper().getTileEntity());
            this.holderEntity = holderEntity;
        }

        public boolean canInteractWith(@Nonnull EntityPlayer entityPlayer) {
            return holderEntity.isUseableByPlayer(entityPlayer);
        }
    }
}
