package me.sharpjaws.sharpsk.hooks.Multiverse;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.onarandombox.MultiverseCore.MultiverseCore;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

class EffMVUnloadWorld extends Effect {

	private Expression<String> w;

	@SuppressWarnings("unchecked")
	@Override
	public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
			SkriptParser.ParseResult paramParseResult) {
		w = (Expression<String>) expr[0];
		return true;
	}

	@Override
	public String toString(@Nullable Event arg0, boolean arg1) {
		return "mv unload world %string%";
	}

	@Override
	protected void execute(Event e) {
		MultiverseCore mv = null;
		mv = MultiverseCore.getPlugin(MultiverseCore.class).getCore();

		mv.getMVWorldManager().unloadWorld(w.getSingle(e));

	}

}
