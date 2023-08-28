// 
// Decompiled by Procyon v0.5.36
// 

package Utils;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class MySQLHandler
{
    public Connection con;
    public String host;
    public String port;
    public String database;
    public String username;
    public String password;
    
    public MySQLHandler(final String host, final String port, final String database, final String username, final String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
        this.connect();
        this.createTables();
    }
    
    public void connect() {
        if (!this.isConnected()) {
            try {
                this.con = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?autoReconnect=true", this.username, this.password);
                System.out.println("Die MySQL Verbindung wurde erfolgreich aufgebaut.");
            }
            catch (SQLException var1) {
                var1.printStackTrace();
            }
        }
    }
    
    public void close() {
        if (this.isConnected()) {
            try {
                this.con.close();
                System.out.println("Die MySQL Verbindung wurde erfolgreich geschlossen.");
            }
            catch (SQLException var1) {
                var1.printStackTrace();
            }
        }
    }
    
    public boolean isConnected() {
        return this.con != null;
    }
    
    public void update(final String query) {
        PreparedStatement ps = null;
        try {
            ps = this.con.prepareStatement(query);
            ps.executeUpdate();
        }
        catch (SQLException var11) {
            var11.printStackTrace();
            try {
                ps.close();
            }
            catch (SQLException var12) {
                var12.printStackTrace();
            }
        }
        finally {
            try {
                ps.close();
            }
            catch (SQLException var13) {
                var13.printStackTrace();
            }
        }
    }
    
    public void insert(final String table, final String[] values) {
        try {
            String in = "?";
            for (int i = 1; i < values.length; ++i) {
                in = String.valueOf(String.valueOf(in)) + ", ?";
            }
            final PreparedStatement State = this.con.prepareStatement("INSERT INTO " + table + " values(" + in + ");");
            for (int index = 1; index <= values.length; ++index) {
                State.setString(index, values[index - 1]);
            }
            State.execute();
            State.close();
        }
        catch (Exception var5) {
            var5.printStackTrace();
        }
    }
    
    public ResultSet getResult(final String query) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = this.con.prepareStatement(query);
            rs = ps.executeQuery();
            return rs;
        }
        catch (SQLException var4) {
            var4.printStackTrace();
            return null;
        }
    }
    
    public boolean isRegistered(final String query) {
        boolean bool = false;
        try {
            final ResultSet rs = this.getResult(query);
            try {
                bool = rs.next();
            }
            catch (SQLException var11) {
                bool = false;
            }
            finally {
                rs.close();
            }
        }
        catch (SQLException var10) {
            var10.printStackTrace();
        }
        return bool;
    }
    
    public void createTables() {
        if (this.isConnected()) {
            try {
                this.con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS verify (MC_UUID VARCHAR(100) UNIQUE, DC_ID VARCHAR(100) UNIQUE, Username VARCHAR(100), Secret VARCHAR(100), TimeStamp VARCHAR(100));");
            }
            catch (SQLException var1) {
                var1.printStackTrace();
            }
        }
    }
}
