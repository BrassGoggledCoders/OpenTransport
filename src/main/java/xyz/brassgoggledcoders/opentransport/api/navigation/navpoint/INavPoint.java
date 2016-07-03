package xyz.brassgoggledcoders.opentransport.api.navigation.navpoint;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface INavPoint extends INBTSerializable<NBTTagCompound> {
	@Nonnull
	BlockPos getNavPosition();

	void setNavPosition(@Nonnull BlockPos navPosition);

	@Nullable
	String getNavName();

	void setNavName(@Nullable String navName);
}
