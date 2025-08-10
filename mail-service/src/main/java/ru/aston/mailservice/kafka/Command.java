package ru.aston.mailservice.kafka;

enum Command {
    CREATE,
    DELETE;

    public boolean is(String name) {
        return this.name().equals(name);
    }
}
