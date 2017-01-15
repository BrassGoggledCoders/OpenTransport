package xyz.brassgoggledcoders.opentransport.minecarts;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.ClientTransportType;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.IClientTransportType;
import xyz.brassgoggledcoders.opentransport.minecarts.entities.EntityMinecartHolder;
import xyz.brassgoggledcoders.opentransport.minecarts.renderers.RenderHolderMinecart;
import xyz.brassgoggledcoders.opentransport.minecarts.renderers.RenderItemHolderMinecart;
import xyz.brassgoggledcoders.opentransport.minecarts.renderers.RenderItemHolderMinecart.DummyTile;
import xyz.brassgoggledcoders.opentransport.minecarts.renderers.RenderItemHolderMinecartAccessor;
import xyz.brassgoggledcoders.opentransport.renderers.TESRModel;
import xyz.brassgoggledcoders.opentransport.renderers.TESRModelLoader;

@ClientTransportType
public class MinecartClientTransport extends MinecartTransport implements IClientTransportType<EntityMinecart> {
    @Override
    public void registerEntityRenderer() {
        RenderingRegistry.registerEntityRenderingHandler(EntityMinecartHolder.class, RenderHolderMinecart::new);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void registerItemRenderer() {
        ClientRegistry.bindTileEntitySpecialRenderer(DummyTile.class, new RenderItemHolderMinecart());
        RenderItemHolderMinecartAccessor accessor = new RenderItemHolderMinecartAccessor();
        this.holderList.forEach(itemMinecartHolder -> {
            ForgeHooksClient.registerTESRItemStack(itemMinecartHolder, 0, DummyTile.class);
            TESRModelLoader.addTESRModel(itemMinecartHolder, new TESRModel<>(itemMinecartHolder, accessor));
        });
    }
}
