package xyz.brassgoggledcoders.opentransport.modules.boats.navigation.tiles;

import net.minecraft.nbt.NBTTagCompound;
import xyz.brassgoggledcoders.boilerplate.tileentities.TileEntityBase;

public class TileEntityBuoy extends TileEntityBase {
	private boolean bottom;

	public TileEntityBuoy(boolean bottom) {
		this.setBottom(bottom);
	}

	@Override
	public void readFromNBTCustom(NBTTagCompound nbtTagCompound) {

	}

	@Override
	public NBTTagCompound writeToNBTCustom(NBTTagCompound nbtTagCompound) {
		return nbtTagCompound;
	}

	public boolean isBottom() {
		return bottom;
	}

	public void setBottom(boolean bottom) {
		this.bottom = bottom;
	}
}
