package xyz.brassgoggledcoders.opentransport.api;

import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OpenTransportAPI {
    public static Logger LOGGER = LogManager.getLogger("OpenTransport|API");

    public static final String CARRIED_BLOCK_NBT_NAME = "carried_block";
    public static final String CARRIED_BLOCK_INSTANCE_NBT_NAME = "carried_block_instance";

    public static final ResourceLocation DEFAULT_CARRIED_BLOCK = new ResourceLocation("opentransport", "minecraft_air");

    public static final ResourceLocation CARRIED_BLOCKS_NAME = new ResourceLocation("opentransport", "carried_blocks");
}
