package xyz.brassgoggledcoders.opentransport.module.base;

import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.entity.minecart.CustomMinecart;
import xyz.brassgoggledcoders.opentransport.module.base.entity.EntityMaterialMinecart;
import xyz.brassgoggledcoders.opentransport.module.base.item.ItemMaterialMinecart;

public class MaterialCustomMinecart extends CustomMinecart {
    @ObjectHolder(OpenTransport.ID + ":minecart_material")
    private static ItemMaterialMinecart itemMinecartMaterial;

    private MaterialPart materialPart;

    public MaterialCustomMinecart(MaterialPart materialPart) {
        this.materialPart = materialPart;
    }

    @Override
    public ItemStack getItemMinecart() {
        return new ItemStack(itemMinecartMaterial, 1, materialPart.getId());
    }

    @Override
    public EntityMinecart getEmptyMinecart(World world) {
        return new EntityMaterialMinecart(world, materialPart, this.getItemMinecart());
    }

    @Override
    public void renderMinecartModel(EntityMinecart entityMinecart) {

    }
}
