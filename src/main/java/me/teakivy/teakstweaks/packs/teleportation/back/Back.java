package me.teakivy.teakstweaks.packs.teleportation.back;

import me.teakivy.teakstweaks.packs.BasePack;
import me.teakivy.teakstweaks.packs.PackType;
import me.teakivy.teakstweaks.utils.ErrorType;
import me.teakivy.teakstweaks.utils.MM;
import me.teakivy.teakstweaks.utils.lang.Translatable;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Back extends BasePack {

    public Back() {
        super("back", PackType.TELEPORTATION, Material.REDSTONE_TORCH);
    }

    public static HashMap<UUID, Location> backLoc = new HashMap<>();

    public static void tpBack(Player player) {
        if (!player.hasPermission("teakstweaks.back")) {
            MM.player(player).sendMessage(ErrorType.MISSING_PERMISSION.m());
            return;
        }
        if (backLoc.containsKey(player.getUniqueId())) {
            player.teleport(backLoc.get(player.getUniqueId()));
        } else {
            MM.player(player).sendMessage(Translatable.get("back.error.no_back_location"));
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (!getConfig().getBoolean("save-death-location")) return;

        backLoc.put(player.getUniqueId(), player.getLocation());
    }

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        List<PlayerTeleportEvent.TeleportCause> causes =
                List.of(PlayerTeleportEvent.TeleportCause.SPECTATE,
                        PlayerTeleportEvent.TeleportCause.COMMAND,
                        PlayerTeleportEvent.TeleportCause.PLUGIN,
                        PlayerTeleportEvent.TeleportCause.CHORUS_FRUIT,
                        PlayerTeleportEvent.TeleportCause.END_GATEWAY,
                        PlayerTeleportEvent.TeleportCause.ENDER_PEARL);
        if (causes.contains(event.getCause())) {
            backLoc.put(event.getPlayer().getUniqueId(), event.getFrom());
        }
    }

}
