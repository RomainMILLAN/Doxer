package fr.skytorstd.doxer.commands.plugins;

import com.github.prominence.openweathermap.api.OpenWeatherMapClient;
import com.github.prominence.openweathermap.api.enums.Language;
import com.github.prominence.openweathermap.api.enums.UnitSystem;
import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.manager.embedCrafter.plugins.WeatherCrafter;
import fr.skytorstd.doxer.objects.Plugin;
import fr.skytorstd.doxer.objects.pluginSlashInterface;
import fr.skytorstd.doxer.states.messages.plugin.DiscordSecurityMessages;
import fr.skytorstd.doxer.states.messages.plugin.WeatherMessages;
import fr.skytorstd.doxer.states.plugins.DiscordSecurityStates;
import fr.skytorstd.doxer.states.plugins.WeatherStates;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.util.ArrayList;

public class weather extends pluginSlashInterface {

    public weather() {
        initPlugin();
    }

    @Override
    public void initPlugin() {
        App.addPlugin(
                new Plugin(
                        WeatherStates.PLUGIN_NAME.getState(),
                        WeatherMessages.PLUGIN_DESCRIPTION.getMessage(),
                        new ArrayList<String>() {
                            {
                                add(WeatherMessages.PLUGIN_COMMAND_WEATHER.getMessage());
                            }
                        }
                )
        );
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        if(event.getName().equalsIgnoreCase(WeatherStates.PLUGIN_NAME.getState())) {
            String ville = "";
            if(null == event.getOption(WeatherStates.PLUGIN_OPTION_WEATHER_VILLE_NAME.getState())){
                ville = App.getConfiguration("WEATHER_DEFAULT_VILLE");
            }else {
                ville = event.getOption(WeatherStates.PLUGIN_OPTION_WEATHER_VILLE_NAME.getState()).getAsString();
            }

            OpenWeatherMapClient openWeatherClient = new OpenWeatherMapClient("08405ba2c247bb8543d82316fe5fb107");
            openWeatherClient.setReadTimeout(1000);
            openWeatherClient.setConnectionTimeout(1000);

            final com.github.prominence.openweathermap.api.model.weather.Weather weather = openWeatherClient.currentWeather().single().byCityName(ville).language(Language.FRENCH).unitSystem(UnitSystem.METRIC).retrieve().asJava();
            event.replyEmbeds(
                    WeatherCrafter.craftWeatherEmbed(weather).build()
            ).queue();
            //e.replyEmbeds(WeatherCrafter.craftEmbedWeather(weather)).queue();
        }

    }
}
