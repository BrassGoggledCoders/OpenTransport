package xyz.brassgoggledcoders.opentransport.modules.manasteam;


import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.state.BotaniaStateProps;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.mana.TilePump;
import xyz.brassgoggledcoders.opentransport.modules.manasteam.capabilities.IManaHolder;

public class ManaUtils {
    //Modified from EntityPoolCart from Botania
    public static void tryLoadMana(IManaHolder holder, BlockPos blockPos, World world) {
        for (EnumFacing dir : EnumFacing.HORIZONTALS) {
            BlockPos pumpPos = blockPos.offset(dir);
            Block pumpBlock = world.getBlockState(pumpPos).getBlock();
            if (pumpBlock == ModBlocks.pump) {
                BlockPos poolPos = pumpPos.offset(dir);
                TileEntity poolTile = world.getTileEntity(poolPos);
                TileEntity pumpTile = world.getTileEntity(pumpPos);

                if (poolTile != null && poolTile instanceof IManaPool && pumpTile instanceof TilePump) {
                    IManaPool pool = (IManaPool) poolTile;
                    TilePump pump = (TilePump) pumpTile;
                    EnumFacing pumpDir = world.getBlockState(pumpPos).getValue(BotaniaStateProps.CARDINALS);
                    boolean did = false;
                    boolean can = false;

                    if (pumpDir == dir) { // Pool -> Cart
                        can = true;

                        if (!pump.hasRedstone) {
                            int poolMana = pool.getCurrentMana();
                            int transfer = Math.min(holder.getTransferRate(), poolMana);
                            int transferred = holder.addMana(poolMana);
                            if (transferred > 0) {
                                pool.recieveMana(-transfer);
                                did = true;
                            }
                        }
                    } else if (pumpDir == dir.getOpposite()) { // Cart -> Pool
                        can = true;

                        if (!pump.hasRedstone && !pool.isFull()) {
                            int cartMana = holder.getMana();
                            int transfer = Math.min(holder.getTransferRate(), cartMana);
                            if (transfer > 0) {
                                pool.recieveMana(transfer);
                                holder.setMana(cartMana - transfer);
                                did = true;
                            }
                        }
                    }

                    if (did) {
                        VanillaPacketDispatcher.dispatchTEToNearbyPlayers(world, poolPos);
                        pump.hasCart = true;
                        if (!pump.active)
                            pump.setActive(true);
                    }

                    if (can) {
                        pump.hasCartOnTop = true;
                        pump.comparator = (int) ((double) holder.getMana() / (double) holder.getMaxMana() * 15);
                    }

                }
            }
        }
    }

    public static boolean findExoFlame(BlockPos railPos, World world) {
        //TODO Figure out way to check for ExoFlame
        return false;
    }
}
