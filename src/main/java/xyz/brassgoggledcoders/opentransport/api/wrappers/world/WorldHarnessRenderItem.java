package xyz.brassgoggledcoders.opentransport.api.wrappers.world;

import com.teamacronymcoders.base.client.ClientHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;

@SideOnly(Side.CLIENT)
public class WorldHarnessRenderItem implements IWorldHarness {
    private IBlockWrapper blockWrapper;

    public WorldHarnessRenderItem(IBlockWrapper blockWrapper) {
        this.blockWrapper = blockWrapper;
    }

    @Override
    public IBlockWrapper getBlockWrapper() {
        return this.blockWrapper;
    }

    @Override
    public World getRealWorld() {
        return ClientHelper.world();
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
