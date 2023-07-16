package fr.skytorstd.doxer;

import fr.skytorstd.doxer.commands.plugins.*;
import fr.skytorstd.doxer.commands.helper;
import fr.skytorstd.doxer.manager.*;
import fr.skytorstd.doxer.objects.Plugin;
import fr.skytorstd.doxer.states.ConsoleState;
import fr.skytorstd.doxer.states.EnvironementState;
import fr.skytorstd.doxer.states.messages.application.BotMessages;
import fr.skytorstd.doxer.states.messages.application.SystemMessages;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.util.ArrayList;

public class App 
{
    private static Configuration configuration;
    private static JDA jda;
    private static ArrayList<Plugin> plugins = new ArrayList<Plugin>();

    private static EnvironementState environementState = EnvironementState.PRODUCTION;
    private static boolean debugingState = false;

    private static String guildId = "";

    public static void main( String[] args ) throws InterruptedException {
        App.configuration = new Configuration();

        if(App.configuration.getConfiguration("APP_ENV").equals("DEVELOP"))
            App.environementState = EnvironementState.DEVELOPMENT;

        if(App.configuration.getConfiguration("APP_DEBUGING").equalsIgnoreCase("true"))
            App.debugingState = true;

        App.guildId = App.configuration.getConfiguration("GUILD_ID");

        App.runJdaBot();
    }

    /**
     * Run the discord bot with JDA
     *
     * @throws InterruptedException
     */
    public static void runJdaBot() throws InterruptedException {
        App.jda = JDABuilder.createDefault(App.configuration.getConfiguration("BOT_TOKEN"))
                .setIdle(true)
                .enableCache(CacheFlag.ACTIVITY, CacheFlag.VOICE_STATE)
                .setActivity(Activity.watching(BotMessages.ACTIVITY_PLAYING_BOT.getMessage()))
                .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_PRESENCES)
                .build();

        App.jda.awaitStatus(JDA.Status.INITIALIZING);
        ConsoleManager.getInstance().toConsole(BotMessages.JDA_BOT_INITIALIZING.getMessage(), ConsoleState.INFO);

        App.jda.awaitStatus(JDA.Status.CONNECTED);
        ConsoleManager.getInstance().toConsole(BotMessages.JDA_BOT_CONNECTED.getMessage(), ConsoleState.INFO);

        App.jda.awaitReady();
        ConsoleManager.getInstance().toConsole(BotMessages.JDA_BOT_READY.getMessage(), ConsoleState.INFO);

        App.updateCommands();

        ConsoleCommand.consoleCommand();
    }

    public static void updateCommands() {
        jda.addEventListener(new discordModerator());
        jda.addEventListener(new discordSecurity());
        jda.addEventListener(new pollExclamer());
        jda.addEventListener(new weather());
        jda.addEventListener(new voiceClick());
        jda.addEventListener(new grouper());
        jda.addEventListener(new helper());

        jda.getGuildById(App.guildId).updateCommands().addCommands(CommandManager.updateSlashCommands()).queue();
    }

    public static String getConfiguration(String configurationKey) {
        return App.configuration.getConfiguration(configurationKey);
    }

    public static EnvironementState getEnvironementState() {
        return environementState;
    }

    public static boolean isDebuging() {
        return debugingState;
    }

    public static JDA getJda() {
        return jda;
    }

    public static String getGuildId() {
        return guildId;
    }

    public static void addPlugin(Plugin plugin) {
        App.plugins.add(plugin);

        ConsoleManager.getInstance().toConsole(
                plugin.getName() + " | " +  SystemMessages.PLUGIN_REGISTER_SUCCESS.getMessage(),
                ConsoleState.DEBUG
        );
    }

    public static ArrayList<Plugin> getPlugins() {
        return plugins;
    }

    public static Plugin getPluginByName(String pluginName) {
        for (Plugin pluginToSearch : getPlugins()) {
            if(
                    pluginToSearch
                            .getName()
                            .equalsIgnoreCase(pluginName)
            ){
                return pluginToSearch;
            }
        }

        return null;
    }
}
