package xyz.brassgoggledcoders.opentransport.vehicle.minecart.entity;

import com.teamacronymcoders.base.entities.EntityMinecartBase;
import net.minecraft.item.ItemMinecart;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.vehicle.minecart.item.ItemMinecartCarrier;

import javax.annotation.Nonnull;

public class EntityMinecartCarrier extends EntityMinecartBase {
    @ObjectHolder(OpenTransport.ID + ":" + "minecart_carrier")
    private static ItemMinecartCarrier itemMinecartCarrier;

    public EntityMinecartCarrier(World world) {
        super(world);
    }

    @Nonnull
    @Override
    public ItemMinecart getItem() {
        return itemMinecartCarrier;
    }
}
