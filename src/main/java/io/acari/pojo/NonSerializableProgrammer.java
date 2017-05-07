package io.acari.pojo;

import java.util.List;

public final class NonSerializableProgrammer {
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

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public NonSerializableComputer getComputer() {
        return computer;
    }

    public List<String> getLanguages() {
        return languages;
    }
}
