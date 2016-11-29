package xyz.brassgoggledcoders.opentransport.modules.tinkers.interfaces;

import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import slimeknights.tconstruct.tools.common.client.GuiPatternChest;
import slimeknights.tconstruct.tools.common.inventory.ContainerCraftingStation;
import slimeknights.tconstruct.tools.common.tileentity.TileCraftingStation;
import slimeknights.tconstruct.tools.common.tileentity.TilePatternChest;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IGuiInterface;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

import javax.annotation.Nonnull;

public class CraftingStationInterface implements IGuiInterface {
    @Override
    public Gui getGUI(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        return new GuiPatternChest(entityPlayer.inventory, blockWrapper.getWorldWrapper(), BlockPos.ORIGIN,
                (TilePatternChest) blockWrapper.getTileEntity());
    }

    @Override
    public Container getContainer(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        return new ContainerCraftingStationEntity(entityPlayer, holderEntity);
    }

    public class ContainerCraftingStationEntity extends ContainerCraftingStation {
        private IHolderEntity holderEntity;
        public ContainerCraftingStationEntity(EntityPlayer entityPlayer, IHolderEntity holderEntity) {
            super(entityPlayer.inventory, (TileCraftingStation) holderEntity.getBlockWrapper().getTileEntity());
            this.holderEntity = holderEntity;
        }

        public boolean canInteractWith(@Nonnull EntityPlayer entityPlayer) {
            return holderEntity.isUseableByPlayer(entityPlayer);
        }
    }
}
