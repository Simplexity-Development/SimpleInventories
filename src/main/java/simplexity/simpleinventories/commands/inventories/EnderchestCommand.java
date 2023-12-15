package simplexity.simpleinventories.commands.inventories;

import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Inventory;
import org.bukkit.permissions.Permission;
import simplexity.simpleinventories.commands.AbstractInventoryCommand;

public class EnderchestCommand extends AbstractInventoryCommand {
    
    
    public EnderchestCommand(Permission permission, Permission otherPermission, Sound sound, String successOnOther) {
        super(permission, otherPermission, sound, successOnOther);
    }
    
    @Override
    public void openInventory(CommandSender sender) {
        Inventory enderchest = getPlayer().getEnderChest();
        getPlayer().openInventory(enderchest);
        super.openInventory(sender);
    }
}
