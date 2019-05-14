package me.sharpjaws.sharpsk.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

class EvtMMBuyEvent extends Event implements Cancellable {
	private static final HandlerList h = new HandlerList();
	private boolean cancelled = false;

	public EvtMMBuyEvent() {
		cancelled = false;
	}

	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean b) {
		cancelled = b;
	}

	@Override
	public HandlerList getHandlers() {
		return h;
	}

	public static HandlerList getHandlerList() {
		return h;
	}
}
