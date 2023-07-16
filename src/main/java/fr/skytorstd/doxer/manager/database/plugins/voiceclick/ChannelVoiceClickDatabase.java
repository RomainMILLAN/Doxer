package fr.skytorstd.doxer.manager.database.plugins.voiceclick;

import fr.skytorstd.doxer.manager.ConsoleManager;
import fr.skytorstd.doxer.manager.database.DatabaseConnection;
import fr.skytorstd.doxer.states.ConsoleState;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChannelVoiceClickDatabase {
    final private static String table_name = "channel_voice_click";

    public static boolean isChannelVoiceClick(String vc_channel_id) {
        final String sql = String.format(
                "SELECT COUNT(*) as channel FROM %s WHERE channel_id='%s'",
                table_name,
                vc_channel_id
        );
        ConsoleManager.getInstance().toConsole(
                sql,
                ConsoleState.DEBUG
        );

        try {
            ResultSet resultatSQL = DatabaseConnection.getInstance().getStatement().executeQuery(sql);

            while(resultatSQL.next()){
                if(resultatSQL.getInt("channel") > 0){
                    return true;
                }

                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
    public static void addChannelVoiceClick(String vc_channel_id) {
        final String sql = String.format(
                "INSERT INTO %s('channel_id') VALUES('%s')",
                table_name,
                vc_channel_id
        );
        ConsoleManager.getInstance().toConsole(
                sql,
                ConsoleState.DEBUG
        );

        try {
            DatabaseConnection.getInstance().getStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removeChannelVoiceClick(String vc_channel_id) {
        final String sql = String.format(
                "DELETE FROM %s WHERE channel_id='%s'",
                table_name,
                vc_channel_id
        );
        ConsoleManager.getInstance().toConsole(
                sql,
                ConsoleState.DEBUG
        );

        try {
            DatabaseConnection.getInstance().getStatement().execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
