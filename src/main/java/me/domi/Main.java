package me.domi;

import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.*;

public class Main extends JavaPlugin implements Listener {

    private static Main instance;

    public static Map<Player, Color> map = new HashMap<>();
    public static List<Player> enabled = new ArrayList<>();
    private static int angle = 0;

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new MyEvent(), this);
        Objects.requireNonNull(getCommand("prometheus")).setExecutor(new MyCommand());

        BukkitScheduler scheduler = this.getServer().getScheduler();
        scheduler.runTaskTimerAsynchronously(this, new MyTask(), 0, 1);
    }

    public static int getAngle() {
        return angle;
    }

    public static void setAngle(int amount) {
        angle = amount;
    }

    public static Main getInstance() {
        return instance;
    }

}
