package xyz.brassgoggledcoders.opentransport.minecarts.items;

import com.teamacronymcoders.base.items.minecarts.ItemMinecartBase;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.api.wrappers.world.WorldWrapper;
import xyz.brassgoggledcoders.opentransport.minecarts.entities.EntityMinecartHolder;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemMinecartHolder extends ItemMinecartBase {
    private IBlockWrapper blockWrapper;
    private WorldWrapper worldWrapper;
    boolean creativeTabSet = false;

    public ItemMinecartHolder(IBlockWrapper blockWrapper, CreativeTabs creativeTabs) {
        super("minecart.holder." + blockWrapper.getUnlocalizedName());
        this.setCreativeTab(creativeTabs);
        setBlockWrapper(blockWrapper);
    }

    @Override
    @Nonnull
    public EnumActionResult onItemUse(@Nonnull ItemStack itemStack, EntityPlayer player, World world,
                                      @Nonnull BlockPos blockPos, EnumHand hand, EnumFacing facing, float hitX,
                                      float hitY, float hitZ) {
        EntityMinecartHolder entityFromItem = this.getEntityFromItem(world, itemStack);
        EnumActionResult placed = placeCart(itemStack, world, blockPos, this.getEntityFromItem(world, itemStack));
        if(placed == EnumActionResult.SUCCESS) {
            entityFromItem.getBlockWrapper().onPlace(player, hand, itemStack);
        }
        return placed;
    }

    @Override
    @Nonnull
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(@Nonnull ItemStack cartItemStack) {
        String displayName = "";

        displayName += Items.MINECART.getItemStackDisplayName(cartItemStack);

        ItemStack wrapperItemStack = this.getBlockWrapper().getItemStack();
        displayName += " " + I18n.format("separator.with") + " ";
        displayName += wrapperItemStack.getItem().getItemStackDisplayName(wrapperItemStack);

        return displayName;
    }

    public void setBlockWrapper(IBlockWrapper blockWrapper) {
        this.blockWrapper = blockWrapper;
    }

    public IBlockWrapper getBlockWrapper() {
        return this.blockWrapper;
    }

    @Nonnull
    @Override
    public EntityMinecartHolder getEntityFromItem(World world, ItemStack itemStack) {
        EntityMinecartHolder minecart = new EntityMinecartHolder(world);
        minecart.setBlockWrapper(this.getBlockWrapper());
        minecart.setItemCart(itemStack);
        return minecart;
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

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        modelNames.add("minecart.holder." + this.getBlockWrapper().getUnlocalizedName());
        return modelNames;
    }

    @Override
    public List<ItemStack> getAllSubItems(List<ItemStack> itemStacks) {
        itemStacks.add(new ItemStack(this));
        return itemStacks;
    }

    public WorldWrapper getWorldWrapper() {
        return this.worldWrapper;
    }

    public void setWorldWrapper(WorldWrapper worldWrapper) {
        this.worldWrapper = worldWrapper;
        this.blockWrapper.setWorldWrapper(worldWrapper);
    }
}
