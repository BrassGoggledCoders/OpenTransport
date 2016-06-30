package xyz.brassgoggledcoders.opentransport.modules.boats.navigation;

import xyz.brassgoggledcoders.boilerplate.module.Module;
import xyz.brassgoggledcoders.boilerplate.module.ModuleBase;
import xyz.brassgoggledcoders.opentransport.OpenTransport;

@Module(mod = OpenTransport.MODID)
public class NavigationModule extends ModuleBase {

	@Override
	public String getName() {
		return "Navigation";
	}
}
