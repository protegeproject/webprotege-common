package edu.stanford.protege.webprotege.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

public record ChangeRequestId(String id) implements ValueObject {

    public ChangeRequestId(String id) {
        this.id = Objects.requireNonNull(id);
    }

    @JsonCreator
    public static ChangeRequestId valueOf(String id) {
        return new ChangeRequestId(id);
    }

    @JsonValue
    @Override
    public String id() {
        return id;
    }

    @Override
    public String value() {
        return id;
    }
}
