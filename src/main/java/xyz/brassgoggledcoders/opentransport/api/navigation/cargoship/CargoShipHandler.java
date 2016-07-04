package xyz.brassgoggledcoders.opentransport.api.navigation.cargoship;

import net.minecraft.nbt.NBTTagCompound;
import xyz.brassgoggledcoders.opentransport.api.navigation.cargoslip.CargoSlipHandler;
import xyz.brassgoggledcoders.opentransport.api.navigation.cargoslip.ICargoSlip;
import xyz.brassgoggledcoders.opentransport.api.navigation.navpoint.INavPoint;
import xyz.brassgoggledcoders.opentransport.api.navigation.navpoint.NavPointHandler;

import javax.annotation.Nullable;

public class CargoShipHandler implements ICargoShip {
	private ICargoSlip cargoSlip;
	private INavPoint navPoint;
	private int currentIndex;

	@Override
	public void setCargoSlip(@Nullable ICargoSlip cargoSlip) {
		this.cargoSlip = cargoSlip;
		this.currentIndex = 0;
		if(this.cargoSlip != null) {
			this.navPoint = cargoSlip.getAllNavPoints().get(currentIndex);
		} else {
			this.navPoint = null;
		}
	}

	@Nullable
	@Override
	public ICargoSlip getCargoSlip() {
		return this.cargoSlip;
	}

	@Override
	public void reachedCurrentNavPoint() {
		if(cargoSlip != null) {
			this.currentIndex++;
			if(this.currentIndex >= cargoSlip.getAllNavPoints().size()) {
				this.currentIndex = 0;
			}
			this.navPoint = this.cargoSlip.getAllNavPoints().get(this.currentIndex);
		}
	}

	@Nullable
	@Override
	public INavPoint getCurrentNavPoint() {
		return this.navPoint;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		if(this.cargoSlip != null) {
			nbtTagCompound.setTag("CARGO_SLIP", this.cargoSlip.serializeNBT());
		}
		if(this.navPoint != null) {
			nbtTagCompound.setTag("NAV_POINT", this.navPoint.serializeNBT());
		}
		nbtTagCompound.setInteger("INDEX", currentIndex);
		return nbtTagCompound;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbtTagCompound) {
		if(nbtTagCompound.hasKey("CARGO_SLIP")) {
			this.cargoSlip = new CargoSlipHandler();
			this.cargoSlip.deserializeNBT(nbtTagCompound.getCompoundTag("CARGO_SLIP"));
		}
		if(nbtTagCompound.hasKey("NAV_POINT")) {
			this.navPoint = new NavPointHandler();
			this.navPoint.deserializeNBT(nbtTagCompound.getCompoundTag("NAV_POINT"));
		}
		currentIndex = nbtTagCompound.getInteger("INDEX");
	}
}
