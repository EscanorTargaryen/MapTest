package dev.davidebilardello;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;

public class MapTest extends JavaPlugin {


    @Override
    public void onEnable() {
        BoundingBox bb = new BoundingBox(0, 0, 0, 20, 20, 20);

        new BukkitRunnable() {
            @Override
            public void run() {

                for (Player p : Bukkit.getOnlinePlayers()) {

                    Location loc = p.getLocation();
                    System.out.println(bb.contains(loc.getX(), loc.getY(), loc.getZ()) + "");

                }


            }
        }.runTaskTimerAsynchronously(this, 20, 20);


    }


}