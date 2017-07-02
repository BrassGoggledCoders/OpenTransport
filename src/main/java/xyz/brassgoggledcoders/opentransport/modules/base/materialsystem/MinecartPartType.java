package xyz.brassgoggledcoders.opentransport.modules.base.materialsystem;

import com.google.common.collect.Maps;
import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.materialsystem.parttype.PartType;
import com.teamacronymcoders.base.registrysystem.EntityRegistry;
import com.teamacronymcoders.base.registrysystem.IRegistryHolder;
import com.teamacronymcoders.base.registrysystem.ItemRegistry;
import xyz.brassgoggledcoders.opentransport.modules.base.entities.EntityMaterialMinecart;
import xyz.brassgoggledcoders.opentransport.modules.base.items.ItemMaterialMinecart;

import javax.annotation.Nonnull;
import java.util.Map;

public class MinecartPartType extends PartType {
    private Map<String, ItemMaterialMinecart> modsSetup = Maps.newHashMap();

    public MinecartPartType() {
        super("minecart");
    }

    public void setup(@Nonnull MaterialPart materialPart) {
        MaterialUser user = materialPart.getMaterialUser();
        if (!modsSetup.containsKey(user.getId())) {
            IRegistryHolder registryHolder = user.getMod().getRegistryHolder();
            ItemMaterialMinecart itemMaterialMinecart = new ItemMaterialMinecart();
            registryHolder.getRegistry(ItemRegistry.class, "ITEM").register(new ItemMaterialMinecart());
            registryHolder.getRegistry(EntityRegistry.class, "ENTITY").register(EntityMaterialMinecart.class);
            modsSetup.put(user.getId(), itemMaterialMinecart);
        }
    }
}
