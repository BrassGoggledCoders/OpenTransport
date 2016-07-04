package xyz.brassgoggledcoders.opentransport.modules.boats.navigation.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import xyz.brassgoggledcoders.boilerplate.items.ItemBase;
import xyz.brassgoggledcoders.opentransport.api.navigation.cargoslip.CapProviderCargoSlip;
import xyz.brassgoggledcoders.opentransport.api.navigation.cargoslip.CapabilityCargoSlip;
import xyz.brassgoggledcoders.opentransport.api.navigation.cargoslip.ICargoSlip;
import xyz.brassgoggledcoders.opentransport.api.navigation.navpoint.CapabilityNavPoint;
import xyz.brassgoggledcoders.opentransport.api.navigation.navpoint.INavPoint;

import javax.annotation.Nonnull;

public class ItemCargoSlip extends ItemBase {
	public ItemCargoSlip() {
		super("cargo_slip");
		this.setMaxStackSize(1);
		this.setHasSubtypes(false);
	}

	@Override
	@Nonnull
	public EnumActionResult onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumHand hand,
			EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		TileEntity tileEntity = world.getTileEntity(pos);
		if(tileEntity != null && tileEntity.hasCapability(CapabilityNavPoint.NAV_POINT_CAP, facing)) {
			if(stack.hasCapability(CapabilityCargoSlip.CARGO_SLIP_CAP, facing)) {
				ICargoSlip cargoSlip = stack.getCapability(CapabilityCargoSlip.CARGO_SLIP_CAP, facing);
				INavPoint navPoint = tileEntity.getCapability(CapabilityNavPoint.NAV_POINT_CAP, facing);
				cargoSlip.addNavPoint(navPoint);
				return EnumActionResult.SUCCESS;
			}
		}
		return EnumActionResult.PASS;
	}

	@Override
	@Nonnull
	public ActionResult<ItemStack> onItemRightClick(@Nonnull ItemStack itemStack, World world, EntityPlayer player,
			EnumHand hand)
	{
		if(player.isSneaking() && itemStack.hasCapability(CapabilityCargoSlip.CARGO_SLIP_CAP, EnumFacing.UP)) {
			ICargoSlip cargoSlip = itemStack.getCapability(CapabilityCargoSlip.CARGO_SLIP_CAP, EnumFacing.UP);
			cargoSlip.removeLastNavPoint();
			return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
		}
		return new ActionResult<>(EnumActionResult.PASS, itemStack);
	}

	@Override
	@Nonnull
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt)
	{
		return new CapProviderCargoSlip(nbt);
	}

}
