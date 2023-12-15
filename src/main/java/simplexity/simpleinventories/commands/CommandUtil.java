package simplexity.simpleinventories.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import simplexity.simpleinventories.ConfigValues;
import simplexity.simpleinventories.SimpleInventories;
import simplexity.simpleinventories.Util;

public class CommandUtil {
    public static Player checkAdminPerms(Permission runForOtherPerm, CommandSender sender, String[] args) {
        if (!sender.hasPermission(runForOtherPerm)) {
            Util.sendUserMessage(sender, ConfigValues.getInstance().tooManyArgumentsError);
            return null;
        }
        Player player = SimpleInventories.getSimpleInventoriesServer().getPlayer(args[0]);
        if (player == null) {
            Util.sendUserMessage(sender, ConfigValues.getInstance().invalidPlayerError);
        }
        return player;
    }

}
