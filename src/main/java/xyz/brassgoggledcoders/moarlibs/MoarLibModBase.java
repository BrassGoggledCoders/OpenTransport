package xyz.brassgoggledcoders.moarlibs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import xyz.brassgoggledcoders.boilerplate.BoilerplateModBase;
import xyz.brassgoggledcoders.moarlibs.api.IMoarRegister;
import xyz.brassgoggledcoders.moarlibs.network.HolderUpdatePacket;
import xyz.brassgoggledcoders.moarlibs.registries.BlockContainerRegistry;

public abstract class MoarLibModBase extends BoilerplateModBase
{
	public MoarLibModBase(String modid, String name, String version, CreativeTabs creativeTab)
	{
		super(modid, name, version, creativeTab);
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
		this.getPacketHandler().registerPacket(HolderUpdatePacket.Handler.class,
			HolderUpdatePacket.class, Side.CLIENT);
	}

	@Override
	protected void afterModuleConstruct(FMLPreInitializationEvent event)
	{
		MoarLibs.INSTANCE.createHandlers(event);
		this.getMoarRegister().registerItems(BlockContainerRegistry.getAllBlockContainers());
		this.getMoarRegister().registerEntities();
	}

	public abstract IMoarRegister getMoarRegister();
}
