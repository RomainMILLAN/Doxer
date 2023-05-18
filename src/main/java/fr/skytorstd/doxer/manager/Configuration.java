package fr.skytorstd.doxer.manager;

import java.util.HashMap;

import io.github.cdimascio.dotenv.Dotenv;

public class Configuration {

    private HashMap<String, String> configuration;
    
    public Configuration(){
        this.configuration = new HashMap<>();

        this.loadConfiguration();
    }

    /**
     * Load the configuration of .env file and store it on configuration property class
     */
    private void loadConfiguration(){
        Dotenv configurationDotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

        configurationDotenv.entries().forEach(element -> {
            this.configuration.put(element.getKey(), element.getValue());
        });
    }

    /**
     * @param configurationKey
     * @return <code>String</code> value of key passed in parameter in .env
     */
    public String getConfiguration(String configurationKey) {
        String res = "";

        res = this.configuration.get(configurationKey);

        return res;
    }
    
}
