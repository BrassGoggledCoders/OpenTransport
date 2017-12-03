package xyz.brassgoggledcoders.opentransport.api.capability.blockcarrier;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import xyz.brassgoggledcoders.opentransport.api.block.CarriedBlock;
import xyz.brassgoggledcoders.opentransport.api.block.CarriedBlockInstance;

public interface IBlockCarrier extends INBTSerializable<NBTTagCompound> {
    CarriedBlockInstance getCarriedBlockInstance();

    CarriedBlock getCarriedBlock();
}
