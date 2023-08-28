// 
// Decompiled by Procyon v0.5.36
// 

package at.msrp.msrp_verify;

import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
import Listener.JoinListener;
import Commands.UnverifyCmd;
import org.bukkit.command.CommandExecutor;
import Commands.VerifyCmd;
import Utils.VerifyHandler;
import Utils.MySQLHandler;
import Utils.ConfigHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class MSRP_Verify extends JavaPlugin
{
    public static String prefix;
    public static String alreadyVerified;
    private static MSRP_Verify instance;
    public ConfigHandler configHandler;
    public MySQLHandler mySQLHandler;
    public VerifyHandler verifyHandler;
    
    public static MSRP_Verify getInstance() {
        return MSRP_Verify.instance;
    }
    
    public void onEnable() {
        MSRP_Verify.instance = this;
        (this.configHandler = new ConfigHandler()).createConfig();
        this.mySQLHandler = new MySQLHandler(this.configHandler.getConfigSetting("host"), this.configHandler.getConfigSetting("port"), this.configHandler.getConfigSetting("database"), this.configHandler.getConfigSetting("username"), this.configHandler.getConfigSetting("password"));
        this.verifyHandler = new VerifyHandler();
        this.getCommand("verify").setExecutor((CommandExecutor)new VerifyCmd());
        this.getCommand("unverify").setExecutor((CommandExecutor)new UnverifyCmd());
        this.getServer().getPluginManager().registerEvents((Listener)new JoinListener(), (Plugin)this);
        System.out.println();
        System.out.println("§3MS§bRP §fVerify-System");
        System.out.println("Version: 1.0");
        System.out.println("Developed by Creeper_craft206");
        System.out.println();
    }
    
    public void onDisable() {
    }
    
    static {
        MSRP_Verify.prefix = "§3§lMS§b§lRP §8| §7";
        MSRP_Verify.alreadyVerified = MSRP_Verify.prefix + "Du bist bereits verifiziert!";
    }
}
