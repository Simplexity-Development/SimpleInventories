package simplexity.simpleinventories.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import simplexity.simpleinventories.ConfigValues;
import simplexity.simpleinventories.Util;

public class SIReload implements CommandExecutor {
    
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        ConfigValues.getInstance().reloadConfig();
        Util.sendUserMessage(commandSender, ConfigValues.getInstance().configReloaded);
        return false;
    }
}
