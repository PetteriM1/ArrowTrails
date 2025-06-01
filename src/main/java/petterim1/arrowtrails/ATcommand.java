package petterim1.arrowtrails;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParameter;
import cn.nukkit.level.ParticleEffect;

public class ATcommand extends cn.nukkit.command.Command {

    public ATcommand() {
        super("arrowtrails");
        setPermission("arrowtrails.command");
        setUsage("/arrowtrails");
        setDescription("ArrowTrails");
        commandParameters.clear();
        commandParameters.put("default", new CommandParameter[] { new CommandParameter("arrowtrails", true, new String[] { "help", "list", "enable", "disable" })});
    }

    @Override
    public boolean execute(CommandSender s, String c, String[] a) {
        if (!s.hasPermission(getPermission())) return false;
        if (!(s instanceof Player)) {
            s.sendMessage("§cThis command only works in game");
            return true;
        }

        if (0 == a.length) {
            showHelp(s);
            return true;
        }

        String n = s.getName();

        switch (a[0].toLowerCase()) {
            case "disable":
                if (ATmain.effects.containsKey(n)) {
                    ATmain.effects.remove(n);
                    s.sendMessage("§aArrow trails disabled");
                } else {
                    s.sendMessage("§cYou don't have arrow trails enabled");
                }
                break;
            case "enable":
                if (2 != a.length) {
                    showHelp(s);
                    return true;
                }

                String e = a[1].toUpperCase();
                if (!isValidParticle(e)) {
                    s.sendMessage("§cUnknown effect. Use §e/arrowtrails list §cto see a list of available arrow trail effects.");
                    return true;
                }

                if (ATmain.bannedEffects.contains(e)) {
                    s.sendMessage("§cThis effect is banned on this server");
                    return true;
                }

                if (ATmain.effects.containsKey(n)) {
                    ATmain.effects.put(n, e);
                    s.sendMessage("§6Arrow trails updated");
                } else {
                    ATmain.effects.put(n, e);
                    s.sendMessage("§aArrow trails enabled");
                }
                break;
            case "list":
                StringBuilder msg = new StringBuilder("§aAvailable arrow trails:§7");
                for (ParticleEffect pe : ParticleEffect.values()) {
                    if (!ATmain.bannedEffects.contains(pe.toString())) {
                        msg.append('\n').append(pe);
                    }
                }
                s.sendMessage(msg.toString());
                break;
            case "help":
            default:
                showHelp(s);
        }

        return true;
    }

    private static void showHelp(CommandSender s) {
        s.sendMessage("§l§cArrowTrails §r§fby PetteriM1");
        s.sendMessage("§7/arrowtrails enable <trail> - Enable arrow trails");
        s.sendMessage("§7/arrowtrails disable - Disable arrow trails");
        s.sendMessage("§7/arrowtrails list - Show list of all available arrow trails");
        s.sendMessage("§7/arrowtrails help - Show this page");
    }

    private static boolean isValidParticle(String p) {
        try {
            ParticleEffect.valueOf(p);
            return true;
        } catch (Exception ignore) {
            return false;
        }
    }
}
