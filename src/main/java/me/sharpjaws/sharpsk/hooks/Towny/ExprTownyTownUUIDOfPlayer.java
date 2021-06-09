package me.sharpjaws.sharpsk.hooks.Towny;

import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.util.SimpleExpression;
import ch.njol.util.Kleenean;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.exceptions.NotRegisteredException;
import com.palmergames.bukkit.towny.object.Resident;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class ExprTownyTownUUIDOfPlayer extends SimpleExpression<String> {

    private Expression<OfflinePlayer> inputSkriptResident;

    @Override
    public Class<? extends String> getReturnType() {
        return String.class;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
                        SkriptParser.ParseResult Result) {
    	inputSkriptResident = (Expression<OfflinePlayer>) expr[0];
        return true;
    }

    @Override
    public String toString(@Nullable Event e, boolean paramBoolean) {
        return "[sharpsk] [towny] town UUID of %player%";
    }

    @Override
    @Nullable
    protected String[] get(Event e) {
        try {
            Resident resident = TownyUniverse.getInstance().getResident(inputSkriptResident.getSingle(e).getName());
            return new String[]{String.valueOf(resident.getTown().getUUID())};
        } catch (NotRegisteredException Ignored) {
            return new String[]{};
        }

    }

    @Override
    public boolean isSingle() {
        return true;
    }

}
