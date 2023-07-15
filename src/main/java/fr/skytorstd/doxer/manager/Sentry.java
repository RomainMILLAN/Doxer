package fr.skytorstd.doxer.manager;

import fr.skytorstd.doxer.App;
import fr.skytorstd.doxer.manager.embedCrafter.SentryCrafter;
import fr.skytorstd.doxer.states.ConsoleColors;
import fr.skytorstd.doxer.states.ConsoleState;
import fr.skytorstd.doxer.states.LogState;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

public class Sentry {
    private static Sentry INSTANCE = null;
    private String fileName = "sentry.txt";
    private TextChannel sentryTextChannel = null;

    public Sentry() {}

    public static Sentry getInstance() {
        if(Sentry.INSTANCE == null)
            Sentry.INSTANCE = new Sentry();

        return INSTANCE;
    }

    public void toLog(String pluginName, String description, String command, LogState logState, Member member, Guild guild){
        this.toLogIntoTextChannel(pluginName, description, command, logState, member);
        this.toLogIntoConsole(guild, pluginName, description, member);
        this.toLogIntoFile(guild, pluginName, description, member);
    }

    public void toLog(String pluginName, String description, LogState logState, Member member, Guild guild){
        this.toLogIntoTextChannel(pluginName, description, logState, member);
        this.toLogIntoConsole(guild, pluginName, description, member);
        this.toLogIntoFile(guild, pluginName, description, member);
    }

    private void toLogIntoTextChannel(String pluginName, String description, String command, LogState logState, Member member){
        if(null == this.sentryTextChannel)
            setSentryTextChannel();

        this.sentryTextChannel.sendMessageEmbeds(
                SentryCrafter.craftSentryEmbedCommand(
                        pluginName,
                        description,
                        command,
                        logState,
                        member
                ).build()
        ).queue();
    }

    private void toLogIntoTextChannel(String pluginName, String description, LogState logState, Member member){
        if(null == this.sentryTextChannel)
            setSentryTextChannel();

        this.sentryTextChannel.sendMessageEmbeds(
                SentryCrafter.craftSentryEmbed(
                        pluginName,
                        description,
                        logState,
                        member
                ).build()
        ).queue();
    }

    private void toLogIntoConsole(Guild guild, String pluginName, String message, Member member){
        //'Doxer Beta Testing' | sentry | Wabezeter -> A fait X
        ConsoleManager.getInstance().toConsole("'" + guild.getName() + "'" +  ConsoleColors.PURPLE.getConsoleColor()+" | " + ConsoleColors.RESET.getConsoleColor() + pluginName + ConsoleColors.PURPLE.getConsoleColor() + " | " + ConsoleColors.RESET.getConsoleColor() + member.getEffectiveName() + " -> " + message, ConsoleState.LOG);
    }
    private void toLogIntoFile(Guild guild, String pluginName, String message, Member member){
        //[XX/XX/XXXX XX:XX] Wabezeter - Doxer Beta Testing/Sentry | A fait X
        WriterFile.getInstance().writeOnFile(fileName, "\n[" + DateHourFormatter.getInstance().getDateAndHourTimeFormat() + "] " + member.getEffectiveName() + " - " + guild.getName() + "/" + pluginName + " | " + message);
    }


    private void setSentryTextChannel(){
        getInstance().sentryTextChannel = App.getJda().getTextChannelById(App.getConfiguration("TC_SENTRY"));
    }

}
