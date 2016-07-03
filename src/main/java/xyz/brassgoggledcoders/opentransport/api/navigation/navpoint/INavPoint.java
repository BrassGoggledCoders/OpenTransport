package xyz.brassgoggledcoders.opentransport.api.navigation.navpoint;

import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface INavPoint {
	@Nonnull
	BlockPos getNavPosition();

	void setNavPosition(@Nonnull BlockPos navPosition);

	@Nullable
	String getNavName();

	void setNavName(@Nullable String navName);
}
