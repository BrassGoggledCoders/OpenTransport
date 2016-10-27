package xyz.brassgoggledcoders.opentransport.modules.vanilla.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ContainerWorkbench;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

import javax.annotation.Nonnull;

public class ContainerCraftingTableEntity extends ContainerWorkbench {
    private IHolderEntity holderEntity;
    public ContainerCraftingTableEntity(IHolderEntity holder, EntityPlayer entityPlayer) {
        super(entityPlayer.inventory, entityPlayer.getEntityWorld(), holder.getEntity().getPosition());
        holderEntity = holder;
    }

    @Override
    public boolean canInteractWith(@Nonnull EntityPlayer entityPlayer) {
        return holderEntity.isUseableByPlayer(entityPlayer);
    }
}
