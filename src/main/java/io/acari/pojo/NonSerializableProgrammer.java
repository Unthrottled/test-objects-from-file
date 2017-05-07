package io.acari.pojo;

import java.util.List;

public final class NonSerializableProgrammer {
    public static final int NULL_LIST = -1;
    private String name;
    private int age;
    private NonSerializableComputer computer;
    private List<String> languages;

    public NonSerializableProgrammer(Programmer programmer) {
        this.name = programmer.getName();
        this.age = programmer.getAge();
        this.computer = new NonSerializableComputer(programmer.getComputer());
        this.languages = programmer.getLanguages();
    }

    @Override
    public String toString() {
        return "NonSerializableProgrammer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", computer=" + computer +
                ", languages=" + languages +
                '}';
    }
}
