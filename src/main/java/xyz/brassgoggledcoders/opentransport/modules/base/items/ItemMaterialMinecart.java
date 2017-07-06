package xyz.brassgoggledcoders.opentransport.modules.base.items;

import com.teamacronymcoders.base.entities.EntityMinecartBase;
import com.teamacronymcoders.base.items.IHasItemColor;
import com.teamacronymcoders.base.items.minecarts.ItemMinecartBase;
import com.teamacronymcoders.base.materialsystem.MaterialSystem;
import com.teamacronymcoders.base.materialsystem.MaterialUser;
import com.teamacronymcoders.base.materialsystem.capabilities.IMaterialPartHolder;
import com.teamacronymcoders.base.materialsystem.capabilities.MaterialPartHolderProvider;
import com.teamacronymcoders.base.materialsystem.materialparts.MaterialPart;
import com.teamacronymcoders.base.util.CapUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import xyz.brassgoggledcoders.opentransport.modules.base.BaseModule;
import xyz.brassgoggledcoders.opentransport.modules.base.entities.EntityMaterialMinecart;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

import static com.teamacronymcoders.base.materialsystem.capabilities.MaterialPartCapability.MATERIAL_PART_HOLDER;

public class ItemMaterialMinecart extends ItemMinecartBase implements IHasItemColor {
    public ItemMaterialMinecart() {
        super("material");
    }

    @Nonnull
    @Override
    public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack) {
        IMaterialPartHolder holder = CapUtils.get(itemStack, MATERIAL_PART_HOLDER);
        MaterialPart materialPart = MaterialSystem.MISSING_MATERIAL_PART;
        if (holder != null) {
            materialPart = holder.getMaterialPart();
        }
        return new EntityMaterialMinecart(world, materialPart);
    }

    @Override
    public List<ItemStack> getAllSubItems(List<ItemStack> itemStacks) {
        MaterialUser user = this.getMod().getMaterialUser();
        if (user != null) {
            user.getMaterialParts().values().stream().filter(materialPart ->
                    materialPart.matchesPartType(BaseModule.minecartPartType)).forEach(materialPart ->
                    itemStacks.add(new ItemStack(this, 1, materialPart.getId())));
        }
        return itemStacks;
    }

    @Override
    public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable NBTTagCompound nbt) {
        MaterialUser user = this.getMod().getMaterialUser();
        MaterialPart materialPart = MaterialSystem.MISSING_MATERIAL_PART;
        if (user != null) {
            materialPart = user.getMaterialPart(stack.getItemDamage());
        }
        return new MaterialPartHolderProvider(materialPart);
    }

    @Override
    public int getColorFromItemstack(@Nonnull ItemStack itemStack, int tintIndex) {
        IMaterialPartHolder holder = CapUtils.get(itemStack, MATERIAL_PART_HOLDER);
        return holder != null ? holder.getMaterialPart().getColor() : -1;
    }
}
