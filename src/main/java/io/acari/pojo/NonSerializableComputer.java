package io.acari.pojo;

public final class NonSerializableComputer {
    private String model;
    private String subModel;
    private int ram;
    private String make;

    public NonSerializableComputer(Computer computer) {
        this.model = computer.getModel();
        this.subModel = computer.getSubModel();
        this.ram = computer.getRam();
        this.make = computer.getMake();
    }


}
