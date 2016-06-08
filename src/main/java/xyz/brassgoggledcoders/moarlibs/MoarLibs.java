package xyz.brassgoggledcoders.moarlibs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.BoilerplateModBase;
import xyz.brassgoggledcoders.moarlibs.proxies.CommonProxy;

@Mod(modid = MoarLibs.MODID, name = MoarLibs.MODNAME, version = MoarLibs.VERSION)
public class MoarLibs extends BoilerplateModBase
{
	public static final String MODID = "moarlibs";
	public static final String MODNAME = "MoarLibs";
	public static final String VERSION = "@VERSION@";

	@Instance(MoarLibs.MODID)
	public static MoarLibs INSTANCE;

	@SidedProxy(clientSide = "xyz.brassgoggledcoders.moarlibs.proxies.ClientProxy",
			serverSide = "xyz.brassgoggledcoders.moarlibs.proxies.CommonProxy")
	public static CommonProxy proxy;

	public MoarLibs()
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
