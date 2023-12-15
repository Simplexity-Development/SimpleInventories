package simplexity.simpleinventories.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import simplexity.simpleinventories.ConfigValues;
import simplexity.simpleinventories.Util;
import simplexity.simpleinventories.commands.inventories.TrashCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class InventoryCloseListener implements Listener {
    
    final HashMap<UUID, Inventory> inventoryHashMap = TrashCommand.getInvMap();
    
    
    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    
    public void onInventoryClose(InventoryCloseEvent closeEvent) {
        UUID playerUUID = closeEvent.getPlayer().getUniqueId();
        Inventory closedInventory = closeEvent.getInventory();
        if (!inventoryHashMap.containsKey(playerUUID)) return;
        if (!inventoryHashMap.containsValue(closedInventory)) return;
        Inventory trashInventory = inventoryHashMap.get(playerUUID);
        if (trashInventory.isEmpty()) return;
        Player player = (Player) closeEvent.getPlayer();
        ItemStack[] trashContents = trashInventory.getContents();
        Location playerLocation = player.getLocation();
        ArrayList<ItemStack> blockedItemsToDrop = new ArrayList<>();
        //check if any items that are in the trash are blacklisted, if they are, add to arraylist
        for (ItemStack item : trashContents) {
            if (item == null) {
                continue;
            }
            Material itemType = item.getType();
            if (!ConfigValues.getInstance().getTrashBlacklist().contains(itemType)) {
                continue;
            }
            blockedItemsToDrop.add(item);
        }
        //Clear first so that no items get duplicated
        trashInventory.clear();
        //if the list isn't empty, drop the things
        if (!blockedItemsToDrop.isEmpty()) {
            for (ItemStack item : blockedItemsToDrop) {
                playerLocation.getWorld().dropItem(playerLocation, item);
            }
            Util.sendUserMessage(player, ConfigValues.getInstance().trashBlacklistError, String.valueOf(blockedItemsToDrop.size()), null);
            player.playSound(playerLocation, ConfigValues.getInstance().trashAlert, 0.5f, 1);
        } else {
            Util.sendUserMessage(player, ConfigValues.getInstance().trashSuccess, null, null);
        }
        
    }
}


