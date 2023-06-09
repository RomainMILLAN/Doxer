package fr.skytorstd.doxerbot.databases;

import fr.skytorstd.doxerbot.App;
import fr.skytorstd.doxerbot.object.Plugin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConfigurationPluginsDatabase {
    /*
    GETTER
     */

    /**
     * Retourne le status d'un plugin par son nom
     * @param pluginName
     * @return
     */
    public static boolean getStatePluginWithPluginName(String pluginName, String idGuild) {
        String sql = "SELECT * FROM configurationPlugins WHERE pluginName='" + pluginName + "' AND idGuild='"+idGuild+"'";
        int intBoolean = 0;

        try {
            ResultSet resultatSQL = DatabaseConnection.getInstance().getStatement().executeQuery(sql);

            intBoolean = resultatSQL.getInt("pluginState");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        if(intBoolean == 1)
            return true;
        else
            return false;
    }

    /**
     * Retourne une liste de String contenant tous les nom des plugins contenue dans la DB
     * @return
     */
    public static ArrayList<String> getAllPluginNameInDB(){
        ArrayList<String> pluginNameList = new ArrayList<>();
        String sql = "SELECT pluginName FROM configurationPlugins";

        try {
            ResultSet resultatSQL = DatabaseConnection.getInstance().getStatement().executeQuery(sql);

            while(resultatSQL.next()){
                pluginNameList.add(resultatSQL.getString("pluginName"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return pluginNameList;
    }

    public static boolean pluginConfigExist(String pluginName, String idGuild){
        String sql = "SELECT COUNT(*) as nb FROM configurationPlugins WHERE pluginName='"+pluginName+"' AND idGuild='"+idGuild+"'";

        try {
            ResultSet ResultatSQL = DatabaseConnection.getInstance().getStatement().executeQuery(sql);

            while(ResultatSQL.next()){
                if(ResultatSQL.getInt("nb") == 1){
                    return true;
                }else {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    /*
    CREATE
     */
    public static void initPluginConfig(String idGuild){
        for(Plugin pl : App.getPlugins()){
            String sql = "INSERT INTO configurationPlugins('idGuild', 'pluginName', 'pluginState') VALUES('"+idGuild+"', '"+pl.getName()+"', '0');";

            try {
                DatabaseConnection.getInstance().getStatement().execute(sql);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    /*
    UPDATE
       */
    public static void updatePluginStateWithPluginAndIdGuild(Plugin plugin, String idGuild){
        int pluginStateInt = 0;
        if(!ConfigurationPluginsDatabase.getStatePluginWithPluginName(plugin.getName(), idGuild))
            pluginStateInt = 1;

        String sql = "";
        if(ConfigurationPluginsDatabase.pluginConfigExist(plugin.getName(), idGuild))
            sql = "UPDATE configurationPlugins SET pluginState='"+pluginStateInt+"' WHERE pluginName='"+plugin.getName()+"' AND idGuild='"+idGuild+"'";
        else
            sql = "INSERT INTO configurationPlugins('pluginName', 'pluginState', 'idGuild') VALUES('"+plugin.getName()+"','"+pluginStateInt+"','"+idGuild+"');";


        try {
            DatabaseConnection.getInstance().getStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /*
    DELETE
     */
}
