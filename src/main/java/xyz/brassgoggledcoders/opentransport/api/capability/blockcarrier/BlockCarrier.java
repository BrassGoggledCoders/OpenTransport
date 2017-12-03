package xyz.brassgoggledcoders.opentransport.api.capability.blockcarrier;

import com.teamacronymcoders.base.util.RegistryUtils;
import com.teamacronymcoders.base.util.TextUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import xyz.brassgoggledcoders.opentransport.api.OpenTransportRegistries;
import xyz.brassgoggledcoders.opentransport.api.block.CarriedBlock;
import xyz.brassgoggledcoders.opentransport.api.block.CarriedBlockInstance;

import static xyz.brassgoggledcoders.opentransport.api.OpenTransportAPI.CARRIED_BLOCK_INSTANCE_NBT_NAME;
import static xyz.brassgoggledcoders.opentransport.api.OpenTransportAPI.CARRIED_BLOCK_NBT_NAME;

public class BlockCarrier implements IBlockCarrier {
    private CarriedBlockInstance carriedBlockInstance;

    public BlockCarrier() {

    }

    public BlockCarrier(CarriedBlockInstance carriedBlockInstance) {
        this.carriedBlockInstance = carriedBlockInstance;
    }

    @Override
    public CarriedBlockInstance getCarriedBlockInstance() {
        return carriedBlockInstance;
    }

    @Override
    public CarriedBlock getCarriedBlock() {
        return carriedBlockInstance.getCarriedBlock();
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setString(CARRIED_BLOCK_NBT_NAME, TextUtils.getRegistryLocation(this.getCarriedBlock()));
        nbtTagCompound.setTag(CARRIED_BLOCK_INSTANCE_NBT_NAME, carriedBlockInstance.writeToNBT(new NBTTagCompound()));
        return nbtTagCompound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbt) {
        CarriedBlock carriedBlock = RegistryUtils.getEntry(OpenTransportRegistries.CARRIED_BLOCKS,
                new ResourceLocation(nbt.getString(CARRIED_BLOCK_NBT_NAME)));
        carriedBlockInstance = carriedBlock.getNewInstance();
        carriedBlockInstance.readFromNBT((NBTTagCompound)nbt.getTag(CARRIED_BLOCK_INSTANCE_NBT_NAME));
    }
}
