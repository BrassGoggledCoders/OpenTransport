package xyz.brassgoggledcoders.opentransport.api;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;

/*
 * Interface to proxy calls to @see xyz.brassgoggledcoders.opentransport.OpenTransport
 * NOT MEANT TO BE IMPLEMENTED BY ANYTHING BUT OPENTRANSPORT.
 */
public interface IModWrapper {
    Object getModInstance();

    void sendBlockWrapperPacket(IHolderEntity entity);

    EntityPlayer getPlayerWrapper(EntityPlayer entityPlayer, IHolderEntity entity);

    void logError(String error);

    void openGui(IHolderEntity entity, EntityPlayer player, World world);

    IBlockWrapper getLoadedBlockWrapper(String name);
}
