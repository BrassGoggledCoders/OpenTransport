package xyz.brassgoggledcoders.opentransport.module;

import com.teamacronymcoders.base.modulesystem.ModuleBase;
import com.teamacronymcoders.base.util.TextUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.block.CarriedBlock;

import java.util.List;

public abstract class OTModuleBase extends ModuleBase {
    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void registerCarriedBlocks(RegistryEvent.Register<CarriedBlock> carriedBlockRegistryEvent) {
        CarriedBlock[] carriedBlocks = this.getCarriedBlocks().stream()
                .map(this::setRegistryName)
                .toArray(CarriedBlock[]::new);
        carriedBlockRegistryEvent.getRegistry().registerAll(carriedBlocks);
    }

    private CarriedBlock setRegistryName(CarriedBlock carriedBlock) {
        String name = TextUtils.toSnakeCase(TextUtils.getRegistryLocation(carriedBlock.getBlock()));
        carriedBlock.setRegistryName(new ResourceLocation(OpenTransport.MODID, name));
        return carriedBlock;
    }

    public abstract List<CarriedBlock> getCarriedBlocks();
}
