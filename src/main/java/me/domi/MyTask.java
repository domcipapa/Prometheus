package me.domi;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import static java.lang.Math.*;

public class MyTask implements Runnable {

    private static final int PARTICLE_COUNT = 3;

    private static final float QUARTER_CIRCLE = (float) (PI / 2);
    private static final float FULL_CIRCLE = (float) (2 * PI);

    private static final float RADIUS_WIDTH = 1.0f;
    private static final float RADIUS_HEIGHT = 0.5f;

    @Override
    public void run() {
        float angle_increment = (float) (Main.getAngle() * (PI / 180));

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!Main.enabled.contains(player)) continue;

            Particle.DustOptions dust_options = new Particle.DustOptions(Main.map.get(player), 1.0F);
            Location core_location = player.getLocation().add(0, 1, 0);
            Location location = core_location.clone();

            for (int i = 0; i < PARTICLE_COUNT; i++) {
                float angle = angle_increment + (i * FULL_CIRCLE / PARTICLE_COUNT);

                double x = RADIUS_WIDTH * cos(angle);
                double z = RADIUS_WIDTH * sin(angle);
                double y = RADIUS_HEIGHT * sin(angle + i * QUARTER_CIRCLE);

                location.setX(core_location.getX() + x);
                location.setY(core_location.getY() + y);
                location.setZ(core_location.getZ() + z);

                player.getWorld().spawnParticle(Particle.DUST, location, 7, dust_options);
            }
        }

        Main.setAngle((Main.getAngle() + 7) % 360);
    }
}
