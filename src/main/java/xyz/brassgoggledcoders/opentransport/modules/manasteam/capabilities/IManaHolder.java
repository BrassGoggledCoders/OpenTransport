package xyz.brassgoggledcoders.opentransport.modules.manasteam.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public interface IManaHolder extends INBTSerializable<NBTTagCompound> {
    int getMana();

    void setMana(int mana);

    int addMana(int mana);

    int getTransferRate();

    int getMaxMana();
}
