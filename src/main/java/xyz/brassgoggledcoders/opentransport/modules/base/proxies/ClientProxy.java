package xyz.brassgoggledcoders.opentransport.modules.base.proxies;

import com.teamacronymcoders.base.modulesystem.proxies.ModuleProxyBase;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.opentransport.modules.base.entities.EntityMaterialMinecart;
import xyz.brassgoggledcoders.opentransport.modules.base.renderers.RenderMaterialMinecart;

public class ClientProxy extends ModuleProxyBase {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityMaterialMinecart.class, RenderMaterialMinecart::new);
    }
}
