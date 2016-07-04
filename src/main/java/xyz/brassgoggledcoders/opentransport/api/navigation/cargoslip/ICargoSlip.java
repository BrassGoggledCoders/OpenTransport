package xyz.brassgoggledcoders.opentransport.api.navigation.cargoslip;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import xyz.brassgoggledcoders.opentransport.api.navigation.navpoint.INavPoint;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public interface ICargoSlip extends INBTSerializable<NBTTagCompound> {
	void addNavPoint(@Nonnull INavPoint iNavPoint);

	void removeLastNavPoint();

	void setNavPoints(@Nonnull Map<Integer, INavPoint> navPoints);

	@Nonnull
	Map<Integer, INavPoint> getAllNavPoints();

	void setSlipName(@Nullable String name);

	@Nullable
	String getSlipName();
}
