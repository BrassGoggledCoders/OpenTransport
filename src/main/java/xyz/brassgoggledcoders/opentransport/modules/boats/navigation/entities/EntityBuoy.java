package xyz.brassgoggledcoders.opentransport.modules.boats.navigation.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.brassgoggledcoders.opentransport.modules.boats.navigation.NavigationModule;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityBuoy extends Entity {
	private static final DataParameter<Integer>
			TIME_SINCE_HIT = EntityDataManager.<Integer>createKey(EntityBoat.class, DataSerializers.VARINT);
	private static final DataParameter<Float> DAMAGE_TAKEN = EntityDataManager.<Float>createKey(EntityBoat.class,
			DataSerializers.FLOAT);

	public EntityBuoy(World world) {
		super(world);
		this.setSize(1F, 2F);
		this.preventEntitySpawning = true;
		this.isImmuneToFire = true;
	}

	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBox(Entity entity)
	{
		return entity.getEntityBoundingBox();
	}

	@Override
	@Nullable
	public AxisAlignedBB getCollisionBoundingBox()
	{
		return this.getEntityBoundingBox();
	}

	@Override
	public boolean canBePushed()
	{
		return true;
	}

	@SideOnly(Side.CLIENT)
	public void performHurtAnimation()
	{
		this.setTimeSinceHit(10);
		this.setDamageTaken(this.getDamageTaken() * 11.0F);
	}

	@Override
	public void onUpdate() {
		if (this.getDamageTaken() > 0.0F)
		{
			this.setDamageTaken(this.getDamageTaken() - 1.0F);
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (this.isEntityInvulnerable(source))
		{
			return false;
		}
		else if (!this.worldObj.isRemote && !this.isDead)
		{
			if (source instanceof EntityDamageSourceIndirect && source.getEntity() != null && this.isPassenger(source.getEntity()))
			{
				return false;
			}
			else
			{
				this.setTimeSinceHit(10);
				this.setDamageTaken(this.getDamageTaken() + amount * 10.0F);
				this.setBeenAttacked();
				boolean flag = source.getEntity() instanceof EntityPlayer && ((EntityPlayer)source.getEntity()).capabilities.isCreativeMode;

				if (flag || this.getDamageTaken() > 40.0F)
				{
					if (!flag && this.worldObj.getGameRules().getBoolean("doEntityDrops"))
					{
						this.dropItemWithOffset(NavigationModule.ITEM_BUOY, 1, 0.0F);
					}

					this.setDead();
				}

				return true;
			}
		}
		else
		{
			return true;
		}
	}

	public void setDamageTaken(float damageTaken)
	{
		this.dataManager.set(DAMAGE_TAKEN, damageTaken);
	}

	public float getDamageTaken()
	{
		return this.dataManager.get(DAMAGE_TAKEN);
	}

	public void setTimeSinceHit(int timeSinceHit)
	{
		this.dataManager.set(TIME_SINCE_HIT, timeSinceHit);
	}

	public int getTimeSinceHit()
	{
		return this.dataManager.get(TIME_SINCE_HIT);
	}

	@Override
	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}

	@Override
	protected void entityInit() {
		this.dataManager.register(DAMAGE_TAKEN, 0.0F);
		this.dataManager.register(TIME_SINCE_HIT, 0);
	}

	@Override
	protected void readEntityFromNBT(@Nonnull NBTTagCompound compound) {

	}

	@Override
	protected void writeEntityToNBT(@Nonnull NBTTagCompound compound) {

	}
}
