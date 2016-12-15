package xyz.brassgoggledcoders.opentransport.proxies;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.player.EntityPlayerSPWrapper;

public class ClientProxy extends CommonProxy {
    @Override
    public EntityPlayer getEntityPlayerWrapper(EntityPlayer entityPlayer, IHolderEntity containerHolder) {
        if (entityPlayer instanceof EntityPlayerSP) {
            return new EntityPlayerSPWrapper((EntityPlayerSP) entityPlayer, containerHolder);
        }
        return super.getEntityPlayerWrapper(entityPlayer, containerHolder);
    }

    @Override
    public World getWorld(MessageContext ctx) {
        return Minecraft.getMinecraft().theWorld;
    }

    @Override
    public IThreadListener getIThreadListener(MessageContext messageContext) {
        return Minecraft.getMinecraft();
    }

    @Override
    public void registerEntityRenders() {

    }
}
