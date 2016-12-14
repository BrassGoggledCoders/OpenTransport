package xyz.brassgoggledcoders.opentransport.modules.manasteam.entities;

import mods.railcraft.api.carts.CartToolsAPI;
import mods.railcraft.api.carts.locomotive.LocomotiveRenderType;
import mods.railcraft.common.carts.EntityLocomotiveSteam;
import mods.railcraft.common.carts.IRailcraftCartContainer;
import mods.railcraft.common.carts.RailcraftCarts;
import mods.railcraft.common.fluids.Fluids;
import mods.railcraft.common.util.misc.Game;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import vazkii.botania.common.block.tile.mana.TilePool;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.modules.manasteam.ManaFuelProvider;
import xyz.brassgoggledcoders.opentransport.modules.manasteam.ManaUtils;
import xyz.brassgoggledcoders.opentransport.modules.manasteam.capabilities.IManaHolder;
import xyz.brassgoggledcoders.opentransport.modules.manasteam.capabilities.ManaHolder;

import javax.annotation.Nonnull;

public class EntityManaSteamLocomotive extends EntityLocomotiveSteam {
    private static final String MANA_NBT = "mana";
    private static final DataParameter<Integer> MANA =
            EntityDataManager.createKey(EntityManaSteamLocomotive.class, DataSerializers.VARINT);

    private IManaHolder manaHolder;
    private ManaFuelProvider manaFuel;
    private IInventory ticketInventory;

    public EntityManaSteamLocomotive(World world) {
        super(world);
        manaHolder = new ManaHolderLocomotive(TilePool.MAX_MANA, 10000);
        manaFuel = new ManaFuelProvider(manaHolder);
        boiler.setFuelProvider(manaFuel);
        ticketInventory = new InventoryBasic("Tickets", true, 1);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(MANA, 0);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();
        if (Game.isHost(worldObj)) {
            if (isSafeToFill() && tankWater.getFluidAmount() < tankWater.getCapacity() / 2) {
                FluidStack pulled = CartToolsAPI.transferHelper.pullFluid(this, Fluids.WATER.getB(1));
                if (pulled != null)
                    tankWater.fill(pulled, true);
            }
        }
    }

    @Override
    public void moveMinecartOnRail(BlockPos railPos) {
        super.moveMinecartOnRail(railPos);
        ManaUtils.tryLoadMana(this.manaHolder, railPos, this.getEntityWorld());
        this.manaFuel.setNearExoFlame(ManaUtils.findExoFlame(railPos, this.getEntityWorld()));
    }

    @Override
    public void writeEntityToNBT(@Nonnull NBTTagCompound nbtTagCompound) {
        super.writeEntityToNBT(nbtTagCompound);
        nbtTagCompound.setTag(MANA_NBT, manaHolder.serializeNBT());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbtTagCompound) {
        super.readEntityFromNBT(nbtTagCompound);
        this.manaHolder = new ManaHolderLocomotive();
        this.manaHolder.deserializeNBT(nbtTagCompound.getCompoundTag(MANA_NBT));
    }

    @Override
    @Nonnull
    protected IInventory getTicketInventory() {
        return ticketInventory;
    }

    @Override
    public boolean needsFuel() {
        boolean needsMana = this.manaHolder.getMana() < (this.manaHolder.getMaxMana() / 4.0);
        FluidStack water = tankWater.getFluid();
        boolean needsWater = water == null || water.amount < tankWater.getCapacity() / 3;
        return needsMana || needsWater;
    }

    private class ManaHolderLocomotive extends ManaHolder {
        public ManaHolderLocomotive() {
            super();
        }

        public ManaHolderLocomotive(int maxMana, int transfer) {
            super(maxMana, transfer);
        }

        @Override
        public void setMana(int mana) {
            EntityManaSteamLocomotive.this.getDataManager().set(MANA, mana);
        }

        @Override
        public int getMana() {
            return EntityManaSteamLocomotive.this.getDataManager().get(MANA);
        }
    }

    /***** Useless Railcraft Methods *****/
    @Override
    @Nonnull
    public IRailcraftCartContainer getCartType() {
        OpenTransport.instance.getLogger().fatal("Call to Mana-Steam getCartType()");
        return RailcraftCarts.TNT;
    }

    @Override
    @Nonnull
    public LocomotiveRenderType getRenderType() {
        OpenTransport.instance.getLogger().fatal("Call to Mana-Steam getRenderType()");
        return LocomotiveRenderType.STEAM_SOLID;
    }

    @Override
    protected void openGui(@Nonnull EntityPlayer entityPlayer) {
        //NO GUIS
    }
}
