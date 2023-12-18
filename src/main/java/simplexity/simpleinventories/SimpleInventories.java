package simplexity.simpleinventories;

import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Server;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;
import simplexity.simpleinventories.commands.SIReload;
import simplexity.simpleinventories.commands.inventories.AnvilCommand;
import simplexity.simpleinventories.commands.inventories.CartographyCommand;
import simplexity.simpleinventories.commands.inventories.CraftCommand;
import simplexity.simpleinventories.commands.inventories.EnderchestCommand;
import simplexity.simpleinventories.commands.inventories.GrindstoneCommand;
import simplexity.simpleinventories.commands.inventories.LoomCommand;
import simplexity.simpleinventories.commands.inventories.SmithingCommand;
import simplexity.simpleinventories.commands.inventories.StonecutterCommand;
import simplexity.simpleinventories.commands.inventories.TrashCommand;
import simplexity.simpleinventories.listeners.InventoryCloseListener;

import java.util.Objects;
import java.util.logging.Logger;

public final class SimpleInventories extends JavaPlugin {
    
    private static SimpleInventories instance;
    private static final MiniMessage miniMessage = MiniMessage.miniMessage();
    private static Server simpleInventoriesServer;
    
    
    @Override
    public void onEnable() {
        instance = this;
        simpleInventoriesServer = this.getServer();
        this.saveDefaultConfig();
        ConfigValues.getInstance().reloadConfig();
        registerCommands();
        this.getServer().getPluginManager().registerEvents(new InventoryCloseListener(), this);
        // Plugin startup logic
        
    }
    
    private void registerCommands() {
        String basePerm = "simpleinv.";
        String basePermOther = "simpleinv.other.";
        Objects.requireNonNull(this.getCommand("craft")).setExecutor(new CraftCommand(
                new Permission(basePerm + "crafting"), new Permission(basePermOther + "crafting"),
                ConfigValues.getInstance().craftingSound, ConfigValues.getInstance().otherCrafting
        ));
        Objects.requireNonNull(this.getCommand("anvil")).setExecutor(new AnvilCommand(
                new Permission(basePerm + "anvil"), new Permission(basePermOther + "anvil"),
                ConfigValues.getInstance().anvilSound, ConfigValues.getInstance().otherAnvil
        ));
        Objects.requireNonNull(this.getCommand("cartography")).setExecutor(new CartographyCommand(
                new Permission(basePerm + "cartography"), new Permission(basePermOther + "cartography"),
                ConfigValues.getInstance().cartographySound, ConfigValues.getInstance().otherCartography
        ));
        Objects.requireNonNull(this.getCommand("grindstone")).setExecutor(new GrindstoneCommand(
                new Permission(basePerm + "grindstone"), new Permission(basePermOther + "grindstone"),
                ConfigValues.getInstance().grindstoneSound, ConfigValues.getInstance().otherGrindstone
        
        ));
        Objects.requireNonNull(this.getCommand("loom")).setExecutor(new LoomCommand(
                new Permission(basePerm + "loom"), new Permission(basePermOther + "loom"),
                ConfigValues.getInstance().loomSound, ConfigValues.getInstance().otherLoom
        
        ));
        Objects.requireNonNull(this.getCommand("smithing")).setExecutor(new SmithingCommand(
                new Permission(basePerm + "smithing"), new Permission(basePermOther + "smithing"),
                ConfigValues.getInstance().smithingSound, ConfigValues.getInstance().otherSmithing
        
        ));
        Objects.requireNonNull(this.getCommand("stonecutter")).setExecutor(new StonecutterCommand(
                new Permission(basePerm + "stonecutter"), new Permission(basePermOther + "stonecutter"),
                ConfigValues.getInstance().stonecutterSound, ConfigValues.getInstance().otherStonecutter
        
        ));
        Objects.requireNonNull(this.getCommand("enderchest")).setExecutor(new EnderchestCommand(
                new Permission(basePerm + "enderchest"), new Permission(basePermOther + "enderchest"),
                ConfigValues.getInstance().enderchestSound, ConfigValues.getInstance().otherEnderchest
        ));
        
        Objects.requireNonNull(this.getCommand("trash")).setExecutor(new TrashCommand(
                new Permission(basePerm + "trash"), new Permission(basePermOther + "trash"),
                ConfigValues.getInstance().trashSound, ConfigValues.getInstance().otherTrash
        ));
        Objects.requireNonNull(this.getCommand("sireload")).setExecutor(new SIReload());
    }
    
    public static SimpleInventories getInstance() {
        return instance;
    }
    
    public static MiniMessage getMiniMessage() {
        return miniMessage;
    }
    
    public static Server getSimpleInventoriesServer() {
        return simpleInventoriesServer;
    }
    
    public static Logger getSILogger() {
        return instance.getLogger();
    }
}
