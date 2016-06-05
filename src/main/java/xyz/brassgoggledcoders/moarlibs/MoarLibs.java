package xyz.brassgoggledcoders.moarlibs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.BoilerplateModBase;

@Mod(modid = MoarLibs.MODID, name = MoarLibs.MODNAME, version = MoarLibs.VERSION)
public class MoarLibs extends BoilerplateModBase
{
	public static final String MODID = "moarlibs";
	public static final String MODNAME = "MoarLibs";
	public static final String VERSION = "@VERSION@";

	@Instance(MoarLibs.MODID)
	public static MoarLibs INSTANCE;

	public MoarLibs()
	{
		super(MODID, MODNAME, VERSION, CreativeTabs.MISC);
	}

	@Override
	protected void modPreInit(FMLPreInitializationEvent event)
	{

	}

	@Override
	protected void modInit(FMLInitializationEvent event)
	{

	}

	@Override
	protected void modPostInit(FMLPostInitializationEvent event)
	{

	}

	@Override
	public Object getInstance()
	{
		return INSTANCE;
	}
}
