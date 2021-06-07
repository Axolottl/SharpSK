package me.sharpjaws.sharpsk.hooks.Towny;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;

import com.palmergames.bukkit.towny.TownyAPI;
import com.palmergames.bukkit.towny.exceptions.AlreadyRegisteredException;
import com.palmergames.bukkit.towny.exceptions.EconomyException;
import com.palmergames.bukkit.towny.exceptions.TownyException;
import com.palmergames.bukkit.towny.object.*;
import me.sharpjaws.sharpsk.SharpSK;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;

import javax.annotation.Nullable;

public class EffTownyCreateTown extends Effect {
    private Expression<String> s;
    private Expression<Number> sb;
    private Expression<Location> homespawn;
    private Expression<OfflinePlayer> owner;
    private Expression<OfflinePlayer> members;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expr, int matchedPattern, Kleenean paramKleenean,
                        SkriptParser.ParseResult paramParseResult) {
        s = (Expression<String>) expr[0];
        homespawn = (Expression<Location>) expr[1];
        sb = (Expression<Number>) expr[2];
        owner = (Expression<OfflinePlayer>) expr[3];
        members = (Expression<OfflinePlayer>) expr[4];

        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "[towny] create town %string% at %location% [with [starting] balance %-number%] [[and] with mayor %-offlineplayer%] [and residents %-offlineplayers%]";
    }

    @Override
    protected void execute(Event e) {
        SharpSK core = SharpSK.instance;

        // Town Generator

        try {

            TownyWorld world = TownyAPI.getInstance().getDataSource().getWorld(homespawn.getSingle(e).getWorld().getName());
            Coord loc = Coord.parseCoord(homespawn.getSingle(e));

            TownyAPI.getInstance().getDataSource().newTown(s.getSingle(e));
            Town town = TownyAPI.getInstance().getDataSource().getTown(s.getSingle(e));
            if (owner != null) {
                Resident resident = TownyAPI.getInstance().getDataSource().getResident(owner.getSingle(e).getName());
                town.addResidentCheck(resident);
                town.setMayor(resident);
                TownyAPI.getInstance().getDataSource().saveResident(resident);
            }
            if (members != null) {

                for (OfflinePlayer member : members.getAll(e)) {
                    Resident loopresident = TownyAPI.getInstance().getDataSource().getResident(member.getName());
                    town.addResidentCheck(loopresident);
                    TownyAPI.getInstance().getDataSource().saveResident(loopresident);
                }

            }

            TownBlock TB = world.getTownBlock(loc);
            TB.setTown(town);
            town.setHomeBlock(TB);

            TB.setType(TB.getType());
            town.setSpawn(homespawn.getSingle(e));
            if (sb != null) {
                town.getAccount().setBalance(sb.getSingle(e).doubleValue(), "Town Creation");
            } else {
                town.getAccount().setBalance(0, "Town Creation");
            }

            TownyAPI.getInstance().getDataSource().saveTownBlock(TB);
            TownyAPI.getInstance().getDataSource().saveTown(town);
            TownyAPI.getInstance().getDataSource().saveWorld(world);

            TownyAPI.getInstance().getDataSource().saveTowns();
            TownyAPI.getInstance().getDataSource().saveTownBlocks();

        } catch (AlreadyRegisteredException ex2) {
            core.getLogger()
                    .warning("Could not register town: " + "\"" + s.getSingle(e) + "\"" + ". Town already exists");
        } catch (EconomyException | TownyException ex1) {
            core.getLogger().warning("Could not register town: " + "\"" + s.getSingle(e) + "\"");
        }

    }

}
