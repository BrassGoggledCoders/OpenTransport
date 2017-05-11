package xyz.brassgoggledcoders.opentransport.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.player.EntityPlayerMPWrapper;

@SideOnly(Side.SERVER)
public class ServerProxy extends CommonProxy {
    public EntityPlayer getEntityPlayerWrapper(EntityPlayer entityPlayer, IHolderEntity containerHolder) {
        if (entityPlayer instanceof EntityPlayerMP) {
            return new EntityPlayerMPWrapper((EntityPlayerMP) entityPlayer, containerHolder);
        }
        OpenTransport.instance.getLogger()
                .fatal("EntityPlayer(" + entityPlayer.getClass().toString() + ") not Wrapped.");
        return null;
    }
}
