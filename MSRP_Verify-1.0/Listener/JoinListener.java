// 
// Decompiled by Procyon v0.5.36
// 

package Listener;

import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import at.msrp.msrp_verify.MSRP_Verify;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.Listener;

public class JoinListener implements Listener
{
    @EventHandler
    private void onJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        e.setJoinMessage("§8[§a+§8] §7" + p.getDisplayName());
        p.sendMessage("");
        p.sendMessage("§3§lMS§b§lRP §fDevelopment");
        p.sendMessage("§7Willkommen auf dem §3§lMS§b§lRP§f-Development §7Server!");
        p.sendMessage("");
        p.sendMessage("§7Nutze §3/verify §7um dich f\u00fcr den Discord zu §fverifizieren§7!");
        p.sendMessage("");
        p.sendMessage("§fDiscord§7: §3https://discord.gg/4EuqWurv");
        p.sendMessage("");
        if (!MSRP_Verify.getInstance().mySQLHandler.isRegistered("SELECT * FROM verify WHERE MC_UUID='" + p.getUniqueId() + "';")) {
            MSRP_Verify.getInstance().mySQLHandler.insert("verify", new String[] { p.getUniqueId().toString(), "", e.getPlayer().getName(), "", "" });
        }
        else {
            MSRP_Verify.getInstance().mySQLHandler.update("UPDATE verify WHERE MC_UUID='" + p.getUniqueId() + "' SET Username='" + p.getName() + "';");
        }
    }
}
