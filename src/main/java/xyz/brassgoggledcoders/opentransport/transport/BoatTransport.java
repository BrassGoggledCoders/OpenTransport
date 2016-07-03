package xyz.brassgoggledcoders.opentransport.transport;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import xyz.brassgoggledcoders.boilerplate.BaseCreativeTab;
import xyz.brassgoggledcoders.opentransport.OpenTransport;
import xyz.brassgoggledcoders.opentransport.api.blockcontainers.IBlockContainer;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.ITransportType;
import xyz.brassgoggledcoders.opentransport.api.transporttypes.TransportType;
import xyz.brassgoggledcoders.opentransport.entities.boats.EntityBoatHolder;
import xyz.brassgoggledcoders.opentransport.items.boats.ItemBoatHolder;

import javax.annotation.Nonnull;
import java.util.Iterator;
import java.util.Map;

@TransportType
public class BoatTransport implements ITransportType<EntityBoat> {
	private CreativeTabs boatsTab = new BoatCreativeTab();
	private boolean isActive = true;

	@Override
	@Nonnull
	public String getName() {
		return "Boats";
	}

	@Override
	@Nonnull
	public Class<EntityBoat> getBaseEntity() {
		return EntityBoat.class;
	}

	@Override
	@Nonnull
	public CreativeTabs getCreativeTab() {
		return boatsTab;
	}

	@Override
	public void registerItems(Map<String, IBlockContainer> blockContainers) {
		Iterator<Map.Entry<String, IBlockContainer>> containerIterable = blockContainers.entrySet().iterator();
		while(containerIterable.hasNext()) {
			IBlockContainer firstContainer = containerIterable.next().getValue();
			IBlockContainer secondContainer = null;
			if(containerIterable.hasNext()) {
				secondContainer = containerIterable.next().getValue();
			}
			ItemBoatHolder holder = new ItemBoatHolder(firstContainer, secondContainer);
			holder.setCreativeTab(this.getCreativeTab());
			OpenTransport.INSTANCE.getRegistryHolder().getItemRegistry().registerItem(holder);
		}
	}

	@Override
	public void registerEntities() {
		OpenTransport.INSTANCE.getRegistryHolder().getEntityRegistry().registerEntity(EntityBoatHolder.class);
	}

	@Override
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	@Override
	public boolean getIsActive() {
		return this.isActive;
	}

	private static class BoatCreativeTab extends BaseCreativeTab {
		public BoatCreativeTab() {
			super("Boats");
		}

		@Override
		public Item getTabIconItem() {
			return Items.BOAT;
		}
	}
}