package xyz.brassgoggledcoders.opentransport.api.entity.minecart;

import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class CustomMinecart extends IForgeRegistryEntry.Impl<CustomMinecart> {
    public double getMaxSpeed() {
        return 0.4D;
    }

    public double getDragFactor() {
        return 0.9599999785423279D;
    }

    public abstract ItemStack getItemMinecart();

    public abstract EntityMinecart getEmptyMinecart(World world);

    public abstract void renderMinecartModel(EntityMinecart entityMinecart);
}
