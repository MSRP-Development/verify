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

public class UnverifyCmd implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (MSRP_Verify.getInstance().verifyHandler.isVerified(p)) {
                MSRP_Verify.getInstance().mySQLHandler.update("UPDATE verify WHERE MC_UUID='" + p.getUniqueId() + "' SET Secret='' AND TimeStamp='' AND DC_ID='';");
                p.sendMessage(MSRP_Verify.prefix + "§cDu wurdest erfolgreich unverifiziert!");
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 5.0f, 5.0f);
            }
            else {
                p.sendMessage(MSRP_Verify.prefix + "§cDu bist nicht verifiziert!");
                p.playSound(p.getLocation(), Sound.VILLAGER_NO, 5.0f, 5.0f);
            }
        }
        return false;
    }
}
