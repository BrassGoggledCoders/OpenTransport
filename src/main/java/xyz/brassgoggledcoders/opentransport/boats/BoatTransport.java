package xyz.brassgoggledcoders.opentransport.boats;

import com.teamacronymcoders.base.registry.EntityRegistry;
import com.teamacronymcoders.base.registry.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.ITransportType;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.TransportType;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.boats.entities.EntityBoatHolder;
import xyz.brassgoggledcoders.opentransport.boats.items.ItemBoatHolder;

import javax.annotation.Nonnull;
import java.util.Map;

@TransportType
public class BoatTransport implements ITransportType<EntityBoat> {
    private CreativeTabs boatsTab = new BoatCreativeTab();
    private boolean isActive = true;

    public static ItemBoatHolder itemBoatHolder;

    @Override
    @Nonnull
    public String getName() {
        return "Boats";
    }

    @Override
    @Nonnull
    public Class<EntityBoat> getBaseEntity() {
        return EntityBoat.class;
    }

    @Override
    @Nonnull
    public CreativeTabs getCreativeTab() {
        return boatsTab;
    }

    @Override
    public void registerItems(Map<String, IBlockWrapper> blockWrappers) {
        itemBoatHolder = new ItemBoatHolder(this.getCreativeTab());
        OpenTransport.instance.getRegistryHolder().getRegistry(ItemRegistry.class, "ITEM").register(itemBoatHolder);
    }

    @Override
    public void registerEntities() {
        OpenTransport.instance.getRegistry(EntityRegistry.class, "ENTITY").register(EntityBoatHolder.class);
    }

    @Override
    public boolean getIsActive() {
        return this.isActive;
    }

    @Override
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    private static class BoatCreativeTab extends CreativeTabs {
        public BoatCreativeTab() {
            super("boats");
        }

        @Override
        @Nonnull
        public Item getTabIconItem() {
            return Items.BOAT;
        }
    }
}
