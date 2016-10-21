package xyz.brassgoggledcoders.opentransport.interactions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;

public class BlockActivationInteraction extends BaseInteraction {
	@Override
	public boolean interact(EntityPlayer entityPlayer, EnumHand hand, ItemStack itemStack, IHolderEntity holderEntity,
			IBlockWrapper blockContainer) {
		return blockContainer.getBlock()
				.onBlockActivated(this.getWorldWrapper(holderEntity), BlockPos.ORIGIN, blockContainer.getBlockState(),
						entityPlayer, hand, itemStack, EnumFacing.UP, 0F, 0F, 0F);
	}
}
