package io.acari.pojo;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

public class NonSerializableProgrammer {
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
}
