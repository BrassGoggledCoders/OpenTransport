package xyz.brassgoggledcoders.opentransport.minecarts.items;

import com.teamacronymcoders.base.entity.EntityMinecartBase;
import com.teamacronymcoders.base.items.minecarts.ItemMinecartBase;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.brassgoggledcoders.opentransport.api.blockwrappers.IBlockWrapper;
import xyz.brassgoggledcoders.opentransport.minecarts.entities.EntityMinecartHolder;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class ItemMinecartHolder extends ItemMinecartBase /*implements IHasItemRenderHandler*/ {
	private IBlockWrapper blockContainer;

	public ItemMinecartHolder(IBlockWrapper blockContainer, CreativeTabs creativeTabs) {
		super("minecart.holder." + blockContainer.getUnlocalizedName());
		this.setCreativeTab(creativeTabs);
		this.blockContainer = blockContainer;
	}

	@Override
	@Nonnull
	@SideOnly(Side.CLIENT)
	public String getItemStackDisplayName(@Nonnull ItemStack itemStack) {
		String displayName = "";

		displayName += Items.MINECART.getItemStackDisplayName(itemStack);

		Item blockItem = Item.getItemFromBlock(this.blockContainer.getBlock());
		if(blockItem != null) {
			displayName += " " + I18n.format("separator.with") + " ";
			displayName += blockItem.getItemStackDisplayName(itemStack);
		}

		return displayName;
	}

	public IBlockWrapper getBlockContainer(ItemStack itemStack) {
		return this.blockContainer;
	}

	@Nonnull
	@Override
	public EntityMinecartBase getEntityFromItem(World world, ItemStack itemStack) {
		EntityMinecartHolder minecart = new EntityMinecartHolder(world);
		minecart.setBlockContainer(blockContainer);
		minecart.setItemCart(itemStack);
		return minecart;
	}

	@Override
	public List<String> getModelNames() {
		List<String> modelNames = new ArrayList<>();
        modelNames.add("minecart");
        return modelNames;
	}

	//TODO RENDERING
	/*@Override
	public String itemRenderPath() {
		return "xyz.brassgoggledcoders.opentransport.minecarts.renderers.RenderItemHolderMinecart";
	}*/
}
