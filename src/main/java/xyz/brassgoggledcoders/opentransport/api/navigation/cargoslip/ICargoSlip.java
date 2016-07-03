package xyz.brassgoggledcoders.opentransport.api.navigation.cargoslip;

import xyz.brassgoggledcoders.opentransport.api.navigation.navpoint.INavPoint;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Map;

public interface ICargoSlip {
	void addNavPoint(@Nonnull INavPoint iNavPoint);

	void removeLastNavPoint();

	void setNavPoints(@Nonnull Map<Integer, INavPoint> navPoints);

	@Nonnull
	Map<Integer, INavPoint> getAllNavPoints();

	void setSlipName(@Nullable String name);

	@Nullable
	String getSlipName();
}
