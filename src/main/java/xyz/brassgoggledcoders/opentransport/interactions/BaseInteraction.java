package xyz.brassgoggledcoders.opentransport.interactions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IBlockContainer;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IInteraction;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.wrappers.world.WorldWrapper;

public class BaseInteraction implements IInteraction {
	@Override
	public boolean interact(EntityPlayer entityPlayer, EnumHand hand, ItemStack itemStack, IHolderEntity holderEntity,
			IBlockContainer blockContainer) {
		return false;
	}

	protected WorldWrapper getWorldWrapper(IHolderEntity holderEntity) {
		return new WorldWrapper(holderEntity);
	}
}
