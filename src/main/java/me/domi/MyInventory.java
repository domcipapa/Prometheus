package me.domi;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public class MyInventory implements InventoryHolder {

    private final Inventory inventory;

    public MyInventory() {
        this.inventory = Main.getInstance().getServer().createInventory(this, 27, "§bColor §dselector §6:3");
    }

    @Override
    public @NotNull Inventory getInventory() {
        return this.inventory;
    }

}
