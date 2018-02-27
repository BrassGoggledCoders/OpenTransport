package xyz.brassgoggledcoders.opentransport.vehicle.minecart.entity;

import com.teamacronymcoders.base.entities.EntityMinecartBase;
import com.teamacronymcoders.base.entities.dataserializers.BaseDataSerializers;
import com.teamacronymcoders.base.util.RegistryUtils;
import com.teamacronymcoders.base.util.TextUtils;
import net.minecraft.item.ItemMinecart;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.OpenTransportAPI;
import xyz.brassgoggledcoders.opentransport.api.OpenTransportRegistries;
import xyz.brassgoggledcoders.opentransport.api.block.CarriedBlock;
import xyz.brassgoggledcoders.opentransport.api.block.CarriedBlockInstance;
import xyz.brassgoggledcoders.opentransport.api.entity.IBlockCarrierEntity;
import xyz.brassgoggledcoders.opentransport.api.entity.minecart.CustomMinecart;
import xyz.brassgoggledcoders.opentransport.vehicle.minecart.item.ItemMinecartCarrier;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

import static xyz.brassgoggledcoders.opentransport.api.OpenTransportAPI.DEFAULT_CARRIED_BLOCK;

@ObjectHolder(OpenTransport.MODID)
public class EntityMinecartCarrier extends EntityMinecartBase implements IBlockCarrierEntity<EntityMinecartCarrier> {
    @ObjectHolder("minecart_carrier")
    private static ItemMinecartCarrier itemMinecartCarrier;
    @ObjectHolder("minecart")
    private static CustomMinecart customMinecart;

    private static final DataParameter<ResourceLocation> CARRIED_BLOCK =
            EntityDataManager.createKey(EntityMinecartCarrier.class, BaseDataSerializers.RESOURCE_LOCATION);

    private CarriedBlockInstance carriedBlockInstance;

    public EntityMinecartCarrier(World world) {
        super(world);
    }

    public EntityMinecartCarrier(World world, CarriedBlock carriedBlock) {
        super(world);
        this.carriedBlockInstance = carriedBlock.getNewInstance(world);
        this.dataManager.set(CARRIED_BLOCK, Optional.ofNullable(carriedBlock.getRegistryName()).orElse(DEFAULT_CARRIED_BLOCK));
    }

    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(CARRIED_BLOCK, DEFAULT_CARRIED_BLOCK);
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
        if (carriedBlockInstance == null) {
            CarriedBlock carriedBlock = RegistryUtils.getEntry(OpenTransportRegistries.CARRIED_BLOCKS, this.dataManager.get(CARRIED_BLOCK));
            carriedBlockInstance = carriedBlock.getNewInstance(world);
        }
        return carriedBlockInstance;
    }

    @Override
    public World getWorld() {
        return this.getEntityWorld();
    }

    @Override
    public EntityMinecartCarrier getCarrier() {
        return this;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        CarriedBlock carriedBlock = RegistryUtils.getEntry(OpenTransportRegistries.CARRIED_BLOCKS,
                new ResourceLocation(compound.getString(OpenTransportAPI.CARRIED_BLOCK_NBT_NAME)));

        carriedBlockInstance = carriedBlock.getNewInstance(this.getEntityWorld());
        carriedBlockInstance.readFromNBT(compound.getCompoundTag(OpenTransportAPI.CARRIED_BLOCK_INSTANCE_NBT_NAME));
    }

    @Override
    protected void writeEntityToNBT(@Nonnull NBTTagCompound compound) {
        compound.setString(OpenTransportAPI.CARRIED_BLOCK_NBT_NAME, TextUtils.getRegistryLocation(carriedBlockInstance.getCarriedBlock()));
        compound.setTag(OpenTransportAPI.CARRIED_BLOCK_INSTANCE_NBT_NAME, carriedBlockInstance.writeToNBT(new NBTTagCompound()));
    }

    @Override
    public boolean hasCapability(@Nonnull Capability<?> capability, EnumFacing facing) {
        return carriedBlockInstance.hasCapability(capability, facing);
    }

    @Override
    @Nullable
    public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing) {
        return carriedBlockInstance.getCapability(capability, facing);
    }
}
