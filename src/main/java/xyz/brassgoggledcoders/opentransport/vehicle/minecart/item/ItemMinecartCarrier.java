package xyz.brassgoggledcoders.opentransport.vehicle.minecart.item;

import com.teamacronymcoders.base.entities.EntityMinecartBase;
import com.teamacronymcoders.base.items.minecarts.ItemMinecartBase;
import com.teamacronymcoders.base.registrysystem.EntityRegistry;
import com.teamacronymcoders.base.registrysystem.config.ConfigRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.opentransport.vehicle.minecart.entity.EntityMinecartCarrier;

import javax.annotation.Nonnull;

public class ItemMinecartCarrier extends ItemMinecartBase {
    public ItemMinecartCarrier() {
        super("carrier");
    }

    @Nonnull
    @Override
    public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack) {
        return new EntityMinecartCarrier(world);
    }
}
