package xyz.brassgoggledcoders.opentransport.api.navigation.cargoslip;

import xyz.brassgoggledcoders.opentransport.api.navigation.navpoint.INavPoint;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class CargoSlipHandler implements ICargoSlip {
	private Map<Integer, INavPoint> navPoints;
	private String slipName;
	public CargoSlipHandler() {
		navPoints = new HashMap<>();
	}

	@Override
	public void addNavPoint(@Nonnull INavPoint navPoint) {
		 navPoints.put(navPoints.size(), navPoint);
	}

	@Override
	public void removeLastNavPoint() {
		navPoints.remove(navPoints.size() - 1);
	}

	@Override
	public void setNavPoints(@Nonnull Map<Integer, INavPoint> navPoints) {
		this.navPoints = navPoints;
	}

	@Override
	@Nonnull
	public Map<Integer, INavPoint> getAllNavPoints() {
		return this.navPoints;
	}

	@Override
	public void setSlipName(@Nullable String name) {
		this.slipName = name;
	}

	@Nullable
	@Override
	public String getSlipName() {
		return this.slipName;
	}
}
