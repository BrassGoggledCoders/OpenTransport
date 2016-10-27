package xyz.brassgoggledcoders.opentransport.modules.vanilla.containers;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IGuiInterface;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

public class CraftingTableGuiInterface implements IGuiInterface {
    @Override
    public Gui getGUI(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        return new GuiCrafting(entityPlayer.inventory, entityPlayer.getEntityWorld());
    }

    @Override
    public Container getContainer(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        return new ContainerCraftingTableEntity(holderEntity, entityPlayer);
    }
}
