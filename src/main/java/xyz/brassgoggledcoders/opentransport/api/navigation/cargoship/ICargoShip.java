package xyz.brassgoggledcoders.opentransport.api.navigation.cargoship;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import xyz.brassgoggledcoders.opentransport.api.navigation.cargoslip.ICargoSlip;
import xyz.brassgoggledcoders.opentransport.api.navigation.navpoint.INavPoint;

import javax.annotation.Nullable;

public interface ICargoShip extends INBTSerializable<NBTTagCompound> {
	void setCargoSlip(@Nullable ICargoSlip cargoSlip);

	@Nullable
	ICargoSlip getCargoSlip();

	void reachedCurrentNavPoint();

	@Nullable
	INavPoint getCurrentNavPoint();
}
