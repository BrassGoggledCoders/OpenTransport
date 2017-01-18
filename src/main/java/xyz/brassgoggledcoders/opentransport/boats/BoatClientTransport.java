package xyz.brassgoggledcoders.opentransport.boats;

import net.minecraft.entity.item.EntityBoat;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.ClientTransportType;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.IClientTransportType;
import xyz.brassgoggledcoders.opentransport.boats.entities.EntityBoatHolder;
import xyz.brassgoggledcoders.opentransport.boats.renderers.RenderHolderBoat;
import xyz.brassgoggledcoders.opentransport.boats.renderers.RenderItemHolderBoat;
import xyz.brassgoggledcoders.opentransport.boats.renderers.RenderItemHolderBoatAccessor;
import xyz.brassgoggledcoders.opentransport.renderers.TESRModel;
import xyz.brassgoggledcoders.opentransport.renderers.TESRModelLoader;

import java.util.ArrayList;

@ClientTransportType
public class BoatClientTransport extends BoatTransport implements IClientTransportType<EntityBoat> {
    @Override
    public void registerEntityRenderer() {
        RenderingRegistry.registerEntityRenderingHandler(EntityBoatHolder.class, RenderHolderBoat::new);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void registerItemRenderer() {
        ClientRegistry.bindTileEntitySpecialRenderer(RenderItemHolderBoat.DummyTile.class, new RenderItemHolderBoat());
        RenderItemHolderBoatAccessor accessor = new RenderItemHolderBoatAccessor();
        itemBoatHolder.getAllSubItems(new ArrayList<>()).forEach(itemStack ->
                ForgeHooksClient.registerTESRItemStack(itemBoatHolder, itemStack.getMetadata(), RenderItemHolderBoat.DummyTile.class));
        TESRModelLoader.addTESRModel(itemBoatHolder, new TESRModel<>(itemBoatHolder, accessor));
    }
}
