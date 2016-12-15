package xyz.brassgoggledcoders.opentransport.minecarts.renderers;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import xyz.brassgoggledcoders.opentransport.minecarts.items.ItemMinecartHolder;
import xyz.brassgoggledcoders.opentransport.renderers.IItemTESRAccessor;

public class RenderItemHolderMinecartAccessor implements IItemTESRAccessor<ItemMinecartHolder> {
    @Override
    public void setTESRItemStack(ItemMinecartHolder item, ItemStack itemStack) {
        RenderItemHolderMinecart.itemMinecartHolder = item;
        RenderItemHolderMinecart.itemStackMinecartHolder = itemStack;
    }

    @Override
    public void setCameraTransformType(ItemCameraTransforms.TransformType transformType) {
        RenderItemHolderMinecart.transformType = transformType;
    }
}
