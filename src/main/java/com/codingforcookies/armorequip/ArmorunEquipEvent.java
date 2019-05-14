package com.codingforcookies.armorequip;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @Author Made by Borlea Fixed by Sharpjaws
 * @Github https://github.com/borlea/
 * @Website http://codingforcookies.com/
 * @since Jul 30, 2015
 */
public final class ArmorunEquipEvent extends PlayerEvent implements Cancellable {

	private static final HandlerList handlers = new HandlerList();
	private boolean cancel = false;
	private final EquipMethod equipType;
	private final ArmorType type;
	private final ItemStack item;

	/**
	 * Constructor for the ArmorEquipEvent.
	 *
	 * @param player
	 *            The player who put on / removed the armor.
	 * @param type
	 *            The ArmorType of the armor added
	 * @param oldArmorPiece
	 *            The ItemStack of the armor removed.
	 * @param newArmorPiece
	 *            The ItemStack of the armor added.
	 */
	public ArmorunEquipEvent(final Player player, final EquipMethod equipType, final ArmorType type,
			final ItemStack item) {
		super(player);
		this.equipType = equipType;
		this.type = type;
		this.item = item;
	}

	/**
	 * Gets a list of handlers handling this event.
	 *
	 * @return A list of handlers handling this event.
	 */
	public final static HandlerList getHandlerList() {
		return handlers;
	}

	/**
	 * Gets a list of handlers handling this event.
	 *
	 * @return A list of handlers handling this event.
	 */
	@Override
	public final HandlerList getHandlers() {
		return handlers;
	}

	/**
	 * Sets if this event should be cancelled.
	 *
	 * @param cancel
	 *            If this event should be cancelled.
	 */
	public final void setCancelled(final boolean cancel) {
		this.cancel = cancel;
	}

	/**
	 * Gets if this event is cancelled.
	 *
	 * @return If this event is cancelled
	 */
	public final boolean isCancelled() {
		return cancel;
	}

	public final ArmorType getType() {
		return type;
	}

	/**
	 * Returns the last equipped armor piece, could be a piece of armor,
	 * {@link Material#Air}, or null.
	 */
	public final ItemStack getItem() {
		return item;
	}

	public EquipMethod getMethod() {
		return equipType;
	}

	public enum EquipMethod {
		/**
		 * When you shift click an armor piece to equip or unequip
		 */
		SHIFT_CLICK,
		/**
		 * When you drag and drop the item to equip or unequip
		 */
		DRAG,
		/**
		 * When you right click an armor piece in the hotbar without the inventory open
		 * to equip.
		 */
		HOTBAR,
		/**
		 * When you press the hotbar slot number while hovering over the armor slot to
		 * equip or unequip
		 */
		HOTBAR_SWAP,
		/**
		 * When in range of a dispenser that shoots an armor piece to equip.
		 */
		DISPENSER,
		/**
		 * When an armor piece breaks to unequip
		 */
		BROKE,
		/**
		 * When you die causing all armor to unequip
		 */
		DEATH,;
	}
}