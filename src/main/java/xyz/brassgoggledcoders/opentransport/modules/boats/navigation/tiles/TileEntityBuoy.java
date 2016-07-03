package xyz.brassgoggledcoders.opentransport.modules.boats.navigation.tiles;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import xyz.brassgoggledcoders.boilerplate.tileentities.TileEntityBase;
import xyz.brassgoggledcoders.opentransport.api.navigation.navpoint.CapabilityNavPoint;
import xyz.brassgoggledcoders.opentransport.api.navigation.navpoint.INavPoint;
import xyz.brassgoggledcoders.opentransport.api.navigation.navpoint.NavPointHandler;

import javax.annotation.Nonnull;

public class TileEntityBuoy extends TileEntityBase {
	private boolean bottom;
	private INavPoint navPoint;

	public TileEntityBuoy(boolean bottom) {
		this.setBottom(bottom);
		if(bottom) {
			this.navPoint = new NavPointHandler(this.getPos());
		}
	}

	@Override
	public void readFromNBTCustom(NBTTagCompound nbtTagCompound) {
		this.setBottom(nbtTagCompound.getBoolean("BOTTOM"));
	}

	@Override
	public NBTTagCompound writeToNBTCustom(NBTTagCompound nbtTagCompound) {
		nbtTagCompound.setBoolean("BOTTOM", this.isBottom());
		if(this.navPoint != null) {
			nbtTagCompound.setTag("NAV_POINT", this.navPoint.serializeNBT());
		}
		return nbtTagCompound;
	}

	public boolean isBottom() {
		return bottom;
	}

	public void setBottom(boolean bottom) {
		this.bottom = bottom;
	}

	public INavPoint getNavPoint() {
		return this.navPoint;
	}

	public TileEntityBuoy getOtherTile() {
		BlockPos otherBuoyPos = this.isBottom() ? this.getPos().up() : this.getPos().down();
		TileEntity tileEntity = this.getWorld().getTileEntity(otherBuoyPos);
		return tileEntity instanceof TileEntityBuoy ? (TileEntityBuoy)tileEntity : null;
	}

	@Override
	public boolean hasCapability(@Nonnull Capability<?> capability, @Nonnull EnumFacing facing) {
		return capability == CapabilityNavPoint.NAV_POINT_CAP || super.hasCapability(capability, facing);
	}

	@Override
	@Nonnull
	public <T> T getCapability(@Nonnull Capability<T> capability, @Nonnull EnumFacing facing) {
		if(capability == CapabilityNavPoint.NAV_POINT_CAP) {
			INavPoint returnNavPoint = this.getNavPoint();
			if(bottom) {
				TileEntityBuoy tileEntityBuoy = this.getOtherTile();
				if(tileEntityBuoy != null) {
					returnNavPoint = tileEntityBuoy.getNavPoint();
				}
			}
			if(returnNavPoint != null) {
				return (T) returnNavPoint;
			}
		}
		return super.getCapability(capability, facing);
	}
}
