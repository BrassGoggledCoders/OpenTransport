package xyz.brassgoggledcoders.opentransport.boats.renderers;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import xyz.brassgoggledcoders.opentransport.boats.items.ItemBoatHolder;
import xyz.brassgoggledcoders.opentransport.minecarts.renderers.RenderItemHolderMinecart;
import xyz.brassgoggledcoders.opentransport.renderers.IItemTESRAccessor;

public class RenderItemHolderBoatAccessor implements IItemTESRAccessor<ItemBoatHolder> {
    @Override
    public void setTESRItemStack(ItemBoatHolder item, ItemStack itemStack) {
        RenderItemHolderBoat.itemBoatHolder = item;
        RenderItemHolderBoat.itemStack = itemStack;
    }

    @Override
    public void setCameraTransformType(ItemCameraTransforms.TransformType transformType) {
        RenderItemHolderBoat.type = transformType;
    }
}
