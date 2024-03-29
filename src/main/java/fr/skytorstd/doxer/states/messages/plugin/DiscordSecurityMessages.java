package fr.skytorstd.doxer.states.messages.plugin;

import fr.skytorstd.doxer.states.messages.IconMessages;
import fr.skytorstd.doxer.states.plugins.DiscordSecurityStates;

public enum DiscordSecurityMessages {

    PLUGIN_DESCRIPTION("Plugin permetant de filtrer les personnes qui rentrent sur votre serveur discord"),

    PLUGIN_COMMAND_SECURITY("/security"),
    PLUGIN_COMMAND_SECURITY_DESCRIPTION("Permet de désigner le groupe par défaut et visualiser le status de la sécurité"),
    PLUGIN_COMMAND_CONFIRM("/confirm <utilisateur> <groupe>"),
    PLUGIN_COMMAND_CONFIRM_DESCRIPTION("Permet de confirmer un utilisateur quand la sécurité est activée sur votre serveur"),
    PLUGIN_OPTION_CONFIRM_USER_DESCRIPTION("Utilisateur à authentifier"),
    PLUGIN_OPTION_CONFIRM_GROUP_DESCRIPTION("Groupe à ajouté à l'utilisateur"),
    PLUGIN_OPTION_CONFIRM_NICKNAME_DESCRIPTION("Nickname de l'utilisateur"),

    SECURITY_STATE("Le status de la sécurité est définie sur `%s`"),
    EMBED_TITLE(IconMessages.WRENCH.getIcon() + " **" + DiscordSecurityStates.PLUGIN_NAME.getState() + "**"),
    CONFIRM_MESSAGE("%s, viens de se faire confirmer par %s"),
    WELCOME_SECURITY_MESSAGE("Bienvenue %s, attends qu'un %s arrive pour te confirmer et avoir accès à l'entièreté du serveur !"),

    SENTRY_CONFIRM("Confirmation du compte %s"),
    SENTRY_SECURITY_FALSE_MEMBER_JOIN("Un nouveau membre vient d'arriver le rôle %s vient de lui être ajoutée automatiquement."),
    MESSAGE("MESSAGE");

    private final String message;

    DiscordSecurityMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
