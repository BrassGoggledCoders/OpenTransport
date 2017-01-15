package xyz.brassgoggledcoders.opentransport.api.wrappers.block.actions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.wrappers.world.WorldHarnessEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.world.WorldWrapper;

public class BaseAction implements IActionListener {
    protected WorldWrapper getWorldWrapper(IHolderEntity holderEntity) {
        return new WorldWrapper(new WorldHarnessEntity(holderEntity));
    }

    @Override
    public boolean actionOccurred(ActionType actionType, EntityPlayer entityPlayer, EnumHand hand, ItemStack itemStack,
                                  IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        return false;
    }
}
