// 
// Decompiled by Procyon v0.5.36
// 

package Commands;

import org.bukkit.Sound;
import at.msrp.msrp_verify.MSRP_Verify;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class VerifyCmd implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (MSRP_Verify.getInstance().verifyHandler.isVerified(p)) {
                p.sendMessage(MSRP_Verify.alreadyVerified);
            }
            else {
                final String secret = MSRP_Verify.getInstance().verifyHandler.generateSecret();
                MSRP_Verify.getInstance().verifyHandler.setVerified(p, secret);
                p.sendMessage("");
                p.sendMessage("§3§lMS§b§lRP");
                p.sendMessage("");
                p.sendMessage("§7Dein §fCode §7lautet: §3" + secret);
                p.sendMessage("§7Gebe diesen Code in unserem §fDiscord §7ein um dich zu §fverifizieren§7!");
                p.sendMessage("§7Nutze hierf\u00fcr den §fBefehl §3/verify" + secret + "§7!");
                p.sendMessage("");
                p.sendMessage("§fDiscord§7: §3https://discord.gg/4EuqWurv");
                p.sendMessage("");
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 5.0f, 5.0f);
            }
        }
        return false;
    }
}
