package fr.skytorstd.doxer.manager;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.manager.embedCrafter.DiscordSentryCrafter;
import fr.skytorstd.doxer.states.ConsoleColors;
import fr.skytorstd.doxer.states.ConsoleState;
import fr.skytorstd.doxer.states.plugins.DiscordSentryStates;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class DiscordSentryManager {
    private static DiscordSentryManager INSTANCE = null;
    private String fileName = "discord_sentry.txt";
    private TextChannel sentryTextChannel = null;

    public DiscordSentryManager() {}

    public static DiscordSentryManager getInstance() {
        if(DiscordSentryManager.INSTANCE == null)
            DiscordSentryManager.INSTANCE = new DiscordSentryManager();

        return INSTANCE;
    }

    public void toLog(String sentryTitle, String description, Member member, Guild guild){
        this.toLogIntoTextChannel(
                sentryTitle,
                description,
                member
        );
        this.toLogIntoConsole(
                guild,
                sentryTitle,
                description,
                member
        );
        this.toLogIntoFile(
                guild,
                sentryTitle,
                description,
                member
        );
    }

    private void toLogIntoTextChannel(String sentryTitle, String description, Member member){
        if(null == this.sentryTextChannel)
            setSentryTextChannel();

        this.sentryTextChannel.sendMessageEmbeds(
                DiscordSentryCrafter.craftDiscordSentryEmbed(
                        sentryTitle,
                        description,
                        member
                ).build()
        ).queue();
    }

    private void toLogIntoConsole(Guild guild, String sentryTitle, String message, Member member){
        //'Doxer Beta Testing' | DiscordSentry/Message | Wabezeter -> A fait X
        ConsoleManager.getInstance().toConsole("'" + guild.getName() + "'" +  ConsoleColors.PURPLE.getConsoleColor()+" | " + ConsoleColors.RESET.getConsoleColor() + DiscordSentryStates.PLUGIN_NAME.getState()+"/"+sentryTitle + ConsoleColors.PURPLE.getConsoleColor() + " | " + ConsoleColors.RESET.getConsoleColor() + member.getEffectiveName() + " -> " + message, ConsoleState.LOG);
    }
    private void toLogIntoFile(Guild guild, String sentryTitle, String message, Member member){
        //[XX/XX/XXXX XX:XX] Wabezeter - Doxer Beta Testing/Messages | A fait X
        WriterFile.getInstance().writeOnFile(fileName, "\n[" + DateHourFormatter.getInstance().getDateAndHourTimeFormat() + "] " + member.getEffectiveName() + " - " + guild.getName() + "/" + sentryTitle + " | " + message);
    }


    private void setSentryTextChannel(){
        getInstance().sentryTextChannel = App.getJda().getTextChannelById(App.getConfiguration("TC_DISCORD_SENTRY"));
    }

}
