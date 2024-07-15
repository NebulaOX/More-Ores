package org.plugin.moreores;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MoreOres extends JavaPlugin implements Listener {

    private final Map<Material, Integer> oreRates = new HashMap<>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadOreRates();
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    private void loadOreRates() {
        FileConfiguration config = getConfig();
        for (String key : Objects.requireNonNull(config.getConfigurationSection("ore-rates")).getKeys(false)) {
            Material material = Material.getMaterial(key);
            if (material != null) {
                int rate = config.getInt("ore-rates." + key);
                oreRates.put(material, rate);
            }
        }
    }

    @EventHandler
    public void onWorldInit(WorldInitEvent event) {
        World world = event.getWorld();
        world.getPopulators().add(new OrePopulator(oreRates));
    }
}
