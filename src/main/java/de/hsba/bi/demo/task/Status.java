package de.hsba.bi.demo.task;

import lombok.Getter;

public enum Status {
    INITIAL ("initial"),
    VERÖFFENTLICHT ("veröffenticht"),
    GESCHLOSSEN ("geschlossen");

    @Getter
    private final String displayValue;

    private Status(String displayValue) {
        this.displayValue = displayValue;
    }

}
