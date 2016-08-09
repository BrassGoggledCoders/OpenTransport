package xyz.brassgoggledcoders.opentransport.minecarts.renderers;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelMinecart;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.client.ClientHelper;
import xyz.brassgoggledcoders.boilerplate.client.renderers.custom.IItemRenderingHandler;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IBlockContainer;
import xyz.brassgoggledcoders.opentransport.minecarts.entities.EntityMinecartHolder;
import xyz.brassgoggledcoders.opentransport.minecarts.items.ItemMinecartHolder;
import xyz.brassgoggledcoders.opentransport.renderers.RenderBlock;

public class RenderItemHolderMinecart implements IItemRenderingHandler {
	private RenderBlock renderBlock;
	private EntityMinecartHolder minecartHolder;
	private ModelMinecart modelMinecart;
	private ResourceLocation minecartTexture = new ResourceLocation("textures/entity/minecart.png");

	public RenderItemHolderMinecart() {
		renderBlock = new RenderBlock();
		modelMinecart = new ModelMinecart();
	}

	@Override
	public void render(World world, Item item, ItemStack itemStack, TransformType type) {
		if(minecartHolder == null)  {
			minecartHolder = new EntityMinecartHolder(Minecraft.getMinecraft().theWorld);
		}
		if(minecartHolder.getEntity().worldObj != null && item instanceof ItemMinecartHolder) {
			ItemMinecartHolder itemMinecartHolder = (ItemMinecartHolder) item;
			IBlockContainer blockContainer = itemMinecartHolder.getBlockContainer(itemStack);
			blockContainer.setHolder(minecartHolder);
			minecartHolder.setBlockContainer(blockContainer);

			GlStateManager.pushMatrix();
			GlStateManager.translate(.5, .55, .5);
			switch(type) {
				case GROUND:
					GlStateManager.scale(.15, .15, .15);
					break;
				case GUI:
					GlStateManager.scale(.25, .25, .25);
					GlStateManager.rotate(45, 1, -1, 0);
					break;
				case FIRST_PERSON_LEFT_HAND:
					GlStateManager.scale(.2, .2, .2);
					GlStateManager.rotate(45, 1, 0, 0);
					break;
				case FIRST_PERSON_RIGHT_HAND:
					GlStateManager.scale(.2, .2, .2);
					GlStateManager.rotate(45, 1, 0, 0);
					break;
				case THIRD_PERSON_LEFT_HAND:
					GlStateManager.scale(.15, .15, .15);
					GlStateManager.rotate(45, 1, 0, 0);
					break;
				case THIRD_PERSON_RIGHT_HAND:
					GlStateManager.scale(.15, .15, .15);
					GlStateManager.rotate(45, 1, 0, 0);
					break;
				default:
					break;
			}
			renderMinecart();
			GlStateManager.rotate(90, 0, 1, 0);
			GlStateManager.scale(1.5, 1.5, 1.5);
			GlStateManager.translate(-.5, -0.25, .5);
			renderBlockContainer(blockContainer);
			GlStateManager.popMatrix();
		}
	}

	protected void renderMinecart() {
		GlStateManager.pushMatrix();
		GlStateManager.rotate(180, 0, 0, 1);
		Minecraft.getMinecraft().renderEngine.bindTexture(minecartTexture);
		modelMinecart.render(ClientHelper.player(), 0, 0, 0, 0, 0, 0.1F);
		GlStateManager.popMatrix();
	}

	protected void renderBlockContainer(IBlockContainer blockContainer) {
		renderBlock.renderEntity(ClientHelper.player(), blockContainer, 0);
	}
}