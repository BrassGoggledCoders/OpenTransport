package xyz.brassgoggledcoders.opentransport.module.base;

import com.teamacronymcoders.base.materialsystem.entity.minecart.EntityMaterialMinecart;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.opentransport.api.entity.minecart.CustomMinecart;

public class MaterialCustomMinecart extends CustomMinecart {
    private MaterialPart materialPart;

    public MaterialCustomMinecart(MaterialPart materialPart) {
        this.materialPart = materialPart;
    }

    @Override
    public ItemStack getItemMinecart() {
        return materialPart.getItemStack();
    }

    @Override
    public EntityMinecart getEmptyMinecart(World world) {
        return new EntityMaterialMinecart(world, materialPart);
    }

    @Override
    public void renderMinecartModel(EntityMinecart entityMinecart) {

    }
}
