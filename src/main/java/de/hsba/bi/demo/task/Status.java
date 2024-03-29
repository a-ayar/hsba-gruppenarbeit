package de.hsba.bi.demo.task;

import lombok.Getter;

public enum Status {
    INITIAL ("initial"),
    VERÖFFENTLICHT ("veröffentlicht"),
    GESCHLOSSEN ("geschlossen");

    @Getter
    private final String name;

    private Status(String displayValue) {
        this.name = displayValue;
    }

}
