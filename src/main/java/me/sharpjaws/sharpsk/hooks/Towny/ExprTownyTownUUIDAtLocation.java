package me.sharpjaws.sharpsk.hooks.Towny;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.Coord;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprTownyTownUUIDAtLocation extends SimpleExpression<String> {

    private Expression<Location> location;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
                        SkriptParser.ParseResult Result) {
    	location = (Expression<Location>) expr[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "[sharpsk] [towny] town UUID at %location%";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        try {
            Town town = TownyAPI.getInstance().getTown(location.getSingle(e));
            return new String[]{String.valueOf(town.getUUID())};
            }
        catch(NullPointerException ex) {
            return new String[]{};
        }
    }

    @Override
    public boolean isSingle() {
        return true;
    }

}
