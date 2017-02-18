package xyz.brassgoggledcoders.opentransport.boats.entities;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSourceIndirect;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityBoatBase extends EntityBoat {
    public EntityBoatBase(World world) {
        super(world);
    }

    @Override
    public boolean canPassengerSteer() {
        return true;
    }

    @Override
    public boolean isBeingRidden() {
        return false;
    }

    @Override
    public boolean processInitialInteract(@Nonnull EntityPlayer player, @Nullable ItemStack stack, EnumHand hand) {
        return true;
    }

    @Override
    protected boolean canFitPassenger(Entity passenger) {
        return false;
    }

    public boolean attackEntityFrom(@Nonnull DamageSource source, float amount) {
        if (this.isEntityInvulnerable(source)) {
            return false;
        } else if (!this.worldObj.isRemote && !this.isDead) {
            if (source instanceof EntityDamageSourceIndirect && source.getEntity() != null && this.isPassenger(source.getEntity())) {
                return false;
            } else {
                this.setForwardDirection(-this.getForwardDirection());
                this.setTimeSinceHit(10);
                this.setDamageTaken(this.getDamageTaken() + amount * 10.0F);
                this.setBeenAttacked();
                boolean flag = source.getEntity() instanceof EntityPlayer && ((EntityPlayer) source.getEntity()).capabilities.isCreativeMode;

                if (flag || this.getDamageTaken() > 40.0F) {
                    if (!flag && this.worldObj.getGameRules().getBoolean("doEntityDrops")) {
                        this.entityDropItem(this.getBoatItemStack(), 0.0F);
                    }

                    this.setDead();
                }

                return true;
            }
        } else {
            return true;
        }
    }

    public ItemStack getBoatItemStack() {
        return new ItemStack(this.getItemBoat(), 1, this.getBoatType().ordinal());
    }
}
