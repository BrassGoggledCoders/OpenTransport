package xyz.brassgoggledcoders.moarlibs.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xyz.brassgoggledcoders.moarlibs.MoarLibs;
import xyz.brassgoggledcoders.moarlibs.api.IHolderEntity;
import xyz.brassgoggledcoders.moarlibs.wrappers.player.EntityPlayerMPWrapper;

public class CommonProxy
{
	public EntityPlayer getEntityPlayerWrapper(EntityPlayer entityPlayer, IHolderEntity containerHolder)
	{
		if(entityPlayer instanceof EntityPlayerMP)
		{
			return new EntityPlayerMPWrapper((EntityPlayerMP)entityPlayer, containerHolder);
		}
		MoarLibs.INSTANCE.getLogger().fatal("EntityPlayer(" + entityPlayer.getClass().toString() + ") not Wrapped.");
		return null;
	}

	public World getWorld(MessageContext ctx)
	{
		return ctx.getServerHandler().playerEntity.getEntityWorld();
	}

	public IThreadListener getIThreadListener(MessageContext messageContext)
	{
		return (WorldServer)messageContext.getServerHandler().playerEntity.getEntityWorld();
	}
}
