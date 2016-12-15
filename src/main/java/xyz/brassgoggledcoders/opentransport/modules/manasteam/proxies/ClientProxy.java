package xyz.brassgoggledcoders.opentransport.modules.manasteam.proxies;

import com.teamacronymcoders.base.modulesystem.proxies.ModuleProxyBase;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.opentransport.modules.manasteam.entities.EntityManaSteamLocomotive;
import xyz.brassgoggledcoders.opentransport.modules.manasteam.renderers.ManaSteamLocomotiveRenderer;

public class ClientProxy extends ModuleProxyBase {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(EntityManaSteamLocomotive.class, ManaSteamLocomotiveRenderer::new);
    }
}
