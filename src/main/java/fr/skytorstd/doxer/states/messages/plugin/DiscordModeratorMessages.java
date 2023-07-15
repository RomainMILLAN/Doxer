package fr.skytorstd.doxer.states.messages.plugin;

public enum DiscordModeratorMessages {
    PLUGIN_DESCRIPTION("Plugin de modération pour discord"),

    PLUGIN_COMMAND_WARN_NAME("/warn <action> <utilisateur>"),
    PLUGIN_COMMAND_WARN_DESCRIPTION("Voir la liste des warns d'un utilisateur"),
    PLUGIN_OPTION_WARN_USER_DESCRIPTION("L'utilisateur dont vous voulez voir ces warns"),
    PLUGIN_OPTION_WARN_ACTION_DESCRIPTION("Action à effectuer"),
    PLUGIN_CHOICE_WARN_SHOW_DESCRIPTION("Lister les warns d'un utilisateur"),
    PLUGIN_CHOICE_WARN_ADD_DESCRIPTION("Ajouter un warn à l'utilisateur"),
    PLUGIN_CHOICE_WARN_REMOVE_DESCRIPTION("Supprimer un warn à un utilisateur"),
    PLUGIN_TEXT_INPUT_WARN_ADD_NAME_DESCRIPTION("Raison du warn"),
    PLUGIN_TEXT_INPUT_WARN_ADD_USERID_DESCRIPTION("Identifiant de l'utilisateur"),
    PLUGIN_TEXT_INPUT_WARN_ADD_STAFFID_DESCRIPTION("Identifiant du staff"),
    PLUGIN_MODAL_WARN_ADD_DESCRIPTION("Ajout d'un warn"),
    PLUGIN_TEXT_INPUT_WARN_REMOVE_WARN_ID_DESCRIPTION("Identifiant du warn à supprimer"),
    PLUGIN_MODAL_WARN_REMOVE_DESCRIPTION("Supprimer un warn"),


    PLUGIN_COMMAND_PROFILE_NAME("/profil <utilisateur>"),
    PLUGIN_COMMAND_PROFILE_DESCRIPTION("Permet de voir le profil utilisateur"),
    PLUGIN_OPTION_PROFILE_USER_DESCRIPTION("Utilisateur à consulter"),

    WARN_NONE("Aucun warns pour cette utilisateur"),
    WARN_ADD("Warn ajouté à %s pour `%s`"),
    WARN_REMOVE("Warn supprimé à %s (`%s`)"),
    WARN_NOT_FOUND("Le warn avec l'identifiant `%s` n'as pas était trouvé"),

    PROFILE_TITLE("%s **%s**"),
    PROFILE_DESCRIPTION("Profile de %s"),
    PROFILE_IDENTIFIANT("Identifiant"),
    PROFILE_WARNS("Nombre de warn"),
    PROFILE_NAME("Effective name"),
    PROFILE_STATUS("Status utilisateur"),
    PROFILE_CREATED_AT("Création du profile"),
    PROFILE_USER_NOT_FOUND("Utilisateur non reconnue"),


    SENTRY_WARN_ADD("Warn ajouté (%s -> `%s`)"),
    SENTRY_WARN_REMOVE("Warn supprimé (%s -> `%s`)"),
    SENTRY_WARN_NOT_FOUND("Warn avec l'identifiant `%s` n'as pas était trouvé dans la BD"),
    SENTRY_WARN_LIST_SUCCESS("Affichage de la liste des warns de %s"),
    SENTRY_PROFILE_NOT_FOUND("Utilisateur non trouvée pour lecture (`%s`)"),
    SENTRY_PROFILE_SEE("Lecture du profile de %s");

    private final String message;

    DiscordModeratorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
