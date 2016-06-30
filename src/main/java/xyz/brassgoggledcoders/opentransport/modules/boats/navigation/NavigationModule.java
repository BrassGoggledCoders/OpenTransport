package xyz.brassgoggledcoders.opentransport.modules.boats.navigation;

import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.module.Module;
import xyz.brassgoggledcoders.boilerplate.module.ModuleBase;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.modules.boats.navigation.blocks.BlockDock;
import xyz.brassgoggledcoders.opentransport.modules.boats.navigation.entities.EntityBuoy;
import xyz.brassgoggledcoders.opentransport.modules.boats.navigation.items.ItemBuoy;

@Module(mod = OpenTransport.MODID)
public class NavigationModule extends ModuleBase {

	@Override
	public String getName() {
		return "Navigation";
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		this.getEntityRegistry().registerEntity(EntityBuoy.class);
		this.getItemRegistry().registerItem(new ItemBuoy());
		this.getBlockRegistry().registerBlock(new BlockDock());
	}
}
