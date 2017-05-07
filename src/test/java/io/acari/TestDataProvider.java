package io.acari;

import io.acari.pojo.Programmer;
import io.acari.repositories.ProgrammerRepository;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.acari.repositories.ProgrammerRepository.newProgrammerRepository;

public class TestDataProvider {
    private static final Map<String, Programmer> programmers = new LinkedHashMap<>();

    static {
        Path testDataFile = Paths.get("src","test", "resources", "programmers.data").toAbsolutePath();
        try {
            boolean notExists = Files.notExists(testDataFile);
            if(notExists || Files.size(testDataFile) == 0){
                if(notExists){
                    Files.createFile(testDataFile);
                }
                try(ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(testDataFile))){
                    ProgrammerRepository programmerRepository = newProgrammerRepository();
                    programmerRepository.getProgrammers().forEach(programmer -> {
                        try {
                            out.writeObject(programmer);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        Path testDataFile = Paths.get("src","test", "resources", "programmers.data").toAbsolutePath();
        if (Files.exists(testDataFile)) {
            try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(testDataFile))) {
                try {
                    while (true) {
                        Programmer programmer = (Programmer) in.readObject();
                        programmers.put(programmer.getName(), programmer);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (EOFException ignored) {
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Map<String, Programmer> getProgrammers() {
        return programmers;
    }


}
