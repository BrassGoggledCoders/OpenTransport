package xyz.brassgoggledcoders.opentransport.minecarts.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import xyz.brassgoggledcoders.boilerplate.client.renderers.custom.IHasItemRenderHandler;
import xyz.brassgoggledcoders.boilerplate.entity.minecarts.EntityMinecartBase;
import xyz.brassgoggledcoders.boilerplate.items.minecarts.ItemMinecartBase;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IBlockContainer;
import xyz.brassgoggledcoders.opentransport.minecarts.entities.EntityMinecartHolder;

import javax.annotation.Nonnull;

public class ItemMinecartHolder extends ItemMinecartBase implements IHasItemRenderHandler {
	private IBlockContainer blockContainer;

	public ItemMinecartHolder(IBlockContainer blockContainer, CreativeTabs creativeTabs) {
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

	public IBlockContainer getBlockContainer(ItemStack itemStack) {
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
	public String[] getResourceLocations()
	{
		return new String[] {"minecart"};
	}

	@Override
	public String itemRenderPath() {
		return "xyz.brassgoggledcoders.opentransport.minecarts.renderers.RenderItemHolderMinecart";
	}
}
