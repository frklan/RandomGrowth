package com.yellowfortyfour.spigot.randomgrowth;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RandomGrowthPlugin extends JavaPlugin {
  @Override
  public void onEnable() {
    Bukkit.getServer().getPluginManager().registerEvents(new BambooGrowLimmiter(this), this);
  }
}
