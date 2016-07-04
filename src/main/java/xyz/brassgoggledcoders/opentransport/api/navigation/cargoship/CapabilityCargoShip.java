package xyz.brassgoggledcoders.opentransport.api.navigation.cargoship;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public class CapabilityCargoShip {
	@CapabilityInject(ICargoShip.class)
	public static Capability<ICargoShip> CARGO_SHIP_CAP;

	public static void register() {
		CapabilityManager.INSTANCE.register(ICargoShip.class, new Capability.IStorage<ICargoShip>() {
			@Override
			public NBTBase writeNBT(Capability<ICargoShip> capability, ICargoShip instance, EnumFacing side) {
				return instance.serializeNBT();
			}

			@Override
			public void readNBT(Capability<ICargoShip> capability, ICargoShip instance, EnumFacing side, NBTBase nbt) {
				NBTTagCompound nbtTagCompound = (NBTTagCompound)nbt;
				instance.deserializeNBT(nbtTagCompound);
			}
		}, CargoShipHandler::new);
	}
}
