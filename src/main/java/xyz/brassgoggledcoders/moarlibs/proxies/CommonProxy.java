package xyz.brassgoggledcoders.moarlibs.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import xyz.brassgoggledcoders.moarlibs.MoarLibs;
import xyz.brassgoggledcoders.moarlibs.api.IHolderEntity;
import xyz.brassgoggledcoders.moarlibs.wrappers.players.EntityPlayerMPWrapper;

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
}
