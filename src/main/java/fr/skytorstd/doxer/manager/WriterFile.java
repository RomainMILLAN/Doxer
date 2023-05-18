package fr.skytorstd.doxer.manager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriterFile {
    private static WriterFile instance = null;

    /**
     * Constructeur vide de singleton
     */
    public WriterFile() {}

    /**
     * Retourne l'instance et la crée si elle n'existe pas
     * @return
     */
    public static WriterFile getInstance() {
        if(instance == null){
            instance = new WriterFile();
        }
        return instance;
    }

    /**
     * Ecrite le message passer en parametre dans le fichier passer en parametre
     * @param fileName
     * @param toWrite
     */
    public void writeOnFile(String fileName, String toWrite) {
        File file = new File(fileName);

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(file.getAbsoluteFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(toWrite);
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
