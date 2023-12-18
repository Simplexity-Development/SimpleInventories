package simplexity.simpleinventories;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashSet;
import java.util.List;

public class ConfigValues {
    public ConfigValues(){}
    private static ConfigValues instance;
    public static ConfigValues getInstance(){
        if (instance == null) instance = new ConfigValues();
        return instance;
    }
    private final HashSet<Material> trashBlacklist = new HashSet<>();
    public Sound craftingSound, anvilSound, cartographySound, stonecutterSound, smithingSound,
            grindstoneSound, loomSound, enderchestSound, trashSound, trashAlert;
    
    public String otherAnvil, otherCartography, otherCrafting, otherEnderchest, otherLoom, otherSmithing,
            otherStonecutter, otherGrindstone, otherTrash, trashName, trashSuccess, trashBlacklistError, pluginPrefix,
            consoleName, configReloaded, syntaxError, tooManyArgumentsError, mustBeAPlayerError, invalidPlayerError;
    
    public void reloadConfig(){
        FileConfiguration config = SimpleInventories.getInstance().getConfig();
        fillBlacklist(config);
        reloadInventorySounds(config);
        reloadLocale(config);
    }
    private void fillBlacklist(FileConfiguration config) {
        trashBlacklist.clear();
        List<String> configuredTrashList = config.getStringList("trash-blacklist");
        for (String item : configuredTrashList) {
            Material blacklistedMaterial;
            blacklistedMaterial = Material.matchMaterial(item);
            if (blacklistedMaterial == null) {
                SimpleInventories.getSILogger().warning(item + " is not a valid material to blacklist (/trash blacklist). Please be sure you are using materials from https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html");
                continue;
            }
            trashBlacklist.add(blacklistedMaterial);
        }
    }
    
    private void reloadInventorySounds(FileConfiguration config) {
        craftingSound = checkSound(config.getString("craft-open-sound"));
        anvilSound = checkSound(config.getString("anvil-open-sound"));
        cartographySound = checkSound(config.getString("cartography-open-sound"));
        stonecutterSound = checkSound(config.getString("stonecutter-open-sound"));
        smithingSound = checkSound(config.getString("smithing-open-sound"));
        grindstoneSound = checkSound(config.getString("grindstone-open-sound"));
        loomSound = checkSound(config.getString("loom-open-sound"));
        enderchestSound = checkSound(config.getString("enderchest-open-sound"));
        trashSound = checkSound(config.getString("trash-open-sound"));
        trashAlert = checkSound(config.getString("trash-alert"));
    }
    private void reloadLocale(FileConfiguration config) {
        otherAnvil = config.getString("open-for-other.anvil");
        otherCartography = config.getString("open-for-other.cartography");
        otherCrafting = config.getString("open-for-other.crafting-table");
        otherEnderchest = config.getString("open-for-other.enderchest");
        otherLoom = config.getString("open-for-other.loom");
        otherSmithing = config.getString("open-for-other.smithing-table");
        otherStonecutter = config.getString("open-for-other.stonecutter");
        otherGrindstone = config.getString("open-for-other.grindstone");
        otherTrash = config.getString("open-for-other.trash");
        trashName = config.getString("trash.inventory-name");
        trashSuccess = config.getString("trash.success-feedback");
        trashBlacklistError = config.getString("trash.blacklist-error");
        pluginPrefix = config.getString("plugin-messages.prefix");
        consoleName = config.getString("plugin-messages.console-name");
        configReloaded = config.getString("plugin-messages.config-reloaded");
        syntaxError = config.getString("errors.syntax-error");
        tooManyArgumentsError = config.getString("errors.too-many-arguments");
        mustBeAPlayerError = config.getString("errors.must-be-player");
        invalidPlayerError = config.getString("errors.invalid-player");
    }
    
    private Sound checkSound(String string) {
        if (string == null) {
            return null;
        }
        if (string.equalsIgnoreCase("none")) {
            return null;
        }
        Sound soundToCheck;
        try {
            soundToCheck = Sound.valueOf(string);
        } catch (ClassCastException e) {
            SimpleInventories.getSILogger().warning("Sound " + string + " unable to be cast to sound, please be sure you are using a sound from https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Sound.html, or write \"none\" for no sound. Setting sound to null.");
            return null;
        }
        return soundToCheck;
    }
    
    public HashSet<Material> getTrashBlacklist() {
        return trashBlacklist;
    }
}
