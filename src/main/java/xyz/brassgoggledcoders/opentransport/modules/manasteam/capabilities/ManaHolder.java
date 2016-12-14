package xyz.brassgoggledcoders.opentransport.modules.manasteam.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;
import vazkii.botania.common.block.tile.mana.TilePool;

public class ManaHolder implements IManaHolder, INBTSerializable<NBTTagCompound> {
    private int mana = 0;
    private int maxMana = 0;
    private int transferRate = 0;

    public ManaHolder() {
        this(TilePool.MAX_MANA);
    }

    public ManaHolder(int maxMana) {
        this(maxMana, maxMana);
    }

    public ManaHolder(int maxMana, int transferRate) {
        this.maxMana = maxMana;
        this.transferRate = transferRate;
    }

    @Override
    public int getMana() {
        return this.mana;
    }

    @Override
    public void setMana(int mana) {
        this.mana = mana > this.getMaxMana() ? this.getMaxMana() : mana;
    }

    @Override
    public int addMana(int manaToAdd) {
        int transfer = Math.min(this.getTransferRate(), manaToAdd);
        int actualTransfer = Math.min(this.getMaxMana() - this.getMana(), transfer);
        this.setMana(this.getMana() + actualTransfer);
        return actualTransfer;
    }

    @Override
    public int getTransferRate() {
        return this.transferRate;
    }

    public void setTransferRate(int transfer) {
        this.transferRate = transfer;
    }

    @Override
    public int getMaxMana() {
        return this.maxMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    @Override
    public NBTTagCompound serializeNBT() {
        NBTTagCompound nbtTagCompound = new NBTTagCompound();
        nbtTagCompound.setInteger("MANA", this.getMana());
        nbtTagCompound.setInteger("MAX_MANA", this.getMaxMana());
        nbtTagCompound.setInteger("TRANSFER", this.getTransferRate());
        return nbtTagCompound;
    }

    @Override
    public void deserializeNBT(NBTTagCompound nbtTagCompound) {
        this.setMana(nbtTagCompound.getInteger("MANA"));
        this.setMaxMana(nbtTagCompound.getInteger("MAX_MANA"));
        this.setTransferRate(nbtTagCompound.getInteger("TRANSFER"));
    }
}
