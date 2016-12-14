package xyz.brassgoggledcoders.opentransport.modules.manasteam;

import mods.railcraft.common.util.steam.IFuelProvider;
import mods.railcraft.common.util.steam.Steam;
import xyz.brassgoggledcoders.opentransport.modules.manasteam.capabilities.IManaHolder;

public class ManaFuelProvider implements IFuelProvider {
    private IManaHolder manaHolder;
    private boolean nearExoFlame = false;

    public ManaFuelProvider(IManaHolder manaHolder) {
        this.manaHolder = manaHolder;
    }

    @Override
    public double getMoreFuel() {
        int burnTime = 0;
        if(manaHolder.getMana() > 100) {
            manaHolder.setMana(manaHolder.getMana() - 100);
            burnTime = 50;
        }
        return burnTime;
    }

    @Override
    public double getHeatStep() {
        return nearExoFlame ? Steam.HEAT_STEP * 30 : Steam.HEAT_STEP;
    }

    public void setNearExoFlame(boolean nearExoFlame) {
        this.nearExoFlame = nearExoFlame;
    }
}
