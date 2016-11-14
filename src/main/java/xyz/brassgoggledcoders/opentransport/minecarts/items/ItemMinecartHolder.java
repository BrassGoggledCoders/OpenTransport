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
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.minecarts.entities.EntityMinecartHolder;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemMinecartHolder extends ItemMinecartBase /*implements IHasItemRenderHandler*/ {
    private IBlockWrapper blockWrapper;

    public ItemMinecartHolder(IBlockWrapper blockWrapper, CreativeTabs creativeTabs) {
        super("minecart.holder." + blockWrapper.getUnlocalizedName());
        this.setCreativeTab(creativeTabs);
        this.blockWrapper = blockWrapper;
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
    public String getItemStackDisplayName(@Nonnull ItemStack itemStack) {
        String displayName = "";

        displayName += Items.MINECART.getItemStackDisplayName(itemStack);

        Item blockItem = Item.getItemFromBlock(this.blockWrapper.getBlock());
        if (blockItem != null) {
            displayName += " " + I18n.format("separator.with") + " ";
            displayName += blockItem.getItemStackDisplayName(itemStack);
        }

        return displayName;
    }

    public IBlockWrapper getBlockWrapper(ItemStack itemStack) {
        return this.blockWrapper;
    }

    @Nonnull
    @Override
    public EntityMinecartHolder getEntityFromItem(World world, ItemStack itemStack) {
        EntityMinecartHolder minecart = new EntityMinecartHolder(world);
        minecart.setBlockWrapper(blockWrapper);
        minecart.setItemCart(itemStack);
        return minecart;
    }

    @Override
    public List<String> getModelNames(List<String> modelNames) {
        modelNames.add("minecart");
        return modelNames;
    }

    //TODO RENDERING
    /*@Override
    public String itemRenderPath() {
		return "xyz.brassgoggledcoders.opentransport.minecarts.renderers.RenderItemHolderMinecart";
	}*/
}
