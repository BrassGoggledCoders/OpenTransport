package xyz.brassgoggledcoders.opentransport.modules.ironchest.interfaces;

import cpw.mods.ironchest.BlockIronChest;
import cpw.mods.ironchest.ContainerIronChest;
import cpw.mods.ironchest.IronChestType;
import cpw.mods.ironchest.TileEntityIronChest;
import cpw.mods.ironchest.client.GUIChest;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.guiinterfaces.IGuiInterface;

import javax.annotation.Nonnull;

public class IronChestInterface implements IGuiInterface {
    @Override
    public Gui getGUI(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        GUIChest guiChest = GUIChest.GUI.buildGUI(this.getType(blockWrapper), entityPlayer.inventory,
                (TileEntityIronChest) blockWrapper.getTileEntity());
        guiChest.inventorySlots = getContainer(entityPlayer, holderEntity, blockWrapper);
        return guiChest;
    }

    @Override
    public Container getContainer(EntityPlayer entityPlayer, IHolderEntity holderEntity,
                                  IBlockWrapper blockWrapper) {
        TileEntityIronChest ironChest = (TileEntityIronChest) blockWrapper.getTileEntity();
        return new ContainerIronChestEntity(entityPlayer.inventory, holderEntity, ironChest, getType(blockWrapper));
    }

    private IronChestType getType(IBlockWrapper blockWrapper) {
        return blockWrapper.getBlockState().getValue(BlockIronChest.VARIANT_PROP);
    }

    public static class ContainerIronChestEntity extends ContainerIronChest {
        private IHolderEntity holderEntity;

        public ContainerIronChestEntity(IInventory playerInventory, IHolderEntity holderEntity, TileEntityIronChest chest,
                                        IronChestType type) {
            super(playerInventory, chest, type, ContainerIronChestEntity.ContainerSize.values()[type.ordinal()].xSize,
                    ContainerIronChestEntity.ContainerSize.values()[type.ordinal()].ySize);
            this.holderEntity = holderEntity;
        }

        public boolean canInteractWith(@Nonnull EntityPlayer entityPlayer) {
            return holderEntity.isUseableByPlayer(entityPlayer);
        }

        public enum ContainerSize {
            IRON(184, 202), GOLD(184, 256), DIAMOND(238, 256), COPPER(184, 184), SILVER(184, 238), CRYSTAL(238,
                    256), OBSIDIAN(238, 256), DIRTCHEST9000(184, 184);

            public int xSize;
            public int ySize;

            ContainerSize(int xSize, int ySize) {
                this.xSize = xSize;
                this.ySize = ySize;
            }
        }
    }
}
