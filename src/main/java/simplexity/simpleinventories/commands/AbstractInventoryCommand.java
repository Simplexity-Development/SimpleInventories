package simplexity.simpleinventories.commands;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;
import simplexity.simpleinventories.ConfigValues;
import simplexity.simpleinventories.Util;

public abstract class AbstractInventoryCommand implements CommandExecutor {
    
    private final Permission permission;
    private final Permission adminPermission;
    private final Sound sound;
    private final String successOnOther;
    private Player player;
    private Location playerLocation;
    
    public AbstractInventoryCommand(Permission permission, Permission otherPermission, Sound sound, String successOnOther) {
        this.permission = permission;
        this.adminPermission = otherPermission;
        this.sound = sound;
        this.successOnOther = successOnOther;
    }
    
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player) && args.length == 0) {
            Util.sendUserMessage(sender, ConfigValues.getInstance().mustBeAPlayerError);
            return false;
        } else if (!(sender instanceof Player)) {
            player = CommandUtil.checkAdminPerms(adminPermission, sender, args);
            if (player == null) return false;
        }
        if (args.length > 0) {
            player = CommandUtil.checkAdminPerms(adminPermission, sender, args);
            if (player == null) return false;
        }
        if (args.length == 0) {
            player = (Player) sender;
            playerLocation = player.getLocation();
            player.playSound(playerLocation, sound, 1, 1);
            openInventory(null);
            return true;
        }
        playerLocation = player.getLocation();
        player.playSound(playerLocation, sound, 1, 1);
        openInventory(sender);
        return false;
    }
    
    public void openInventory(CommandSender sender) {
        if (sender != null) {
            Util.sendUserMessage(sender, successOnOther, null, player);
        }
    }
    
    
    public Permission getPermission() {
        return permission;
    }
    
    public Permission getAdminPermission() {
        return adminPermission;
    }
    
    public Sound getSound() {
        return sound;
    }
    
    
    public Player getPlayer() {
        return player;
    }
    
    public Location getPlayerLocation() {
        return playerLocation;
    }
    
    public String getSuccessOnOther() {
        return successOnOther;
    }
}


