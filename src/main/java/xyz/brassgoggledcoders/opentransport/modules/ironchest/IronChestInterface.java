package xyz.brassgoggledcoders.opentransport.modules.ironchest;

import cpw.mods.ironchest.BlockIronChest;
import cpw.mods.ironchest.IronChestType;
import cpw.mods.ironchest.TileEntityIronChest;
import cpw.mods.ironchest.client.GUIChest;
import net.minecraft.client.gui.Gui;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IGuiInterface;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

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
}
