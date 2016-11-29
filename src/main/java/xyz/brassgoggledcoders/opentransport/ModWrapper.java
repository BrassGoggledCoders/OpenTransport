package xyz.brassgoggledcoders.opentransport;

import net.minecraft.entity.player.EntityPlayer;
import xyz.brassgoggledcoders.opentransport.api.IModWrapper;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.network.HolderUpdatePacket;

public class ModWrapper implements IModWrapper {
    @Override
    public Object getModInstance() {
        return OpenTransport.instance;
    }

    @Override
    public void sendBlockWrapperPacket(IHolderEntity entity) {
        OpenTransport.instance.getPacketHandler().sendToAllAround(new HolderUpdatePacket(entity), entity.getEntity());
    }

    @Override
    public EntityPlayer getPlayerWrapper(EntityPlayer entityPlayer, IHolderEntity entity) {
        return OpenTransport.proxy.getEntityPlayerWrapper(entityPlayer, entity);
    }
}
