package fr.skytorstd.doxerbot.databases.plugins;

import fr.skytorstd.doxerbot.databases.DatabaseConnection;
import fr.skytorstd.doxerbot.object.VoiceClickChannel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VoiceClickDatabase {
    /*
    GETTER
     */
    /**
     * Return a liste of VoiceClickChannel
     * @return
     */
    public static ArrayList<VoiceClickChannel> getAllVoiceClickChanels(String idGuild){
        final String sql = "SELECT * FROM VC_Creator WHERE idGuild='"+idGuild+"'";
        ArrayList<VoiceClickChannel> resultat = new ArrayList<>();

        try {
            ResultSet ResultatSQL = DatabaseConnection.getInstance().getStatement().executeQuery(sql);

            while(ResultatSQL.next()){
                resultat.add(new VoiceClickChannel(ResultatSQL.getString("id_channel"), ResultatSQL.getString("name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultat;
    }
    public static VoiceClickChannel getVoiceClickChannel(String id_channel, String idGuild){
        final String sql = "SELECT * FROM VC_Creator WHERE idGuild='"+idGuild+"'";

        try {
            ResultSet ResultatSQL = DatabaseConnection.getInstance().getStatement().executeQuery(sql);

            while(ResultatSQL.next()){
                return new VoiceClickChannel(ResultatSQL.getString("id_channel"), ResultatSQL.getString("name"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
    /*
    AJOUT
     */
    /**
     * Add a VoiceClickChannel to the DB
     * @param vcc
     */
    public static void addVoiceClickChannel(VoiceClickChannel vcc, String idGuild){
        final String sql = "INSERT INTO VC_Creator('id_channel', 'name', 'idGuild') VALUES('"+vcc.getId_channel()+"', '"+vcc.getName()+"', '"+idGuild+"');";

        try {
            DatabaseConnection.getInstance().getStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /*
    REMOVE
     */
    /**
     * Remove a VoiceClickChannel to the DB
     * @param id_channel
     */
    public static void removeVoiceClickChannel(String id_channel, String idGuild){
        final String sql = "DELETE FROM VC_Creator WHERE id_channel='"+id_channel+"' AND idGuild='"+idGuild+"'";

        try {
            DatabaseConnection.getInstance().getStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
