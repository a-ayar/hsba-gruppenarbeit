package de.hsba.bi.demo.task;

import lombok.Getter;

public enum Evaluation {
    UNBENOTET ("Unbenoted"),
    SEHRGUT ("Sehr gut"),
    GUT("Gut"),
    DURCHSCHNITTLICH ("Durchschnittlich"),
    AUSREICHEND ("Ausreichend"),
    MANGELHAFT ("Mangelhaft"),
    UNGENÜGEND("Ungenügend");

    @Getter
    private final String name;

    Evaluation(String name) {
        this.name = name;
    }
}

