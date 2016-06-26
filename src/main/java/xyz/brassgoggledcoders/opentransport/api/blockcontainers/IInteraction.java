package xyz.brassgoggledcoders.opentransport.api.blockcontainers;

import net.minecraft.entity.player.EntityPlayer;

public interface IInteraction
{
	boolean interact(EntityPlayer entityPlayer, IBlockContainer blockContainer);
}
