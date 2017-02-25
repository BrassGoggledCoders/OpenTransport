package xyz.brassgoggledcoders.opentransport.api.wrappers.block.actions;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;

import javax.annotation.Nonnull;

import static xyz.brassgoggledcoders.opentransport.api.wrappers.block.actions.ActionType.UPDATE;

public class TileUpdateAction implements IActionListener {
    private ITickable tickable;

    public TileUpdateAction(ITickable tickable) {
        this.tickable = tickable;
    }

    @Override
    public boolean hasActionForType(@Nonnull ActionType actionType) {
        return actionType == UPDATE;
    }

    @Override
    public boolean actionOccurred(EntityPlayer entityPlayer, EnumHand hand, ItemStack itemStack, IHolderEntity holderEntity, IBlockWrapper blockWrapper) {
        this.tickable.update();
        return false;
    }
}
