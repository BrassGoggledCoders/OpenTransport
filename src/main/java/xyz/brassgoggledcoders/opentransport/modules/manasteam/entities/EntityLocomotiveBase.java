package xyz.brassgoggledcoders.opentransport.modules.manasteam.entities;

import com.google.common.base.Optional;
import com.mojang.authlib.GameProfile;
import com.teamacronymcoders.base.entity.EntityMinecartBase;
import com.teamacronymcoders.base.util.ItemStackUtils;
import mods.railcraft.api.carts.CartToolsAPI;
import mods.railcraft.api.carts.IRoutableCart;
import mods.railcraft.common.items.ItemTicket;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class EntityLocomotiveBase extends EntityMinecartBase implements IRoutableCart{
    private static final DataParameter<String> DESTINATION =
            EntityDataManager.createKey(EntityLocomotiveBase.class, DataSerializers.STRING);
    private static final DataParameter<Optional<ItemStack>> TICKET =
            EntityDataManager.createKey(EntityLocomotiveBase.class, DataSerializers.OPTIONAL_ITEM_STACK);


    public EntityLocomotiveBase(World world) {
        super(world);
    }

    public EntityLocomotiveBase(World world, double x, double y, double z) {
        this(world);
        this.setPosition(x, y, z);
    }

    @Override
    @Nonnull
    public String getDestination() {
        return this.getDataManager().get(DESTINATION);
    }

    private void setDestination(String destination) {
        this.getDataManager().set(DESTINATION, destination);
    }

    @Override
    public boolean setDestination(@Nullable ItemStack ticketStack) {
        boolean setNewDestination = false;

        if(ItemStackUtils.isItemInstanceOf(ticketStack, ItemTicket.class)) {
            String newDestination = ItemTicket.getDestination(ticketStack);
            if(newDestination.equals(this.getDestination())) {
                this.setDestination(newDestination);
                this.setTicket(ItemTicket.copyTicket(ticketStack));
                setNewDestination = true;
            }
        }

        return setNewDestination;
    }

    protected void setTicket(ItemStack ticketStack) {
        this.getDataManager().set(TICKET, Optional.of(ticketStack));
    }

    protected ItemStack getTicket() {
        Optional<ItemStack> ticketStack = this.getDataManager().get(TICKET);
        return ticketStack.isPresent() ? ticketStack.get() : null;
    }

    @Override
    @Nonnull
    public GameProfile getOwner() {
        return CartToolsAPI.getCartOwner(this);
    }
}
