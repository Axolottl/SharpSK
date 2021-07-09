package me.sharpjaws.sharpsk.hooks.Towny;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ConBendingElement extends Condition {
	
    private Expression<String> s;
    private Expression<OfflinePlayer> p;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
                        SkriptParser.ParseResult Result) {
        p = (Expression<OfflinePlayer>) expr[0];
        s = (Expression<String>) expr[1];
        
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "[sharpsk] [bending] element of %offlineplayer% is %string%";
    }

    @Override
    @Nullable
    public boolean check(Event e) {
    	
        try {
        	TownyAPI.getInstance().getDataSource().getTown((java.util.UUID.fromString(UUID.getSingle(e))));
            return true;
        } catch (NullPointerException | IllegalArgumentException | NotRegisteredException ignored) {
        	//ignored.printStackTrace();
            return false;
        }
    }
}
