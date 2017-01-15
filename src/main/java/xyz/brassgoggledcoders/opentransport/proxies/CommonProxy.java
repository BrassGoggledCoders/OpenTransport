package xyz.brassgoggledcoders.opentransport.proxies;

import com.teamacronymcoders.base.util.ClassLoading;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.ITransportType;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.TransportType;
import xyz.brassgoggledcoders.opentransport.api.wrappers.player.EntityPlayerMPWrapper;

import java.util.List;

public class CommonProxy {
    public EntityPlayer getEntityPlayerWrapper(EntityPlayer entityPlayer, IHolderEntity containerHolder) {
        if (entityPlayer instanceof EntityPlayerMP) {
            return new EntityPlayerMPWrapper((EntityPlayerMP) entityPlayer, containerHolder);
        }
        OpenTransport.instance.getLogger()
                .fatal("EntityPlayer(" + entityPlayer.getClass().toString() + ") not Wrapped.");
        return null;
    }

    public World getWorld(MessageContext ctx) {
        return ctx.getServerHandler().playerEntity.getEntityWorld();
    }

    public IThreadListener getIThreadListener(MessageContext messageContext) {
        return (WorldServer) messageContext.getServerHandler().playerEntity.getEntityWorld();
    }

    public List<ITransportType> getTransportTypes(ASMDataTable dataTable) {
        return ClassLoading.getInstances(dataTable, TransportType.class, ITransportType.class);
    }

    public void registerCustomModelLoader() {

    }

    public void registerRenderers(List<ITransportType> transportTypes) {

    }
}
