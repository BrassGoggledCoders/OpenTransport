package xyz.brassgoggledcoders.opentransport.proxies;

import com.teamacronymcoders.base.client.ClientHelper;
import com.teamacronymcoders.base.util.ClassLoading;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.OpenTransportAPI;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.ClientTransportType;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.IClientTransportType;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.ITransportType;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.wrappers.player.EntityPlayerSPWrapper;
import xyz.brassgoggledcoders.opentransport.api.wrappers.world.WorldHarnessRenderItem;
import xyz.brassgoggledcoders.opentransport.renderers.TESRModelLoader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public EntityPlayer getEntityPlayerWrapper(EntityPlayer entityPlayer, IHolderEntity containerHolder) {
        if (entityPlayer instanceof EntityPlayerSP) {
            return new EntityPlayerSPWrapper((EntityPlayerSP) entityPlayer, containerHolder);
        }
        OpenTransport.instance.getLogger()
                .fatal("EntityPlayer(" + entityPlayer.getClass().toString() + ") not Wrapped.");
        return null;
    }

    @Override
    public World getWorld(MessageContext ctx) {
        return ClientHelper.world();
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
            if (transportType instanceof IClientTransportType) {
                IClientTransportType clientTransportType = (IClientTransportType) transportType;
                clientTransportType.registerEntityRenderer();
                clientTransportType.registerItemRenderer();
            }
        });
    }

    @Override
    public void setWorldHarness(IBlockWrapper blockWrapper) {
        blockWrapper.setWorldHarness(new WorldHarnessRenderItem(blockWrapper));
    }

    private Map<String, IBlockWrapper> loadedBlockWrapperMap = new HashMap<>();

    @Override
    public IBlockWrapper getLoadedBlockWrapper(String name) {
        IBlockWrapper blockWrapper;
        if (ClientHelper.world() != null) {
            if (loadedBlockWrapperMap.containsKey(name)) {
                blockWrapper = loadedBlockWrapperMap.get(name);
            } else {
                blockWrapper = OpenTransportAPI.getBlockWrapperRegistry().getBlockWrapper(name);
                blockWrapper.setWorldHarness(new WorldHarnessRenderItem(blockWrapper));
                loadedBlockWrapperMap.put(name, blockWrapper);
            }
        } else {
            blockWrapper = OpenTransportAPI.getBlockWrapperRegistry().getBlockWrapper(name);
        }
        return blockWrapper;
    }
}
