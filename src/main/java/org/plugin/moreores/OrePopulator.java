package org.plugin.moreores;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.Chunk;

import java.util.Map;
import java.util.Random;

public class OrePopulator extends BlockPopulator {

    private final Map<Material, Integer> oreRates;

    static {
        new Random();
    }

    public OrePopulator(Map<Material, Integer> oreRates) {
        this.oreRates = oreRates;
    }

    @Override
    public void populate(World world, Random random, Chunk chunk) {
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                for (int y = 0; y < world.getMaxHeight(); y++) {
                    for (Map.Entry<Material, Integer> entry : oreRates.entrySet()) {
                        if (random.nextInt(500) < entry.getValue()) {
                            chunk.getBlock(x, y, z).setType(entry.getKey());
                        }
                    }
                }
            }
        }
    }
}
