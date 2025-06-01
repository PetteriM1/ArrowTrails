package petterim1.arrowtrails;

import cn.nukkit.entity.Entity;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

import java.util.*;

public class ATmain extends PluginBase {

    private Config data;

    static Map<String, String> effects;

    static Set<String> bannedEffects;

    @Override
    public void onLoad() {
        Entity.registerEntity("ATentity", ATentity.class);
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        saveResource("data.yml");
        data = new Config(getDataFolder() + "/data.yml", Config.YAML);
        effects = (Map<String, String>) data.get("effects");
        if (null == effects) {
            effects = new HashMap<>();
            getLogger().info("No data found");
        }
        bannedEffects = new HashSet<>(getConfig().getStringList("bannedEffects"));
        getServer().getCommandMap().register("arrowtrails", new ATcommand());
        getServer().getPluginManager().registerEvents(new ATevents(), this);
        getLogger().info("§cArrowTrails §aby PetteriM1 enabled!");
    }

    @Override
    public void onDisable() {
        data.set("effects", effects);
        data.save(false);
    }
}
