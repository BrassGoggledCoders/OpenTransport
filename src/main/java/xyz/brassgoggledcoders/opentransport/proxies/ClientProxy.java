package xyz.brassgoggledcoders.opentransport.proxies;

import com.teamacronymcoders.base.util.ClassLoading;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.ClientTransportType;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.IClientTransportType;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.ITransportType;
import xyz.brassgoggledcoders.opentransport.api.wrappers.player.EntityPlayerSPWrapper;
import xyz.brassgoggledcoders.opentransport.renderers.TESRModelLoader;

import java.util.List;

public class ClientProxy extends CommonProxy {
    @Override
    public EntityPlayer getEntityPlayerWrapper(EntityPlayer entityPlayer, IHolderEntity containerHolder) {
        if (entityPlayer instanceof EntityPlayerSP) {
            return new EntityPlayerSPWrapper((EntityPlayerSP) entityPlayer, containerHolder);
        }
        return super.getEntityPlayerWrapper(entityPlayer, containerHolder);
    }

    @Override
    public World getWorld(MessageContext ctx) {
        return Minecraft.getMinecraft().theWorld;
    }

    @Override
    public IThreadListener getIThreadListener(MessageContext messageContext) {
        return Minecraft.getMinecraft();
    }

    @Override
    public List<ITransportType> getTransportTypes(ASMDataTable dataTable) {
        return ClassLoading.getInstances(dataTable, ClientTransportType.class, ITransportType.class);
    }

    @Override
    public void registerCustomModelLoader() {
        TESRModelLoader tesrModelLoader = new TESRModelLoader();
    }

    @Override
    public void registerRenderers(List<ITransportType> transportTypes) {
        transportTypes.forEach(transportType -> {
            if(transportType instanceof IClientTransportType) {
                IClientTransportType clientTransportType = (IClientTransportType)transportType;
                clientTransportType.registerEntityRenderer();
                clientTransportType.registerItemRenderer();
            }
        });
    }
}
