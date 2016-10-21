package xyz.brassgoggledcoders.opentransport.modules.ironchest;

import cpw.mods.ironchest.ContainerIronChest;
import cpw.mods.ironchest.IronChestType;
import cpw.mods.ironchest.TileEntityIronChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

import javax.annotation.Nonnull;

public class ContainerIronChestEntity extends ContainerIronChest {
    private IHolderEntity holderEntity;

    public ContainerIronChestEntity(IInventory playerInventory, IHolderEntity holderEntity, TileEntityIronChest chest,
                                    IronChestType type) {
        super(playerInventory, chest, type, ContainerSize.values()[type.ordinal()].xSize,
                ContainerSize.values()[type.ordinal()].ySize);
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
