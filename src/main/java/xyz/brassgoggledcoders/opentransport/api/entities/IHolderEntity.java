package xyz.brassgoggledcoders.opentransport.api.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IBlockContainer;

public interface IHolderEntity<T extends Entity>
{
	T getEntity();

	IBlockContainer getBlockContainer();

	void setBlockContainer(IBlockContainer blockContainer);

	default boolean isUseableByPlayer(EntityPlayer entityPlayer)
	{
		return !this.getEntity().isDead && entityPlayer.getDistanceSqToEntity(this.getEntity()) <= 64.0D;
	}
}
