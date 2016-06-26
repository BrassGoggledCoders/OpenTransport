package xyz.brassgoggledcoders.opentransport.modules;

import cpw.mods.ironchest.BlockIronChest;
import cpw.mods.ironchest.IronChest;
import cpw.mods.ironchest.IronChestType;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import xyz.brassgoggledcoders.boilerplate.module.Module;
import xyz.brassgoggledcoders.boilerplate.module.ModuleBase;
import xyz.brassgoggledcoders.boilerplate.module.dependencies.IDependency;
import xyz.brassgoggledcoders.boilerplate.module.dependencies.ModDependency;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IBlockContainer;
import xyz.brassgoggledcoders.opentransport.blocks.BlockContainerBase;
import xyz.brassgoggledcoders.opentransport.registries.BlockContainerRegistry;
import xyz.brassgoggledcoders.opentransport.renderers.RenderType;

import java.util.List;

@Module(mod = OpenTransport.MODID)
public class IronChestModule extends ModuleBase
{
	public static IBlockContainer[] IRON_CHESTS;

	@Override
	public String getName()
	{
		return "Iron Chest";
	}

	@Override
	public List<IDependency> getDependencies(List<IDependency> dependencies)
	{
		dependencies.add(new ModDependency("IronChest"));
		return dependencies;
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
