package io.acari.repositories;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LanguageRepository {
    private final List<String> LANGUAGES = Stream.of("Java", "Groovy", "Go", "Clojure", "Lisp", "Ruby",
            "Javascript", "C++", "C", "C#", "Perl", "Cobol", "R", "Matlab").collect(Collectors.toCollection(LinkedList::new));
    private final Random ranbo = new Random(9002);

    List<String> randomLanguages() {
        return LANGUAGES.stream().filter(s -> ranbo.nextBoolean()).collect(Collectors.toList());
    }

}
