package xyz.brassgoggledcoders.moarlibs.wrappers.players;

import net.minecraft.entity.player.EntityPlayerMP;
import xyz.brassgoggledcoders.moarlibs.api.IBlockContainer;

public class EntityPlayerMPWrapper extends EntityPlayerMP
{
	protected EntityPlayerMP entityPlayer;
	protected IBlockContainer blockContainer;

	public EntityPlayerMPWrapper(EntityPlayerMP entityPlayer, IBlockContainer blockContainer)
	{
		super(entityPlayer.mcServer, entityPlayer.getServerWorld(), entityPlayer.getGameProfile(),
				entityPlayer.interactionManager);
		this.entityPlayer = entityPlayer;
		this.blockContainer = blockContainer;
	}
}
