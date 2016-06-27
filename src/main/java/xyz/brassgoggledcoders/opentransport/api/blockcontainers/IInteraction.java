package xyz.brassgoggledcoders.opentransport.api.blockcontainers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

public interface IInteraction {
	boolean interact(EntityPlayer entityPlayer, EnumHand hand, ItemStack itemStack, IHolderEntity holderEntity,
			IBlockContainer blockContainer);
}
