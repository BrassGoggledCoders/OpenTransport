package xyz.brassgoggledcoders.opentransport.api.navigation.cargoslip;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class CapProviderCargoSlip implements ICapabilityProvider {
	private ICargoSlip cargoSlip;

	public CapProviderCargoSlip() {
		this(null);
	}

	public CapProviderCargoSlip(NBTTagCompound nbtTagCompound) {
		cargoSlip = new CargoSlipHandler();
		if(nbtTagCompound != null) {
			cargoSlip.deserializeNBT(nbtTagCompound);
		}
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CapabilityCargoSlip.CARGO_SLIP_CAP;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CapabilityCargoSlip.CARGO_SLIP_CAP ? (T) cargoSlip : null;
	}
}
