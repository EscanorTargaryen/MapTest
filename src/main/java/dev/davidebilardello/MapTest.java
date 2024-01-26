package dev.davidebilardello;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIBukkitConfig;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.BooleanArgument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MapTest extends JavaPlugin implements Listener {

    List<Rect> rects = new ArrayList<>();

    @Override
    public void onEnable() {
        CommandAPI.onEnable();


        new CommandAPICommand("maptest").withArguments(new IntegerArgument("xPoint1"), new IntegerArgument("yPoint1"), new IntegerArgument("xPoint2"), new IntegerArgument("yPoint2"), new BooleanArgument("isPvpEnabled"))
                .executesPlayer((player, args) -> {

                    rects.add(new Rect((int) args.get(0), (int) args.get(1), (int) args.get(2), (int) args.get(3), (boolean) args.get(4)));

                }).register();

        Bukkit.getPluginManager().registerEvents(this, this);

    }

    @EventHandler
    public void onMapInitialize(MapInitializeEvent e) {

        MapView mapView = e.getMap();

        mapView.addRenderer(new MapRenderer() {
            @Override
            public void render(MapView map, MapCanvas canvas, Player player) {

                for (Rect r : rects) {
                    drawRectColor(map, canvas, r.getxPoint1(), r.getxPoint2(), r.getyPoint1(), r.getyPoint2(), r.isPvpEnabled() ? Color.red : Color.green);

                }


            }
        });


    }


    public void drawRectColor(MapView map, MapCanvas canvas, int xStart, int xEnd, int yStart, int yEnd, Color color) {

        int centerX = map.getCenterX();
        int centerZ = map.getCenterZ();

        int startDrawx = 0, startDrawz = 0, endDrawx = 0, endDraz = 0;


        for (int x = centerX - 64, xx = 0; x < centerX + 64; x++, xx++) {
            for (int y = centerZ - 64, yy = 0; y < centerZ + 64; y++, yy++) {
                if (x == xStart) {
                    startDrawx = xx;
                }

                if (x == xEnd) {
                    endDrawx = xx;
                }

                if (y == yStart) {
                    startDrawz = yy;
                }

                if (y == yEnd) {
                    endDraz = yy;
                }
            }
        }


        for (int x = Math.min(startDrawx, endDrawx); x < Math.max(startDrawx, endDrawx); x++) {
            for (int y = Math.min(startDrawz, endDraz); y < Math.max(startDrawz, endDraz); y++) {
                canvas.setPixelColor(x, y, color);
            }
        }

    }


    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIBukkitConfig(this).silentLogs(true));

    }
}