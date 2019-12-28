package com.yellowfortyfour.spigot.randomgrowth;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.block.Block;

public class BambooGrowLimmiter implements Listener {
  final Plugin plugin;

  public BambooGrowLimmiter(Plugin pl) {
    Bukkit.getLogger().info("[BambooGrowLimmiter] watching");

    plugin = pl;
  }

  @EventHandler
  public void onBlockSpreadEvent(BlockSpreadEvent event) {

    Block sourceBlock = event.getSource();
    Block newBlock = event.getBlock();
    Block rootBlock = null;

    if (sourceBlock.getType() == Material.BAMBOO) {
      Bukkit.getLogger().info("--- [BlockSpreadEvent] ---");

      final Location sourceLoc = sourceBlock.getLocation();
      final Location newLoc = newBlock.getLocation();
      Location rootLoc = newLoc.clone();

      World world = sourceBlock.getWorld();
      for (int y = sourceLoc.getBlockY(); y > 0; y--) {
        if (world.getBlockAt(sourceLoc.getBlockX(), y, sourceLoc.getBlockZ()).getType() != sourceBlock.getType()) {
          rootLoc.setY(++y);
          break;
        }
      }
      int plantLenght = newLoc.getBlockY() - rootLoc.getBlockY() + 1; // lenght incl. new block that is about to grow
      rootBlock = world.getBlockAt(rootLoc);

      Bukkit.getLogger().info("newBlock is at " + newLoc.toString());
      Bukkit.getLogger().info("sourceBlock is at " + sourceLoc.toString());
      Bukkit.getLogger().info("rootBlock is at " + rootLoc.toString());
      Bukkit.getLogger().info("new plant lenght = " + plantLenght);

      if (plantLenght > getMaxHeight(rootBlock)) {
        Bukkit.getLogger().info("plant to long, let's cancel the growth.");
        org.bukkit.block.data.type.Bamboo bambooBlockData = (org.bukkit.block.data.type.Bamboo) sourceBlock.getBlockData();
        bambooBlockData.setStage(1);
        sourceBlock.setBlockData(bambooBlockData);

        event.setCancelled(true);
      }
      Bukkit.getLogger().info("--------------------------");
    }
  }

  private int getMaxHeight(Block rootBlock) {
    int maxHeight = 0;
    if (rootBlock.hasMetadata("maxHeight")) {
      maxHeight = rootBlock.getMetadata("maxHeight").get(0).asInt();
    } else {
      maxHeight = getRandom(3, 25);
      rootBlock.setMetadata("maxHeight", new FixedMetadataValue(plugin, maxHeight));
    }
    return maxHeight;
  }

  private int getRandom(int min, int max) {
    assert (min < max);

    Random random = new Random();
    return random.nextInt((max - min) + 1) + min;
  }
}
