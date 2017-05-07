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
import java.util.LinkedList;
import java.util.List;

import static io.acari.repositories.ProgrammerRepository.newProgrammerRepository;

public class TestDataProvider {
    private static final List<Programmer> programmers = new LinkedList<>();

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
                        programmers.add((Programmer) in.readObject());
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

    public static List<Programmer> getProgrammers() {
        return programmers;
    }


}
