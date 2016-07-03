package xyz.brassgoggledcoders.opentransport.api.navigation.navpoint;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityNavPoint {
	@CapabilityInject(INavPoint.class)
	public static Capability<INavPoint> NAV_POINT_CAPABILITY;

	public static void register() {
		CapabilityManager.INSTANCE.register(INavPoint.class, new Capability.IStorage<INavPoint>() {
			@Override
			public NBTBase writeNBT(Capability<INavPoint> capability, INavPoint instance, EnumFacing side) {
				NBTTagCompound nbtTagCompound = new NBTTagCompound();
				if(instance.getNavName() != null) {
					nbtTagCompound.setString("NAV_NAME", instance.getNavName());
				}
				nbtTagCompound.setLong("NAV_POSITION", instance.getNavPosition().toLong());
				return null;
			}

			@Override
			public void readNBT(Capability<INavPoint> capability, INavPoint instance, EnumFacing side, NBTBase nbt) {
				NBTTagCompound nbtTagCompound = (NBTTagCompound)nbt;
				if(nbtTagCompound.hasKey("NAV_NAME")) {
					instance.setNavName(nbtTagCompound.getString("NAV_NAME"));
				}

				instance.setNavPosition(BlockPos.fromLong(nbtTagCompound.getLong("NAV_POSITION")));
			}
		}, () -> {
			return new NavPointHandler(BlockPos.ORIGIN);
		});
	}
}
