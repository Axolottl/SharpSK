package me.sharpjaws.sharpsk.hooks.Towny;

import ch.njol.skript.lang.Condition;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;

import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ConTownyTownUUIDExists extends Condition {

    private Expression<String> UUID;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
                        SkriptParser.ParseResult Result) {
    	UUID = (Expression<String>) expr[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "[sharpsk] [towny] town %string% exists";
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
