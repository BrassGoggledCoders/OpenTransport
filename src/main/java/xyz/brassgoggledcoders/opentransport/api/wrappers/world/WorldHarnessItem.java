package xyz.brassgoggledcoders.opentransport.api.wrappers.world;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;

public class WorldHarnessItem implements IWorldHarness {
    private IBlockWrapper blockWrapper;

    public WorldHarnessItem(IBlockWrapper blockWrapper) {
        this.blockWrapper = blockWrapper;
    }

    @Override
    public IBlockWrapper getBlockWrapper() {
        return this.blockWrapper;
    }

    @Override
    public World getRealWorld() {
        return Minecraft.getMinecraft().theWorld;
    }

    @Override
    public void setBlockToAir() {

    }

    @Override
    public boolean getRedstonePower() {
        return false;
    }

    @Override
    public double getPosX() {
        return 0;
    }

    @Override
    public double getPosY() {
        return 0;
    }

    @Override
    public double getPosZ() {
        return 0;
    }
}
