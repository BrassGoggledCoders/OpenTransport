package xyz.brassgoggledcoders.opentransport.renderers;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface IItemTESRAccessor<ITEM extends Item> {
    void setTESRItemStack(ITEM item, ItemStack itemStack);

    void setCameraTransformType(TransformType transformType);
}
