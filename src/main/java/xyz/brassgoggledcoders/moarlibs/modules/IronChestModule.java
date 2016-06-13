package xyz.brassgoggledcoders.moarlibs.modules;

import cpw.mods.ironchest.BlockIronChest;
import cpw.mods.ironchest.IronChest;
import cpw.mods.ironchest.IronChestType;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.module.Module;
import xyz.brassgoggledcoders.boilerplate.module.ModuleBase;
import xyz.brassgoggledcoders.moarlibs.MoarLibs;
import xyz.brassgoggledcoders.moarlibs.api.IBlockContainer;
import xyz.brassgoggledcoders.moarlibs.blocks.BlockContainerBase;
import xyz.brassgoggledcoders.moarlibs.registries.BlockContainerRegistry;
import xyz.brassgoggledcoders.moarlibs.renderers.RenderType;

@Module(mod = MoarLibs.MODID)
public class IronChestModule extends ModuleBase
{
	public static IBlockContainer[] IRON_CHESTS;

	@Override
	public String getName()
	{
		return "Iron Chest";
	}

	@Override
	public boolean areDependenciesMet()
	{
		return Loader.isModLoaded("IronChest");
	}

	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		IRON_CHESTS = new IBlockContainer[IronChestType.values().length];
		for(int i = 0; i < IRON_CHESTS.length; i++)
		{
			IRON_CHESTS[i] = new BlockContainerBase(IronChest.ironChestBlock).setRenderType(RenderType.TESR)
					.setBlockState(IronChest.ironChestBlock.getDefaultState().withProperty(BlockIronChest.VARIANT_PROP,
							IronChestType.values()[i]));
			BlockContainerRegistry.registerContainer(IRON_CHESTS[i]);
		}
	}
}
