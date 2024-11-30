package me.domi;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class MyEvent implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Main.map.put(player, Color.WHITE);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        Main.map.remove(player);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory inventory = event.getInventory();

        if (!(inventory.getHolder() instanceof MyInventory)) {
            return;
        }
        Player player = (Player) event.getWhoClicked();

        ItemStack current_item = event.getCurrentItem();
        if (current_item == null) return;

        if (current_item.isSimilar(disabled())) {
            Main.enabled.add(player);
            inventory.setItem(0, enabled());

            player.sendMessage("§cTrails enabled");
            event.setCancelled(true);
            return;
        }

        if (current_item.isSimilar(enabled())) {
            Main.enabled.remove(player);
            inventory.setItem(0, disabled());

            player.sendMessage("§cTrails disabled");
            event.setCancelled(true);
            return;
        }

        if (current_item.getType().name().endsWith("_STAINED_GLASS_PANE")) {
            String colour = current_item.getType().name().replace("_STAINED_GLASS_PANE", "");
            Color color = parse_color(colour);
            Main.map.put(player, color);

            player.sendMessage("§cTrail color is set to " + colour);
            event.setCancelled(true);
        }
    }

    public static ItemStack enabled() {
        ItemStack item_stack = new ItemStack(Material.GREEN_STAINED_GLASS);
        ItemMeta item_meta = item_stack.getItemMeta();
        item_meta.setDisplayName("§2Enabled");
        item_stack.setItemMeta(item_meta);

        return item_stack;
    }

    public static ItemStack disabled() {
        ItemStack item_stack = new ItemStack(Material.RED_STAINED_GLASS);
        ItemMeta item_meta = item_stack.getItemMeta();
        item_meta.setDisplayName("§4Disabled");
        item_stack.setItemMeta(item_meta);

        return item_stack;
    }

    private static Color parse_color(String string) {
        return switch (string) {
            case "BLACK" -> Color.BLACK;
            case "RED" -> Color.RED;
            case "ORANGE" -> Color.ORANGE;
            case "YELLOW" -> Color.YELLOW;
            case "LIME" -> Color.LIME;
            case "BLUE" -> Color.BLUE;
            case "PURPLE" -> Color.PURPLE;
            case "WHITE" -> Color.WHITE;
            default -> throw new IllegalStateException("Unexpected value: " + string);
        };
    }

}
