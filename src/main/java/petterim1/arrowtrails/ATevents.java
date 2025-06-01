package petterim1.arrowtrails;

import cn.nukkit.Player;
import cn.nukkit.entity.projectile.EntityArrow;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityShootBowEvent;
import cn.nukkit.event.player.PlayerJoinEvent;

public class ATevents implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void EntityShootBowEvent(EntityShootBowEvent ev) {
        if (ev.getEntity() instanceof Player && ev.getProjectile() instanceof EntityArrow) {
            Player p = (Player) ev.getEntity();
            if (ATmain.effects.containsKey(p.getName())) {
                ev.setProjectile(new ATentity(ev.getProjectile().getChunk(), ev.getProjectile().namedTag, ev.getEntity(), ((EntityArrow) ev.getProjectile()).isCritical(), false, ATmain.effects.get(p.getName())));
            }
        }
    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent ev) {
        Player p = ev.getPlayer();
        if (ATmain.effects.containsKey(p.getName()) && !p.hasPermission("arrowtrails.command")) {
            ATmain.effects.remove(p.getName());
        }
    }
}
