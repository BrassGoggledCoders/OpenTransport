package xyz.brassgoggledcoders.moarlibs.api;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IHolderItem<T extends Item>
{
	T getItem();

	IBlockContainer getBlockContainer(ItemStack itemStack);

	void setBlockContainer(ItemStack itemStack, IBlockContainer blockContainer);
}
