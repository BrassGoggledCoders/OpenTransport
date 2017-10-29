package xyz.brassgoggledcoders.opentransport.api.entity.minecart;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemMinecart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class CustomMinecart<ITEM extends ItemMinecart, ENTITY extends EntityMinecart> extends IForgeRegistryEntry.Impl<CustomMinecart<ITEM, ENTITY>> {
    public double getMaxSpeed() {
        return 0.4D;
    }

    public double getDragFactor() {
        return 0.9599999785423279D;
    }

    public abstract ITEM getItemMinecart();

    public abstract ENTITY getEmptyMinecart(World world);

    public abstract void renderMinecartModel(EntityMinecart entityMinecart);

    public NBTTagCompound writeAdditonalInfo() {
        return new NBTTagCompound();
    }

    public void readAdditionalInfo(NBTTagCompound nbtTagCompound) {

    }
}
