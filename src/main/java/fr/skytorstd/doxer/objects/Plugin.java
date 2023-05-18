package fr.skytorstd.doxer.objects;

import java.util.List;

public class Plugin {
    private String name;
    private String description;
    private List<String> commands;

    public Plugin(String name, String description, List<String> commands) {
        this.name = name;
        this.description = description;
        this.commands = commands;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }
}
