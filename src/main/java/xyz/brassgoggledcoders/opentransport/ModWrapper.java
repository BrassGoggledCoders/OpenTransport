package xyz.brassgoggledcoders.opentransport;

import com.teamacronymcoders.base.guisystem.target.GuiEntityTarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.opentransport.api.IModWrapper;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.network.HolderUpdatePacket;

public class ModWrapper implements IModWrapper {
    @Override
    public Object getModInstance() {
        return OpenTransport.instance;
    }

    @Override
    public void sendBlockWrapperPacket(IHolderEntity entity) {
        OpenTransport.instance.getPacketHandler().sendToAllAround(new HolderUpdatePacket(entity), entity.getEntity());
    }

    @Override
    public EntityPlayer getPlayerWrapper(EntityPlayer entityPlayer, IHolderEntity entity) {
        return OpenTransport.proxy.getEntityPlayerWrapper(entityPlayer, entity);
    }

    @Override
    public void logError(String error) {
        OpenTransport.instance.getLogger().error(error);
    }

    @Override
    public void openGui(IHolderEntity entity, EntityPlayer player, World world) {
        OpenTransport.instance.getGuiHandler().openGui(new GuiEntityTarget(entity.getEntity()), new NBTTagCompound(),
                false, player, world);
    }

    @Override
    public void setWorldHarness(IBlockWrapper blockWrapper) {
        OpenTransport.proxy.setWorldHarness(blockWrapper);
    }
}
