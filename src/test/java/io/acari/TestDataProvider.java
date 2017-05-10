package io.acari;

import com.google.gson.Gson;
import io.acari.pojo.NonSerializableProgrammer;
import io.acari.pojo.Programmer;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TestDataProvider {
    private static Map<String, Programmer> programmers;
    private static Map<String, NonSerializableProgrammer> nonSerialProgrammers = new LinkedHashMap<>();
    private static final Gson GSON = new Gson();

    static {
        TestDataCreater testDataCreater = new TestDataCreater();
        Path path = testDataCreater.fetchSerializableObjectFile();
        programmers = createProgrammersFromFile(path);
        nonSerialProgrammers = createProgrammersFromJSON(testDataCreater.fetchJSONFile());
    }

    private static Map<String, NonSerializableProgrammer> createProgrammersFromJSON(Path path) {
        if (Files.exists(path)) {
            try {
                return Files.lines(path)
                        .map(programmerJson -> GSON.fromJson(programmerJson, NonSerializableProgrammer.class))
                        .collect(Collectors.toMap(NonSerializableProgrammer::getName, Function.identity()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Collections.emptyMap();
    }

    private static Map<String, Programmer> createProgrammersFromFile(Path path) {
        Map<String, Programmer> emptyMap = Collections.emptyMap();
        if (Files.notExists(path)) {
            return emptyMap;
        }

        Map<String, Programmer> programmerMap = new LinkedHashMap<>();
        try (ObjectInputStream in = new ObjectInputStream(Files.newInputStream(path))) {
            try {
                while (true) {
                    Programmer programmer = (Programmer) in.readObject();
                    programmerMap.put(programmer.getName(), programmer);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (EOFException ignored) {
                    /*
                 * Reached the end of the file.
                 * No more objects to read
                 */
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return programmerMap;
    }

    public static Map<String, Programmer> getProgrammers() {
        return programmers;
    }

    public static Map<String, NonSerializableProgrammer> getNonSerialProgrammers() {
        return nonSerialProgrammers;
    }
}