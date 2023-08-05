package fr.skytorstd.doxer.manager.embedCrafter.plugins;

import com.github.prominence.openweathermap.api.model.weather.Weather;
import fr.skytorstd.doxer.manager.embedCrafter.EmbedCrafterBase;
import fr.skytorstd.doxer.states.messages.IconMessages;
import net.dv8tion.jda.api.EmbedBuilder;

import java.awt.*;

public class WeatherCrafter extends EmbedCrafterBase {

    private static EmbedBuilder craftWeatherBase() {
        EmbedBuilder embed = craftEmbedBase();

        embed.setColor(Color.BLACK);

        return embed;
    }

    public static EmbedBuilder craftWeatherEmbed(Weather weather){
        EmbedBuilder embed = craftWeatherBase();

        embed.setTitle(IconMessages.METEO.getIcon() + " **Météo: " + weather.getLocation().getName() + "**");
        embed.setThumbnail(weather.getWeatherState().getWeatherIconUrl());
        embed.addField("**Température**", IconMessages.TEMPERATURE.getIcon() + " " + weather.getTemperature().getValue() + "°C\n" + IconMessages.ARROW_UP.getIcon() + " " + weather.getTemperature().getMaxTemperature() + "°C\n" + IconMessages.ARROW_DOWN.getIcon() + " " + weather.getTemperature().getMinTemperature() + "°C", true);
        embed.addField("**Informations**", IconMessages.WIND.getIcon() + " " + weather.getWind().getSpeed() + "km/h\n" + IconMessages.BULLES.getIcon() + " " + weather.getHumidity().getValue() + "%\n" + IconMessages.PRESSURE.getIcon() + " " + weather.getAtmosphericPressure().getValue() + "hPa", true);
        embed.addField("**Ephéméride**", IconMessages.EPHEMERIDE_DAY.getIcon() + " " + weather.getLocation().getSunriseTime().getHour() + ":" + weather.getLocation().getSunriseTime().getMinute() + "\n" + IconMessages.EPHEMERIDE_NIGHT.getIcon() + " " + weather.getLocation().getSunsetTime().getHour() + ":" + weather.getLocation().getSunsetTime().getMinute(), true);
        embed.setFooter(getFooterEmbed());

        return embed;
    }

}
