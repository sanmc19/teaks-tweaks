package me.teakivy.teakstweaks.packs.mobs.moremobheads.mobs;

import me.teakivy.teakstweaks.packs.mobs.moremobheads.Head;
import me.teakivy.teakstweaks.packs.mobs.moremobheads.MobHeads;
import org.bukkit.event.entity.EntityDeathEvent;

public class PolarBear {
    public static void onDeath(EntityDeathEvent event) {
        if (!MobHeads.dropChance(event.getEntity().getKiller(), Head.getChance("polar-bear"))) return;

        event.getDrops().add(MobHeads.getHead("Polar Bear", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2Q4NzAyOTExZTYxNmMwZDMyZmJlNzc4ZDE5NWYyMWVjY2U5MDI1YmNiZDA5MTUxZTNkOTdhZjMxOTJhYTdlYyJ9fX0"));
    }
}