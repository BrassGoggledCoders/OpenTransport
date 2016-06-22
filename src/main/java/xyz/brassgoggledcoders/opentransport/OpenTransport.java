package xyz.brassgoggledcoders.opentransport;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.BoilerplateModBase;
import xyz.brassgoggledcoders.opentransport.proxies.CommonProxy;

@Mod(modid = OpenTransport.MODID, name = OpenTransport.MODNAME, version = OpenTransport.VERSION, dependencies = OpenTransport.DEPENDENCIES)
public class OpenTransport extends BoilerplateModBase
{
	public static final String MODID = "opentransport";
	public static final String MODNAME = "OpenTransport";
	public static final String VERSION = "@VERSION@";
	public static final String DEPENDENCIES = "after:IronChest;";

	@Instance(OpenTransport.MODID)
	public static OpenTransport INSTANCE;

	@SidedProxy(clientSide = "xyz.brassgoggledcoders.opentransport.proxies.ClientProxy",
			serverSide = "xyz.brassgoggledcoders.opentransport.proxies.CommonProxy")
	public static CommonProxy proxy;

	public OpenTransport()
	{
		super(MODID, MODNAME, VERSION, CreativeTabs.MISC);
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		super.postInit(event);
	}

	@Override
	public Object getInstance()
	{
		return INSTANCE;
	}
}
