package fr.skytorstd.doxer.manager.database.plugins.voiceclick;

import fr.skytorstd.doxer.manager.ConsoleManager;
import fr.skytorstd.doxer.manager.database.DatabaseConnection;
import fr.skytorstd.doxer.objects.plugins.VoiceClick;
import fr.skytorstd.doxer.states.ConsoleState;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VoiceClickDatabase {
    final private static String table_name = "voice_click";

    public static ArrayList<VoiceClick> voiceClickChannels() {
        final String sql = String.format("SELECT * FROM %s", table_name);
        ConsoleManager.getInstance().toConsole(
                sql,
                ConsoleState.DEBUG
        );
        ArrayList<VoiceClick> voiceClicks = new ArrayList<>();

        try {
            ResultSet resultatSQL = DatabaseConnection.getInstance().getStatement().executeQuery(sql);

            while(resultatSQL.next()){
                voiceClicks.add(
                        new VoiceClick(
                                resultatSQL.getInt("vc_id"),
                                resultatSQL.getString("vc_name"),
                                resultatSQL.getString("vc_channel_id")
                        )
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return voiceClicks;
    }

    public static VoiceClick getVoiceClick(String vc_id) {
        final String sql = String.format(
                "SELECT * FROM %s WHERE vc_id='%s'",
                table_name,
                vc_id
        );
        ConsoleManager.getInstance().toConsole(
                sql,
                ConsoleState.DEBUG
        );

        try {
            ResultSet resultatSQL = DatabaseConnection.getInstance().getStatement().executeQuery(sql);

            while(resultatSQL.next()){
                return new VoiceClick(
                        resultatSQL.getInt("vc_id"),
                        resultatSQL.getString("vc_name"),
                        resultatSQL.getString("vc_channel_id")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static VoiceClick getVoiceClickByChannelId(String channel_id) {
        final String sql = String.format(
                "SELECT * FROM %s WHERE vc_channel_id='%s'",
                table_name,
                channel_id
        );
        ConsoleManager.getInstance().toConsole(
                sql,
                ConsoleState.DEBUG
        );

        try {
            ResultSet resultatSQL = DatabaseConnection.getInstance().getStatement().executeQuery(sql);

            while(resultatSQL.next()){
                return new VoiceClick(
                        resultatSQL.getInt("vc_id"),
                        resultatSQL.getString("vc_name"),
                        resultatSQL.getString("vc_channel_id")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static void addVoiceClickChannel(VoiceClick vc) {
        final String sql = String.format(
                "INSERT INTO %s('vc_name', 'vc_channel_id') VALUES('%s', '%s')",
                table_name,
                vc.getVc_name(),
                vc.getVc_channel_id()
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

    public static void removeVoiceClickChannel(String vc_id) {
        final String sql =  String.format(
                "DELETE FROM %s WHERE vc_id='%s'",
                table_name,
                vc_id
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
