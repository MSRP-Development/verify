// 
// Decompiled by Procyon v0.5.36
// 

package Utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.IOException;
import java.io.File;

public class ConfigHandler
{
    public void createConfig() {
        final File ordner = new File("plugins//Verify//");
        final File file = new File("plugins//Verify//mysql.yml");
        if (!ordner.exists()) {
            ordner.mkdir();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        final FileConfiguration cfg = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        cfg.set("host", (Object)"localhost");
        cfg.set("port", (Object)"3306");
        cfg.set("database", (Object)"verify");
        cfg.set("username", (Object)"root");
        cfg.set("password", (Object)"your_password");
        try {
            cfg.save(file);
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }
    
    public String getConfigSetting(final String setting) {
        final File file = new File("plugins//Verify//mysql.yml");
        final FileConfiguration cfg = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
        return cfg.getString(setting);
    }
}
