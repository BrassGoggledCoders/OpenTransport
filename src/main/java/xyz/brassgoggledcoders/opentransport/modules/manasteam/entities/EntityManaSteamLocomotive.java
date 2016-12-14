package xyz.brassgoggledcoders.opentransport.modules.manasteam.entities;

import mods.railcraft.api.carts.locomotive.LocomotiveRenderType;
import mods.railcraft.common.carts.EntityLocomotiveSteam;
import mods.railcraft.common.carts.IRailcraftCartContainer;
import mods.railcraft.common.carts.RailcraftCarts;
import mods.railcraft.common.util.steam.IFuelProvider;
import mods.railcraft.common.util.steam.Steam;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import vazkii.botania.common.block.tile.mana.TilePool;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.modules.manasteam.capabilities.IManaHolder;
import xyz.brassgoggledcoders.opentransport.modules.manasteam.capabilities.ManaHolder;

import javax.annotation.Nonnull;

public class EntityManaSteamLocomotive extends EntityLocomotiveSteam {
    private static final String MANA_NBT = "mana";
    private static final DataParameter<Integer> MANA =
            EntityDataManager.createKey(EntityManaSteamLocomotive.class, DataSerializers.VARINT);

    private IManaHolder manaHolder = new ManaHolderLocomotive(TilePool.MAX_MANA, 10000);
    private boolean nearExoFlame = false;

    public EntityManaSteamLocomotive(World world) {
        super(world);
        boiler.setFuelProvider(new IFuelProvider() {
            IManaHolder manaHolder = EntityManaSteamLocomotive.this.manaHolder;
            @Override
            public double getMoreFuel() {
                int burnTime = 0;
                if(manaHolder.getMana() > 100) {
                    manaHolder.setMana(manaHolder.getMana() - 100);
                    burnTime = 50;
                }
                return burnTime;
            }

            @Override
            public double getHeatStep() {
                return nearExoFlame ? Steam.HEAT_STEP * 30 : Steam.HEAT_STEP;
            }
        });
    }


    @Override
    protected void entityInit() {
        super.entityInit();
        dataManager.register(MANA, 0);
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
        return null;
    }

    @Override
    public boolean needsFuel() {
        return this.manaHolder.getMana() < (this.manaHolder.getMaxMana() / 4.0);
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
