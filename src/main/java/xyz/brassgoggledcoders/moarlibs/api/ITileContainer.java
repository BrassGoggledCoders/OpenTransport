package xyz.brassgoggledcoders.moarlibs.api;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public interface ITileContainer<T extends TileEntity>
{
	boolean shouldUpdate();

	void update();

	T getTileEntity();

	NBTTagCompound writeToNBT(NBTTagCompound tagCompound);

	void readFromNBT(NBTTagCompound tagCompound);
}
