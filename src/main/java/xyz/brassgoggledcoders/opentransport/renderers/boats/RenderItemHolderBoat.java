package xyz.brassgoggledcoders.opentransport.renderers.boats;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.boilerplate.client.ClientHelper;
import xyz.brassgoggledcoders.boilerplate.client.renderers.custom.IItemRenderingHandler;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IBlockContainer;
import xyz.brassgoggledcoders.opentransport.entities.boats.EntityBoatHolder;
import xyz.brassgoggledcoders.opentransport.items.boats.ItemBoatHolder;
import xyz.brassgoggledcoders.opentransport.models.boats.ModelBoatNoPaddles;
import xyz.brassgoggledcoders.opentransport.renderers.RenderBlock;

public class RenderItemHolderBoat implements IItemRenderingHandler {
	private static final String shortCut = "textures/entity/boat/boat_";
	private static final ResourceLocation[] BOAT_TEXTURES = new ResourceLocation[] {
		new ResourceLocation(shortCut + "oak.png"), new ResourceLocation(shortCut + "spruce.png"),
		new ResourceLocation(shortCut + "birch.png"), new ResourceLocation(shortCut + "jungle.png"),
		new ResourceLocation(shortCut + "acacia.png"), new ResourceLocation(shortCut + "darkoak.png")};

	private RenderBlock renderBlock;
	private EntityBoatHolder boatHolder;
	private ModelBoatNoPaddles modelBoat;

	public RenderItemHolderBoat() {
		renderBlock = new RenderBlock();
		modelBoat = new ModelBoatNoPaddles();
	}

	@Override
	public void render(World world, Item item, ItemStack itemStack, ItemCameraTransforms.TransformType type) {
		if(boatHolder == null)  {
			boatHolder = new EntityBoatHolder(Minecraft.getMinecraft().theWorld);
		}
		if(boatHolder.getEntity().worldObj != null && item instanceof ItemBoatHolder) {
			GlStateManager.pushMatrix();
			ItemBoatHolder itemBoatHolder = (ItemBoatHolder)item;
			IBlockContainer blockContainer = itemBoatHolder.getBlockContainer(itemStack);
			blockContainer.setHolder(boatHolder);
			boatHolder.setBlockContainer(blockContainer);
			switch(type) {
				case GROUND:
					GlStateManager.translate(.5, .55, .5);
					GlStateManager.scale(.15, .15, .15);
					renderBoat(itemStack.getItemDamage());
					GlStateManager.rotate(90, 0, 1, 0);
					GlStateManager.scale(1.5, 1.5, 1.5);
					GlStateManager.translate(-.5, -0.25, .5);
					renderBlockContainer(blockContainer);
					break;
				case GUI:
					GlStateManager.translate(.5, .55, .5);
					GlStateManager.scale(.15, .15, .15);
					renderBoat(itemStack.getItemDamage());
					GlStateManager.rotate(90, 0, 1, 0);
					GlStateManager.scale(1.5, 1.5, 1.5);
					GlStateManager.translate(-.5, -0.25, .5);
					renderBlockContainer(blockContainer);
					break;
				case FIRST_PERSON_LEFT_HAND:
					break;
				case FIRST_PERSON_RIGHT_HAND:
					break;
				case THIRD_PERSON_LEFT_HAND:
					break;
				case THIRD_PERSON_RIGHT_HAND:
					break;
				default:
					break;
			}
			GlStateManager.popMatrix();
		}
	}

	protected void renderBoat(int boatNumber) {
		GlStateManager.pushMatrix();
		GlStateManager.rotate(180, 0, 0, 1);
		Minecraft.getMinecraft().renderEngine.bindTexture(BOAT_TEXTURES[boatNumber]);
		modelBoat.render(ClientHelper.player(), 0, 0, 0, 0, 0, 0.1F);
		GlStateManager.popMatrix();
	}

	protected void renderBlockContainer(IBlockContainer blockContainer) {
		renderBlock.renderEntity(ClientHelper.player(), blockContainer, 0);
	}
}
