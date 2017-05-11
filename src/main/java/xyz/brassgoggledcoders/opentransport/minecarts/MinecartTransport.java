package xyz.brassgoggledcoders.opentransport.minecarts;

import com.teamacronymcoders.base.registrysystem.EntityRegistry;
import com.teamacronymcoders.base.registrysystem.ItemRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.ITransportType;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.TransportType;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.minecarts.entities.EntityMinecartHolder;
import xyz.brassgoggledcoders.opentransport.minecarts.items.ItemMinecartHolder;

import javax.annotation.Nonnull;
import java.util.Map;

@TransportType
public class MinecartTransport implements ITransportType<EntityMinecart> {
    private CreativeTabs cartsTab = new MinecartCreativeTab();
    private boolean isActive = true;

    public static ItemMinecartHolder itemMinecartHolder;

    @Nonnull
    @Override
    public String getName() {
        return "Minecarts";
    }

    @Nonnull
    @Override
    public Class<EntityMinecart> getBaseEntity() {
        return EntityMinecart.class;
    }

    @Nonnull
    @Override
    public CreativeTabs getCreativeTab() {
        return cartsTab;
    }

    @Override
    public void registerItems(Map<String, IBlockWrapper> blockWrappers) {
        itemMinecartHolder = new ItemMinecartHolder(this.getCreativeTab());
        OpenTransport.instance.getRegistryHolder().getRegistry(ItemRegistry.class, "ITEM").register(itemMinecartHolder);
    }

    @Override
    public void registerEntities() {
        OpenTransport.instance.getRegistryHolder().getRegistry(EntityRegistry.class, "ENTITY").register(EntityMinecartHolder.class);
    }

    @Override
    public boolean getIsActive() {
        return this.isActive;
    }

    @Override
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    private static class MinecartCreativeTab extends CreativeTabs {
        public MinecartCreativeTab() {
            super("minecarts");
        }

        @Override
        @Nonnull
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.MINECART);
        }
    }
}
