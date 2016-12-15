package xyz.brassgoggledcoders.opentransport.modules.manasteam.items;

import com.teamacronymcoders.base.util.BlockUtils;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.brassgoggledcoders.opentransport.modules.manasteam.entities.EntityManaSteamLocomotive;

import javax.annotation.Nonnull;

public class ItemManaSteamLocomotive extends ItemMinecart {
    public ItemManaSteamLocomotive() {
        super(EntityMinecart.Type.TNT);
        setUnlocalizedName("manasteam_locomotive");
        setMaxStackSize(1);
    }

    @Override
    @Nonnull
    public EnumActionResult onItemUse(@Nonnull ItemStack itemStack, EntityPlayer player, World world,
                                      @Nonnull BlockPos blockPos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return placeCart(itemStack, world, blockPos, new EntityManaSteamLocomotive(world));
    }

    public EnumActionResult placeCart(ItemStack itemStack, World world, BlockPos blockPos, EntityMinecart entityMinecart) {
        if (itemStack != null && BlockUtils.isRailBlock(world.getBlockState(blockPos))) {
            if (itemStack.hasDisplayName()) {
                entityMinecart.setCustomNameTag(itemStack.getDisplayName());
            }

            if (!world.isRemote) {
                entityMinecart.posX = (float) blockPos.getX() + 0.5F;
                entityMinecart.posY = (float) blockPos.getY() + 0.5F;
                entityMinecart.posZ = (float) blockPos.getZ() + 0.5F;
                world.spawnEntityInWorld(entityMinecart);
            }
            --itemStack.stackSize;
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }
}
