package xyz.brassgoggledcoders.opentransport.proxies;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.entities.boats.EntityBoatHolder;
import xyz.brassgoggledcoders.opentransport.renderers.boats.RenderHolderBoat;
import xyz.brassgoggledcoders.opentransport.wrappers.player.EntityPlayerSPWrapper;

public class ClientProxy extends CommonProxy
{
	@Override
	public EntityPlayer getEntityPlayerWrapper(EntityPlayer entityPlayer, IHolderEntity containerHolder)
	{
		if(entityPlayer instanceof EntityPlayerSP)
		{
			return new EntityPlayerSPWrapper((EntityPlayerSP)entityPlayer, containerHolder);
		}
		return super.getEntityPlayerWrapper(entityPlayer, containerHolder);
	}

	@Override
	public void resetPlayer(EntityPlayer player)
	{

	}

	@Override
	public World getWorld(MessageContext ctx)
	{
		return Minecraft.getMinecraft().theWorld;
	}

	@Override
	public IThreadListener getIThreadListener(MessageContext messageContext)
	{
		return Minecraft.getMinecraft();
	}

	@Override
	public void registerEntityRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityBoatHolder.class, RenderHolderBoat.Factory.INSTANCE);
	}
}