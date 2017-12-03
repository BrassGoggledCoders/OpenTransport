package xyz.brassgoggledcoders.opentransport.vehicle.minecart.item;

import com.teamacronymcoders.base.entities.EntityMinecartBase;
import com.teamacronymcoders.base.items.minecart.ItemMinecartBase;
import com.teamacronymcoders.base.util.CapabilityUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.OpenTransportAPI;
import xyz.brassgoggledcoders.opentransport.api.OpenTransportRegistries;
import xyz.brassgoggledcoders.opentransport.api.block.CarriedBlock;
import xyz.brassgoggledcoders.opentransport.api.block.CarriedBlockInstance;
import xyz.brassgoggledcoders.opentransport.api.capability.blockcarrier.BlockCarrier;
import xyz.brassgoggledcoders.opentransport.api.capability.blockcarrier.BlockCarrierProvider;
import xyz.brassgoggledcoders.opentransport.api.capability.blockcarrier.IBlockCarrier;
import xyz.brassgoggledcoders.opentransport.api.item.ItemUtils;
import xyz.brassgoggledcoders.opentransport.vehicle.minecart.entity.EntityMinecartCarrier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;

import static xyz.brassgoggledcoders.opentransport.api.capability.blockcarrier.CapabilityBlockCarrier.BLOCK_CARRIER_ITEM;

public class ItemMinecartCarrier extends ItemMinecartBase {
    @ObjectHolder(OpenTransport.MODID + ":minecraft_air")
    private static CarriedBlock air;

    public ItemMinecartCarrier() {
        super("carrier");
    }

    @Nonnull
    @Override
    public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack) {
        return new EntityMinecartCarrier(world, this.getCarriedBlock(itemStack).getCarriedBlock());
    }

    @Override
    public List<ItemStack> getAllSubItems(List<ItemStack> itemStacks) {
        itemStacks.addAll(ItemUtils.getCarrierItemStacksFor(this));
        return itemStacks;
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public String getItemStackDisplayName(@Nonnull ItemStack cartItemStack) {
        return I18n.translateToLocalFormatted("carrier.name", super.getItemStackDisplayName(cartItemStack),
                this.getCarriedBlock(cartItemStack).getItemStack());
    }

    private CarriedBlockInstance getCarriedBlock(ItemStack itemStack) {
        return CapabilityUtils.getCapability(itemStack, BLOCK_CARRIER_ITEM)
                .map(IBlockCarrier::getCarriedBlockInstance)
                .orElse(air.getNewInstance());
    }

    @Override
    public ICapabilityProvider initCapabilities(ItemStack itemStack, @Nullable NBTTagCompound nbt) {
        return new BlockCarrierProvider(new BlockCarrier(Optional.ofNullable(nbt)
                .map(nbtTagCompound -> nbtTagCompound.getString(OpenTransportAPI.CARRIED_BLOCK_NBT_NAME))
                .map(ResourceLocation::new)
                .map(OpenTransportRegistries.CARRIED_BLOCKS::getValue)
                .orElse(air).getNewInstance()));
    }
}
