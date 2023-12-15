package simplexity.simpleinventories;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.command.CommandSender;

public class Util {
    private static final MiniMessage miniMessage = SimpleInventories.getMiniMessage();
    public static void sendUserMessage(CommandSender sender, String message) {
        if (message.isEmpty()) return;
        sender.sendMessage(miniMessage.deserialize(message));
    }
    
    public static void sendUserMessage(CommandSender userToSendTo, String message,
                                       String value, CommandSender userToParse) {
        if (message.isEmpty()) return;
        Component parsedName;
        if (userToParse == null) {
            parsedName = Component.empty();
        } else {
            parsedName = userToParse.name();
        }
        if (value == null) {
            value = "";
        }
        userToSendTo.sendMessage(miniMessage.deserialize(message,
                Placeholder.parsed("prefix", ConfigValues.getInstance().pluginPrefix),
                Placeholder.parsed("value", value),
                Placeholder.component("target", parsedName)));
    }

}
