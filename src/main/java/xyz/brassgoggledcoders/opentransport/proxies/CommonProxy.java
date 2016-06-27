package xyz.brassgoggledcoders.opentransport.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.wrappers.player.EntityPlayerMPWrapper;

public class CommonProxy {
	public EntityPlayer getEntityPlayerWrapper(EntityPlayer entityPlayer, IHolderEntity containerHolder) {
		if(entityPlayer instanceof EntityPlayerMP) {
			return new EntityPlayerMPWrapper((EntityPlayerMP) entityPlayer, containerHolder);
		}
		OpenTransport.INSTANCE.getLogger()
				.fatal("EntityPlayer(" + entityPlayer.getClass().toString() + ") not Wrapped.");
		return null;
	}

	public void resetPlayer(EntityPlayer player) {
		if(player instanceof EntityPlayerMP) {
			((EntityPlayerMP) player).interactionManager.thisPlayerMP = (EntityPlayerMP) player;
		}
	}

	public World getWorld(MessageContext ctx) {
		return ctx.getServerHandler().playerEntity.getEntityWorld();
	}

	public IThreadListener getIThreadListener(MessageContext messageContext) {
		return (WorldServer) messageContext.getServerHandler().playerEntity.getEntityWorld();
	}

	public void registerEntityRenders() {

	}
}
