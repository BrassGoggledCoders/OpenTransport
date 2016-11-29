package xyz.brassgoggledcoders.opentransport.api;

import net.minecraft.entity.player.EntityPlayer;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

public interface IModWrapper {
    Object getModInstance();

    void sendBlockWrapperPacket(IHolderEntity entity);

    EntityPlayer getPlayerWrapper(EntityPlayer entityPlayer, IHolderEntity entity);
}
