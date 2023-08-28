// 
// Decompiled by Procyon v0.5.36
// 

package Utils;

import org.apache.commons.lang.RandomStringUtils;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import at.msrp.msrp_verify.MSRP_Verify;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.bukkit.entity.Player;

public class VerifyHandler
{
    public void setVerified(final Player p, final String secret) {
        final String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        MSRP_Verify.getInstance().mySQLHandler.update("UPDATE verify WHERE MC_UUID='" + p.getUniqueId() + "' SET Secret='" + secret + "' AND TimeStamp='" + timeStamp + "';");
    }
    
    public boolean isVerified(final Player p) {
        PreparedStatement State = null;
        try {
            State = MSRP_Verify.getInstance().mySQLHandler.con.prepareStatement("SELECT * FROM verify WHERE DC_ID='" + p.getUniqueId() + "';");
            final ResultSet Result = State.executeQuery();
            if (Result != null && Result.next()) {
                return true;
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
    
    public String generateSecret() {
        String key = "";
        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        key = RandomStringUtils.random(6, chars);
        return key;
    }
}
