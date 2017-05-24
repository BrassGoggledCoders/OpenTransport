package xyz.brassgoggledcoders.opentransport.api.wrappers.block;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraftforge.common.capabilities.Capability;
import xyz.brassgoggledcoders.opentransport.api.OpenTransportAPI;
import xyz.brassgoggledcoders.opentransport.api.entities.IHolderEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.actions.*;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.guiinterfaces.BaseInterface;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.guiinterfaces.IGuiInterface;
import xyz.brassgoggledcoders.opentransport.api.wrappers.block.rendering.RenderType;
import xyz.brassgoggledcoders.opentransport.api.wrappers.world.IWorldHarness;
import xyz.brassgoggledcoders.opentransport.api.wrappers.world.WorldHarnessEntity;
import xyz.brassgoggledcoders.opentransport.api.wrappers.world.WorldWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockWrapper implements IBlockWrapper {
    private Block block;
    private IBlockState blockState;
    private TileEntity tileEntity;
    private WorldWrapper world;
    private boolean hasTileEntity;
    private String unlocalizedName;
    private ItemStack itemStack;
    private boolean itemStackChange = true;
    private Map<ActionType, List<IActionListener>> actionListeners;
    private IGuiInterface guiInterface;
    private RenderType renderType = RenderType.VMC;
    private IHolderEntity holderEntity;

    private TileUpdateAction tileUpdateAction = null;

    public BlockWrapper(@Nonnull Block block) {
        this.block = block;
        this.blockState = block.getDefaultState();
        this.unlocalizedName = block.getUnlocalizedName().replaceFirst("tile.", "");
        this.hasTileEntity = block.hasTileEntity(this.blockState);
        this.guiInterface = new BaseInterface();
        this.actionListeners = new HashMap<>();
        for (ActionType actionType : ActionType.values()) {
            this.actionListeners.put(actionType, new ArrayList<>());
        }
        this.addActionListener(new BlockActivationAction());
        this.addActionListener(new BlockPlacedByAction());
        this.addActionListener(new BlockBreakAction());
        this.changeItemStack();
    }

    public <T extends Comparable<T>, V extends T> BlockWrapper withProperty(IProperty<T> property, V value) {
        this.blockState = this.blockState.withProperty(property, value);
        this.changeItemStack();
        this.setUnlocalizedSuffix(value.toString().toLowerCase());
        return this;
    }

    public BlockWrapper setUnlocalizedSuffix(String name) {
        return this.setUnlocalizedName(this.getBlock().getUnlocalizedName() + "." + name);
    }

    public BlockWrapper setGuiInterface(IGuiInterface guiInterface) {
        this.guiInterface = guiInterface;
        return this;
    }

    @Override
    @Nonnull
    public Block getBlock() {
        return block;
    }

    public BlockWrapper setBlock(Block block) {
        this.block = block;
        this.blockState = block.getDefaultState();
        this.changeItemStack();
        return this;
    }

    @Override
    @Nonnull
    public IBlockState getBlockState() {
        return blockState;
    }

    public void alterBlockState(IBlockState blockState) {
        this.setBlockState(blockState);
    }

    public BlockWrapper setBlockState(IBlockState blockState) {
        this.block = blockState.getBlock();
        this.blockState = blockState;
        this.hasTileEntity = this.block.hasTileEntity(this.blockState);
        this.changeItemStack();
        return this;
    }

    @Override
    @Nonnull
    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public BlockWrapper setUnlocalizedName(String name) {
        this.unlocalizedName = name.replaceFirst("tile.", "");
        return this;
    }

    @Override
    @Nonnull
    public RenderType getRenderType() {
        return renderType;
    }

    public BlockWrapper setRenderType(RenderType renderType) {
        this.renderType = renderType;
        return this;
    }

    @Override
    @Nonnull
    public Map<ActionType, List<IActionListener>> getActionListeners() {
        return actionListeners;
    }

    public BlockWrapper addActionListener(IActionListener actionListener) {
        for (ActionType actionType : ActionType.values()) {
            if(actionListener.hasActionForType(actionType)) {
                this.actionListeners.get(actionType).add(actionListener);
            }
        }
        return this;
    }

    public BlockWrapper setActionListeners(Map<ActionType, List<IActionListener>> actionListeners) {
        this.actionListeners = actionListeners;
        return this;
    }

    @Override
    @Nonnull
    public IGuiInterface getInterface() {
        return guiInterface;
    }

    @Override
    public void onPlace(EntityPlayer entityPlayer, EnumHand hand, ItemStack itemStack) {
        iterateActionListeners(ActionType.PLACED, entityPlayer, hand, itemStack);
    }

    @Override
    public boolean onInteract(EntityPlayer entityPlayer, EnumHand hand, ItemStack itemStack) {
        return iterateActionListeners(ActionType.INTERACTION, entityPlayer, hand, itemStack);
    }

    @Override
    public void onBreak() {
        iterateActionListeners(ActionType.BROKEN, null, null, null);
    }

    private boolean iterateActionListeners(@Nonnull ActionType actionType, @Nullable EntityPlayer entityPlayer,
                                           @Nullable EnumHand hand, @Nullable ItemStack itemStack) {
        boolean result = false;
        this.updateBlockWrapper();
        EntityPlayer entityPlayerWrapper = null;
        if(entityPlayer != null) {
            entityPlayerWrapper = OpenTransportAPI.getModWrapper().getPlayerWrapper(entityPlayer, this.holderEntity);
        }
        List<IActionListener> actionListeners = this.getActionListeners().get(actionType);
        for(IActionListener listener : actionListeners) {
            result |= listener.actionOccurred(entityPlayerWrapper, hand, itemStack, this.holderEntity, this);
        }
        this.updateBlockWrapper();
        return result;
    }

    @Override
    public void onUpdate() {
        this.iterateActionListeners(ActionType.UPDATE, null, null, null);
    }

    @Override
    public void markDirty() {
        updateBlockWrapper();
    }

    @Override
    public void setHolder(IHolderEntity holderEntity) {
        this.holderEntity = holderEntity;
        this.setWorldHarness(new WorldHarnessEntity(holderEntity));
    }

    @Override
    public void setWorldHarness(IWorldHarness worldHarness) {
        this.world = new WorldWrapper(worldHarness);
        this.getTileEntity();
    }

    @Override
    public boolean hasTileEntity() {
        return hasTileEntity;
    }

    @Override
    public TileEntity getTileEntity() {
        if (this.tileEntity == null && this.hasTileEntity()) {
            this.tileEntity = this.getBlock().createTileEntity(this.world, this.getBlockState());
            this.tileEntity.setWorld(this.world);
            if(this.tileEntity instanceof ITickable && tileUpdateAction == null) {
                this.tileUpdateAction = new TileUpdateAction((ITickable)this.getTileEntity());
                this.addActionListener(tileUpdateAction);
            }
        }
        return this.tileEntity;
    }

    @Override
    public void setWorldWrapper(WorldWrapper worldWrapper) {
        this.world = worldWrapper;
    }

    @Override
    public WorldWrapper getWorldWrapper() {
        return world;
    }

    @Override
    public ItemStack getItemStack() {
        return this.itemStack;
    }

    public BlockWrapper setItemStack(ItemStack itemStack) {
        return setItemStack(itemStack, true);
    }

    public BlockWrapper setItemStack(ItemStack itemStack, boolean changeWithState) {
        this.itemStack = itemStack;
        this.itemStackChange = changeWithState;
        return this;
    }

    public void changeItemStack() {
        if(this.itemStackChange) {
            Item item = Item.getItemFromBlock(block);
            if(item != null) {
                this.itemStack = new ItemStack(item, 1, block.getMetaFromState(this.blockState));
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tagCompound) {
        tagCompound.setInteger("BLOCK_STATE", this.block.getMetaFromState(this.getBlockState()));
        if (world != null && this.hasTileEntity()) {
            tagCompound.setTag("TILE_DATA", this.getTileEntity().writeToNBT(new NBTTagCompound()));
        }
        return tagCompound;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void readFromNBT(NBTTagCompound tagCompound) {
        this.setBlockState(this.block.getStateFromMeta(tagCompound.getInteger("BLOCK_STATE")));
        if (world != null && this.getTileEntity() != null) {
            if (tagCompound.hasKey("TILE_DATA")) {
                this.getTileEntity().readFromNBT(tagCompound.getCompoundTag("TILE_DATA"));
            }
        }
    }

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return this.hasTileEntity && this.getTileEntity().hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Override
    @Nonnull
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if(this.hasTileEntity) {
            return this.getTileEntity().getCapability(capability, facing);
        }
        return null;
    }

    @Override
    public BlockWrapper copy() {
        BlockWrapper copyBlockWrapper = new BlockWrapper(this.getBlock());
        copyBlockWrapper.setBlockState(this.getBlockState()).setActionListeners(this.getActionListeners())
                .setGuiInterface(this.getInterface()).setRenderType(this.getRenderType())
                .setUnlocalizedName(this.getUnlocalizedName()).setItemStack(this.getItemStack(), this.itemStackChange);
        return copyBlockWrapper;
    }

    private void updateBlockWrapper() {
        OpenTransportAPI.getModWrapper().sendBlockWrapperPacket(this.holderEntity);
    }

    public void register() {
        OpenTransportAPI.getBlockWrapperRegistry().registerWrapper(this);
    }
}
