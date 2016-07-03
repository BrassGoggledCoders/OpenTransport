package xyz.brassgoggledcoders.opentransport.api.navigation.cargoslip;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import xyz.brassgoggledcoders.opentransport.api.navigation.navpoint.INavPoint;
import xyz.brassgoggledcoders.opentransport.api.navigation.navpoint.NavPointHandler;

import java.util.HashMap;
import java.util.Map;

public class CapabilityCargoSlip {
	@CapabilityInject(ICargoSlip.class)
	public static Capability<ICargoSlip> CARGO_SLIP_CAP;

	public static void register() {
		CapabilityManager.INSTANCE.register(ICargoSlip.class, new Capability.IStorage<ICargoSlip>() {
			@Override
			public NBTBase writeNBT(Capability<ICargoSlip> capability, ICargoSlip instance, EnumFacing side) {
				NBTTagCompound nbtTagCompound = new NBTTagCompound();
				Map<Integer, INavPoint> navPoints = instance.getAllNavPoints();
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
			public void readNBT(Capability<ICargoSlip> capability, ICargoSlip instance, EnumFacing side, NBTBase nbt) {
				NBTTagCompound nbtTagCompound = (NBTTagCompound)nbt;
				if(nbtTagCompound.hasKey("NAV_POINTS")) {
					HashMap<Integer, INavPoint> navPoints = new HashMap<>();
					NBTTagList navPointsNBT = nbtTagCompound.getTagList("NAV_POINTS", 9);
					for(int i = 0; i < navPointsNBT.tagCount(); i++) {
						INavPoint navPoint = new NavPointHandler();
						navPoint.deserializeNBT((NBTTagCompound)navPointsNBT.get(i));
						navPoints.put(i, navPoint);
					}
					instance.setNavPoints(navPoints);
				}
			}
		}, CargoSlipHandler::new);
	}
}
