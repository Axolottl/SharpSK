package me.sharpjaws.sharpSK.hooks.LogBlock;

import ch.njol.skript.Skript;

public class LogBlockRegistry {

	public static void registerLogBlock() {
		Skript.registerEffect(EffQueueLog.class, "logblock queue block (1�break|2�place|3�replace) from %player% at %location% [with [previous block %-block% and new] block %-block%]");
	}
	
}
