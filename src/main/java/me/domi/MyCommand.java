package me.domi;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class MyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) return true;

        MyInventory inventory = new MyInventory();
        if (Main.enabled.contains(player)) {
            inventory.getInventory().setItem(0, MyEvent.enabled());
        } else {
            inventory.getInventory().setItem(0, MyEvent.disabled());
        }

        inventory.getInventory().setItem(10, new ItemStack(create_stack(Material.BLACK_STAINED_GLASS_PANE, "§0BLACK")));
        inventory.getInventory().setItem(11, new ItemStack(create_stack(Material.RED_STAINED_GLASS_PANE, "§4RED")));
        inventory.getInventory().setItem(12, new ItemStack(create_stack(Material.ORANGE_STAINED_GLASS_PANE, "§6ORANGE")));
        inventory.getInventory().setItem(13, new ItemStack(create_stack(Material.YELLOW_STAINED_GLASS_PANE, "§eYELLOW")));
        inventory.getInventory().setItem(14, new ItemStack(create_stack(Material.LIME_STAINED_GLASS_PANE, "§2LIME")));
        inventory.getInventory().setItem(15, new ItemStack(create_stack(Material.BLUE_STAINED_GLASS_PANE, "§1BLUE")));
        inventory.getInventory().setItem(16, new ItemStack(create_stack(Material.PURPLE_STAINED_GLASS_PANE, "§5PURPLE")));
        inventory.getInventory().setItem(22, new ItemStack(create_stack(Material.WHITE_STAINED_GLASS_PANE, "§fWHITE")));

        player.openInventory(inventory.getInventory());
        return false; // return usage
    }

    private static ItemStack create_stack(Material material, String name) {
        ItemStack item_stack = new ItemStack(material);
        ItemMeta item_meta = item_stack.getItemMeta();

        if (item_meta != null) {
            item_meta.setDisplayName(name);
            item_stack.setItemMeta(item_meta);
        }

        return item_stack;
    }

}
