package xyz.brassgoggledcoders.opentransport.api.navigation.navpoint;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityNavPoint {
	@CapabilityInject(INavPoint.class)
	public static Capability<INavPoint> NAV_POINT_CAP;

	public static void register() {
		CapabilityManager.INSTANCE.register(INavPoint.class, new Capability.IStorage<INavPoint>() {
			@Override
			public NBTBase writeNBT(Capability<INavPoint> capability, INavPoint instance, EnumFacing side) {
				return instance.serializeNBT();
			}

			@Override
			public void readNBT(Capability<INavPoint> capability, INavPoint instance, EnumFacing side, NBTBase nbt) {
				NBTTagCompound nbtTagCompound = (NBTTagCompound) nbt;
				instance.deserializeNBT(nbtTagCompound);
			}
		}, NavPointHandler::new);
	}
}
