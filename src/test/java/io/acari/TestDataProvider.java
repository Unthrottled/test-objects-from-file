package io.acari;

import com.google.gson.Gson;
import io.acari.pojo.NonSerializableProgrammer;
import io.acari.pojo.Programmer;
import io.acari.repositories.ProgrammerRepository;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static io.acari.repositories.ProgrammerRepository.newProgrammerRepository;

public class TestDataProvider {
    private static final Map<String, Programmer> programmers = new LinkedHashMap<>();
    private static Map<String, NonSerializableProgrammer> nonSerialProgrammers = new LinkedHashMap<>();
    private static final Path TEST_DATA_FILE = Paths.get("src", "test", "resources", "programmers.data").toAbsolutePath();
    private static final Path TEST_DATA_FILE_1 = Paths.get("src", "test", "resources", "programmers.json").toAbsolutePath();
    private static final Gson GSON = new Gson();

    static {
        if (createFile(TEST_DATA_FILE)) {
            createData(TEST_DATA_FILE, path -> {
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
            });
        }

        if (createFile(TEST_DATA_FILE_1)) {
            createData(TEST_DATA_FILE_1, path -> {
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
            });
        }
    }

    static {
        if (Files.exists(TEST_DATA_FILE)) {
            try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(TEST_DATA_FILE))) {
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
        if (Files.exists(TEST_DATA_FILE_1)) {
            try {
                nonSerialProgrammers = Files.lines(TEST_DATA_FILE_1)
                        .map(programmerJson -> GSON.fromJson(programmerJson, NonSerializableProgrammer.class))
                        .collect(Collectors.toMap(NonSerializableProgrammer::getName, Function.identity()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createData(Path testDataFile, Consumer<Path> consumer) {
        try {
            if (Files.size(testDataFile) == 0) {
                consumer.accept(testDataFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean createFile(Path testDataFile) {
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

    public static Map<String, Programmer> getProgrammers() {
        return programmers;
    }

    public static Map<String, NonSerializableProgrammer> getNonSerialProgrammers() {
        return nonSerialProgrammers;
    }
}
