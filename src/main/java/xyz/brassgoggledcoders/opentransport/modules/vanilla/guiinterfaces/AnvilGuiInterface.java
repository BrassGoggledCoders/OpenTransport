package xyz.brassgoggledcoders.opentransport.modules.vanilla.guiinterfaces;

import mods.railcraft.client.gui.GuiAnvil;
import mods.railcraft.common.gui.containers.ContainerAnvil;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IGuiInterface;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.modules.immersiveengineering.ContainerCrateEntity;

import javax.annotation.Nonnull;

public class AnvilGuiInterface implements IGuiInterface {
    @Override
    public Gui getGUI(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        return new GuiRepair(entityPlayer.inventory, blockWrapper.getWorldWrapper());
    }

    @Override
    public Container getContainer(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        return new ContainerAnvilEntity(entityPlayer, holderEntity, blockWrapper);
    }

    public class ContainerAnvilEntity extends ContainerRepair {
        private IHolderEntity holderEntity;
        public ContainerAnvilEntity(EntityPlayer entityPlayer, IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
            super(entityPlayer.inventory, blockWrapper.getWorldWrapper(), BlockPos.ORIGIN, entityPlayer);
            this.holderEntity = holderEntity;
        }

        @Override
        public boolean canInteractWith(@Nonnull EntityPlayer entityPlayer) {
            return holderEntity.isUseableByPlayer(entityPlayer);
        }
    }
}
