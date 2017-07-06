package xyz.brassgoggledcoders.opentransport.boats.items;

import com.teamacronymcoders.base.client.models.IHasModel;
import com.teamacronymcoders.base.items.boats.ItemBoatBase;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityBoat.Type;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.opentransport.api.OpenTransportAPI;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.boats.BoatTransport;
import xyz.brassgoggledcoders.opentransport.boats.entities.EntityBoatHolder;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemBoatHolder extends ItemBoatBase<EntityBoatHolder> implements IHasModel {
    boolean creativeTabSet = false;

    public ItemBoatHolder(CreativeTabs tab) {
        super("boat.holder");
        this.setCreativeTab(tab);
    }

    @Override
    @Nonnull
    public String getUnlocalizedName(@Nonnull ItemStack itemStack) {
        return this.getUnlocalizedName() + "." + this.getType(itemStack).toString().toLowerCase();
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecation")
    public String getItemStackDisplayName(@Nonnull ItemStack boatItemStack) {
        String displayName = "";

        displayName += this.getBoatItem(boatItemStack).getItemStackDisplayName(boatItemStack);

        ItemStack wrapperItemStack = this.getBlockWrapper(boatItemStack).getItemStack();
        displayName += " " + I18n.translateToLocal("separator.with") + " ";
        displayName += wrapperItemStack.getItem().getItemStackDisplayName(wrapperItemStack);

        return displayName;
    }

    @Override
    @Nonnull
    public Item setCreativeTab(@Nonnull CreativeTabs tab) {
        if (!creativeTabSet) {
            super.setCreativeTab(tab);
            this.creativeTabSet = true;
        }
        return this;
    }

    public IBlockWrapper getBlockWrapper(ItemStack itemStack) {
        return OpenTransportAPI.getBlockWrapperRegistry().getLoadedBlockWrapper(itemStack);
    }

    @Override
    public EntityBoatHolder getBoatToPlace(World world, ItemStack itemStack) {
        EntityBoatHolder entityBoatHolder = new EntityBoatHolder(world);
        entityBoatHolder.setItemBoat(itemStack);
        entityBoatHolder.setBlockWrapper(this.getBlockWrapper(itemStack).copy());
        return entityBoatHolder;
    }

    @Override
    public void onPlace(EntityBoatHolder entityBoatHolder, EntityPlayer entityPlayer, EnumHand hand, ItemStack itemStack) {
        entityBoatHolder.getBlockWrapper().onPlace(entityPlayer, hand, itemStack);
    }

    public Item getBoatItem(ItemStack itemStack) {
        Type type = this.getType(itemStack);
        Item itemBoat = Items.BOAT;
        switch (type) {
            case ACACIA:
                itemBoat = Items.ACACIA_BOAT;
                break;
            case BIRCH:
                itemBoat = Items.BIRCH_BOAT;
                break;
            case DARK_OAK:
                itemBoat = Items.DARK_OAK_BOAT;
                break;
            case JUNGLE:
                itemBoat = Items.JUNGLE_BOAT;
                break;
            case SPRUCE:
                itemBoat = Items.SPRUCE_BOAT;
                break;
        }
        return itemBoat;
    }

    @Override
    public List<ItemStack> getAllSubItems(List<ItemStack> itemStacks) {
        OpenTransportAPI.getBlockWrapperRegistry().getAllBlockWrappers().forEach((name, blockWrapper) -> {
            for (Type type : Type.values()) {
                itemStacks.add(getItemStackForBlockWrapper(type, blockWrapper));
            }
        });
        return itemStacks;
    }

    public static ItemStack getItemStackForBlockWrapper(Type type, IBlockWrapper blockWrapper) {
        ItemStack itemStack = new ItemStack(BoatTransport.itemBoatHolder, 1, type.ordinal());
        NBTTagCompound nbtTagCompound = itemStack.getOrCreateSubCompound("blockWrapper");
        nbtTagCompound.setString("name", blockWrapper.getUnlocalizedName());
        return itemStack;
    }

    @Override
    public Item getItem() {
        return this;
    }
}
