package fr.skytorstd.doxer.manager.database.plugins.discordmoderator;

import fr.skytorstd.doxer.manager.ConsoleManager;
import fr.skytorstd.doxer.manager.database.DatabaseConnection;
import fr.skytorstd.doxer.objects.plugins.discordmoderator.Warn;
import fr.skytorstd.doxer.states.ConsoleState;
import net.dv8tion.jda.api.entities.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DiscordModeratorWarnsDatabase {

    final private static String table_name = "discord_moderator_warns";

    public static int memberCountWarns(Member member) {
        return memberWarns(member).size();
    }

    public static ArrayList<Warn> memberWarns(Member member) {
        final String sql = String.format("SELECT * FROM %s WHERE user_id=%s", table_name, member.getId());
        ConsoleManager.getInstance().toConsole(
                sql,
                ConsoleState.DEBUG
        );
        ArrayList<Warn> warns = new ArrayList<>();

        try {
            ResultSet resultatSQL = DatabaseConnection.getInstance().getStatement().executeQuery(sql);

            while(resultatSQL.next()){
                warns.add(
                        new Warn(
                                resultatSQL.getString("warn_id"),
                                resultatSQL.getString("user_id"),
                                resultatSQL.getString("warn_name"),
                                resultatSQL.getString("user_staff_id"),
                                resultatSQL.getString("created_at")
                        )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return warns;
    }

    public static Warn getWarnById(String warnId) {
        final String sql = String.format("SELECT * FROM %s WHERE warn_id='%s'", table_name, warnId);
        ConsoleManager.getInstance().toConsole(
                sql,
                ConsoleState.DEBUG
        );

        try {
            ResultSet resultatSQL = DatabaseConnection.getInstance().getStatement().executeQuery(sql);

            while(resultatSQL.next()){
                return new Warn(
                        resultatSQL.getString("warn_id"),
                        resultatSQL.getString("user_id"),
                        resultatSQL.getString("warn_name"),
                        resultatSQL.getString("user_staff_id"),
                        resultatSQL.getString("created_at")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public static void addWarn(String userId, String name, String staffId, String createdAt) {
        final String sql = String.format("INSERT INTO %s('user_id', 'warn_name', 'user_staff_id', 'created_at') VALUES('%s', '%s', '%s', '%s')", table_name, userId, name, staffId, createdAt);
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

    public static void removeWarn(String warnId) {
        final String sql = String.format("DELETE FROM %s WHERE warn_id='%s'", table_name, warnId);
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
