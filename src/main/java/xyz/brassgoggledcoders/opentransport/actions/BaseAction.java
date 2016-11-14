package xyz.brassgoggledcoders.opentransport.actions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.ActionType;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IActionListener;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.wrappers.world.WorldWrapper;

public class BaseAction implements IActionListener {
    protected WorldWrapper getWorldWrapper(IHolderEntity holderEntity) {
        return new WorldWrapper(holderEntity);
    }

    @Override
    public boolean actionOccurred(ActionType actionType, EntityPlayer entityPlayer, EnumHand hand, ItemStack itemStack,
                                  IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        return false;
    }
}
