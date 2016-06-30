package xyz.brassgoggledcoders.opentransport.modules.boats.navigation.items;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.items.ItemBase;
import xyz.brassgoggledcoders.opentransport.modules.boats.navigation.entities.EntityBuoy;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemBuoy extends ItemBase {
	public ItemBuoy() {
		super("buoy");
	}

	@Override
	@Nonnull
	public ActionResult<ItemStack> onItemRightClick(@Nonnull ItemStack itemStack, World world, EntityPlayer entityPlayer, EnumHand hand) {
		float f = 1.0F;
		float f1 = entityPlayer.prevRotationPitch + (entityPlayer.rotationPitch - entityPlayer.prevRotationPitch) * f;
		float f2 = entityPlayer.prevRotationYaw + (entityPlayer.rotationYaw - entityPlayer.prevRotationYaw) * f;
		double d0 = entityPlayer.prevPosX + (entityPlayer.posX - entityPlayer.prevPosX) * (double) f;
		double d1 = entityPlayer.prevPosY + (entityPlayer.posY - entityPlayer.prevPosY) * (double) f +
				(double) entityPlayer.getEyeHeight();
		double d2 = entityPlayer.prevPosZ + (entityPlayer.posZ - entityPlayer.prevPosZ) * (double) f;
		Vec3d vec3d = new Vec3d(d0, d1, d2);
		float f3 = MathHelper.cos(-f2 * 0.017453292F - (float) Math.PI);
		float f4 = MathHelper.sin(-f2 * 0.017453292F - (float) Math.PI);
		float f5 = -MathHelper.cos(-f1 * 0.017453292F);
		float f6 = MathHelper.sin(-f1 * 0.017453292F);
		float f7 = f4 * f5;
		float f8 = f3 * f5;
		double d3 = 5.0D;
		Vec3d vec3d1 = vec3d.addVector((double) f7 * d3, (double) f6 * d3, (double) f8 * d3);
		RayTraceResult raytraceresult = world.rayTraceBlocks(vec3d, vec3d1, true);

		if(raytraceresult == null) {
			return new ActionResult<>(EnumActionResult.PASS, itemStack);
		} else {
			Vec3d vec3d2 = entityPlayer.getLook(f);
			boolean flag = false;
			List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(entityPlayer,
					entityPlayer.getEntityBoundingBox()
							.addCoord(vec3d2.xCoord * d3, vec3d2.yCoord * d3, vec3d2.zCoord * d3).expandXyz(1.0D));

			for(Entity entity : list) {
				if(entity.canBeCollidedWith()) {
					AxisAlignedBB axisalignedbb =
							entity.getEntityBoundingBox().expandXyz((double) entity.getCollisionBorderSize());

					if(axisalignedbb.isVecInside(vec3d)) {
						flag = true;
					}
				}
			}

			if(flag) {
				return new ActionResult<>(EnumActionResult.PASS, itemStack);
			}
			else if(raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
				return new ActionResult<>(EnumActionResult.PASS, itemStack);
			}
			else {
				Block block = world.getBlockState(raytraceresult.getBlockPos()).getBlock();
				boolean isWater = block == Blocks.WATER || block == Blocks.FLOWING_WATER;
				EntityBuoy entityBuoy = new EntityBuoy(world);
				double boatPosX = raytraceresult.hitVec.xCoord;
				double boatPosY = isWater ? raytraceresult.hitVec.yCoord - 0.12D : raytraceresult.hitVec.yCoord;
				double boatPosZ = raytraceresult.hitVec.zCoord;
				entityBuoy.setPosition(boatPosX, boatPosY, boatPosZ);
				entityBuoy.rotationYaw = 0;

				if(!world.getCollisionBoxes(entityBuoy, entityBuoy.getEntityBoundingBox().expandXyz(-0.1D))
						.isEmpty()) {
					return new ActionResult<>(EnumActionResult.FAIL, itemStack);
				} else {
					if(!world.isRemote) {
						world.spawnEntityInWorld(entityBuoy);
					}

					if(!entityPlayer.capabilities.isCreativeMode) {
						--itemStack.stackSize;
					}

					this.increaseStat(entityPlayer);
					return new ActionResult<>(EnumActionResult.SUCCESS, itemStack);
				}
			}
		}
	}

	public void increaseStat(EntityPlayer entityPlayer) {
		StatBase stat = StatList.getObjectUseStats(this);
		if(stat != null) {
			entityPlayer.addStat(stat);
		}
	}

}
