package io.acari;

import com.google.gson.Gson;
import io.acari.pojo.NonSerializableProgrammer;
import io.acari.repositories.ProgrammerRepository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;

import static io.acari.repositories.ProgrammerRepository.newProgrammerRepository;

public class TestDataCreater {
    private static final Path TEST_DATA_FILE = Paths.get("src", "test", "resources", "programmers.data").toAbsolutePath();
    private static final Path TEST_DATA_FILE_1 = Paths.get("src", "test", "resources", "programmers.json").toAbsolutePath();
    private static final Gson GSON = new Gson();

    public Path fetchSerializableObjectFile() {
        if (needToWriteToFile(TEST_DATA_FILE)) {
            Consumer<Path> objectWriter = path -> {
                try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(path))) {
                    ProgrammerRepository programmerRepository = newProgrammerRepository();
                    programmerRepository.getProgrammers().forEach(programmer -> {
                        try {
                            out.writeObject(programmer);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
            createData(TEST_DATA_FILE, objectWriter);
        }

        return TEST_DATA_FILE;
    }

    public Path fetchJSONFile(){
        if (needToWriteToFile(TEST_DATA_FILE_1)) {
            Consumer<Path> jsonWriter = path -> {
                try (BufferedWriter out = Files.newBufferedWriter(path)) {
                    newProgrammerRepository().getProgrammers()
                            .map(NonSerializableProgrammer::new)
                            .forEach(programmer -> {
                                try {
                                    out.write(GSON.toJson(programmer));
                                    out.newLine();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
            createData(TEST_DATA_FILE_1, jsonWriter);
        }
        return TEST_DATA_FILE_1;
    }

    private void createData(Path testDataFile, Consumer<Path> consumer) {
        try {
            if (Files.size(testDataFile) == 0) {
                consumer.accept(testDataFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean needToWriteToFile(Path testDataFile) {
        try {
            if (Files.notExists(testDataFile)) {
                Files.createFile(testDataFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
