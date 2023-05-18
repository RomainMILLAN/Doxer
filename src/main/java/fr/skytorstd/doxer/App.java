package fr.skytorstd.doxer;

import fr.skytorstd.doxer.manager.Configuration;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

public class App 
{
    private static Configuration configuration;
    private static JDA jda;

    public static void main( String[] args )
    {
        App.configuration = new Configuration();
        
        App.jda = JDABuilder.createDefault(App.configuration.getConfiguration("BOT_TOKEN"))
            .setIdle(true)
            .enableCache(CacheFlag.ACTIVITY, CacheFlag.VOICE_STATE)
            .setActivity(Activity.watching("Doxer bot - Version 2"))
            .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.GUILD_PRESENCES)
            .build();
    }

}
