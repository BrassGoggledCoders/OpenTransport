package xyz.brassgoggledcoders.opentransport.vehicle.minecart.entity;

import com.teamacronymcoders.base.entities.EntityMinecartBase;
import net.minecraft.item.ItemMinecart;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.block.CarriedBlockInstance;
import xyz.brassgoggledcoders.opentransport.api.entity.IBlockCarrier;
import xyz.brassgoggledcoders.opentransport.api.entity.minecart.CustomMinecart;
import xyz.brassgoggledcoders.opentransport.vehicle.minecart.item.ItemMinecartCarrier;

import javax.annotation.Nonnull;

public class EntityMinecartCarrier extends EntityMinecartBase implements IBlockCarrier<EntityMinecartCarrier> {
    @ObjectHolder(OpenTransport.MODID + ":minecart_carrier")
    private static ItemMinecartCarrier itemMinecartCarrier;

    @ObjectHolder(OpenTransport.MODID + ":minecart")
    private static CustomMinecart customMinecart;

    public EntityMinecartCarrier(World world) {
        super(world);
    }

    @Nonnull
    @Override
    public ItemMinecart getItem() {
        return itemMinecartCarrier;
    }

    public CustomMinecart getCustomMinecart() {
        return customMinecart;
    }

    @Override
    public CarriedBlockInstance getCarriedBlock() {
        return null;
    }

    @Override
    public World getWorld() {
        return this.getEntityWorld();
    }

    @Override
    public EntityMinecartCarrier getCarrier() {
        return this;
    }
}
