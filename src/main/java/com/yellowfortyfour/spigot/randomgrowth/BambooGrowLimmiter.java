package com.yellowfortyfour.spigot.randomgrowth;

import java.util.Random;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.block.data.*;
import org.bukkit.block.data.type.Bamboo;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.Plugin;
import org.bukkit.block.Block;

public class BambooGrowLimmiter implements Listener {
  final Plugin plugin;
  final Random random = new Random();


  public BambooGrowLimmiter(Plugin plugin) {
    Bukkit.getLogger().info("[BambooGrowLimmiter] watching");

    this.plugin = plugin;
  }

  @EventHandler(priority = EventPriority.HIGHEST)
  public void onBlockSpreadEvent(BlockSpreadEvent event) {
    final Block sourceBlock = event.getSource();
    
    if (sourceBlock.getType() == Material.BAMBOO) {
      final Block newBlock = event.getBlock();
      final Location sourceLoc = sourceBlock.getLocation();
      final Location newLoc = newBlock.getLocation();
      final World world = sourceBlock.getWorld();
      Location rootLoc = newLoc.clone();
      
      for (int y = sourceLoc.getBlockY(); y > 0; y--) {
        if (world.getBlockAt(sourceLoc.getBlockX(), y, sourceLoc.getBlockZ()).getType() != sourceBlock.getType()) {
          rootLoc.setY(++y);
          break;
        }
      }
      int plantLenght = newLoc.getBlockY() - rootLoc.getBlockY() + 1;

      if (plantLenght > getMaxHeight() && !allowGrowth(plantLenght)) {
        org.bukkit.block.data.type.Bamboo bambooData = (org.bukkit.block.data.type.Bamboo) sourceBlock.getBlockData();
        bambooData.setStage(1);
        bambooData.setLeaves(Bamboo.Leaves.LARGE);
        sourceBlock.setBlockData(bambooData);

        event.setCancelled(true);
      }
    }
  }

  private Boolean allowGrowth(int plantHeight) {
    final float factor = 1.0f - (plantHeight / 48.0f);
    final float ran = random.nextFloat();
    final Boolean allowed = factor > ran;
    return allowed;
  }

  private int getMaxHeight() {
    return 3;
  }
}
