package xyz.brassgoggledcoders.opentransport.api.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IBlockContainer;

public interface IHolderItem<T extends Item>
{
	T getItem();

	IBlockContainer getBlockContainer(ItemStack itemStack);

	void setBlockContainer(ItemStack itemStack, IBlockContainer blockContainer);
}
