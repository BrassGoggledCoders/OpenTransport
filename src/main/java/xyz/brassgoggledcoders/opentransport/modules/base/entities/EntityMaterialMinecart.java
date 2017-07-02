package xyz.brassgoggledcoders.opentransport.modules.base.entities;

import com.teamacronymcoders.base.entities.EntityMinecartBase;
import com.teamacronymcoders.base.materialsystem.capabilities.IMaterialPartHolder;
import com.teamacronymcoders.base.materialsystem.capabilities.MaterialPartHolder;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import net.minecraft.item.ItemMinecart;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;
import xyz.brassgoggledcoders.opentransport.modules.base.items.ItemMaterialMinecart;

import javax.annotation.Nonnull;

public class EntityMaterialMinecart extends EntityMinecartBase {
    @ObjectHolder("opentransport:material_minecart")
    public static ItemMaterialMinecart materialMinecart;

    public IMaterialPartHolder materialPartHolder;

    public EntityMaterialMinecart(World world) {
        super(world);
    }

    public EntityMaterialMinecart(World world, MaterialPart materialPart) {
        super(world);
        this.materialPartHolder = new MaterialPartHolder(materialPart);
    }

    @Nonnull
    @Override
    public ItemMinecart getItem() {
        return materialMinecart;
    }

    public int getColor() {
        return this.materialPartHolder.getMaterialPart().getColor();
    }
}
