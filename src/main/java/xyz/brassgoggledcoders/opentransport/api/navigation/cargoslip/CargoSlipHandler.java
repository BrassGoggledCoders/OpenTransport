package xyz.brassgoggledcoders.opentransport.api.navigation.cargoslip;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import xyz.brassgoggledcoders.opentransport.api.navigation.navpoint.INavPoint;
import xyz.brassgoggledcoders.opentransport.api.navigation.navpoint.NavPointHandler;

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
		if(!navPoints.get(navPoints.size()).equals(navPoint)) {
			navPoints.put(navPoints.size(), navPoint);
		}
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

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		if(this.getSlipName() != null) {
			nbtTagCompound.setString("SLIP_NAME", this.getSlipName());
		}
		Map<Integer, INavPoint> navPoints = this.getAllNavPoints();
		if(navPoints.size() > 0) {
			NBTTagList navPointsListNBT = new NBTTagList();
			for(Map.Entry<Integer, INavPoint> navPoint: navPoints.entrySet()) {
				navPointsListNBT.set(navPoint.getKey(), navPoint.getValue().serializeNBT());
			}
			nbtTagCompound.setTag("NAV_POINTS", navPointsListNBT);
		}

		return nbtTagCompound;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbtTagCompound) {
		if(nbtTagCompound.hasKey("SLIP_NAME")) {
			this.setSlipName(nbtTagCompound.getString("SLIP_NAME"));
		}
		if(nbtTagCompound.hasKey("NAV_POINTS")) {
			HashMap<Integer, INavPoint> navPoints = new HashMap<>();
			NBTTagList navPointsNBT = nbtTagCompound.getTagList("NAV_POINTS", 9);
			for(int i = 0; i < navPointsNBT.tagCount(); i++) {
				INavPoint navPoint = new NavPointHandler();
				navPoint.deserializeNBT((NBTTagCompound)navPointsNBT.get(i));
				navPoints.put(i, navPoint);
			}
			this.setNavPoints(navPoints);
		}
	}
}
