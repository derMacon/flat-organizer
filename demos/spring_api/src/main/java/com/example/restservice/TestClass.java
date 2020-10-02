package com.example.restservice;

public class TestClass {
    private String name;

    public TestClass() {}

    public TestClass(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "input='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }
}
