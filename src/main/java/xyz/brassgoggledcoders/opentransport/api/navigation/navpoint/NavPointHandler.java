package xyz.brassgoggledcoders.opentransport.api.navigation.navpoint;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class NavPointHandler implements INavPoint, INBTSerializable<NBTTagCompound> {
	private BlockPos navPosition;
	private String navName;

	public NavPointHandler(@Nonnull BlockPos navPosition) {
		this(null, navPosition);
	}

	public NavPointHandler(@Nullable String navName,@Nonnull BlockPos navPosition) {
		this.navName = navName;
		this.navPosition = navPosition;
	}

	@Nonnull
	@Override
	public BlockPos getNavPosition() {
		return navPosition;
	}

	@Override
	public void setNavPosition(@Nonnull BlockPos navPosition) {
		this.navPosition = navPosition;
	}

	@Nullable
	@Override
	public String getNavName() {
		return navName;
	}

	@Override
	public void setNavName(@Nullable String navName) {
		this.navName = navName;
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbtTagCompound = new NBTTagCompound();
		if(this.getNavName() != null) {
			nbtTagCompound.setString("NAV_NAME", this.getNavName());
		}
		nbtTagCompound.setLong("NAV_POSITION", this.getNavPosition().toLong());

		return nbtTagCompound;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		if(nbt.hasKey("NAV_NAME")) {
			this.setNavName(nbt.getString("NAV_NAME"));
		}
		this.setNavPosition(BlockPos.fromLong(nbt.getLong("NAV_POSITION")));
	}
}
