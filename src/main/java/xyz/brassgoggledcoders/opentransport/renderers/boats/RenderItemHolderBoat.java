package xyz.brassgoggledcoders.opentransport.renderers.boats;

import net.minecraft.client.Minecraft;
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
			ItemBoatHolder itemBoatHolder = (ItemBoatHolder)item;
			IBlockContainer blockContainer = itemBoatHolder.getBlockContainer(itemStack);
			blockContainer.setHolder(boatHolder);
			boatHolder.setBlockContainer(blockContainer);
			renderBlock.renderEntity(ClientHelper.player(), blockContainer, 0);
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("textures/entity/boat/boat_oak.png"));
			modelBoat.render(ClientHelper.player(), 0, 0, 0, 0, 0, 0.5F);
		}
	}
}
