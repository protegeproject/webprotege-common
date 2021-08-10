package edu.stanford.protege.webprotege.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.annotation.Nonnull;
import java.util.UUID;

public record ProjectId(@Nonnull String id) implements ValueObject {

    @JsonCreator
    @Nonnull
    public static ProjectId valueOf(String id) {
        return new ProjectId(UUID.fromString(id).toString());
    }

    /**
     * Creates a fresh project id that has a randonly generated UUID
     */
    @Nonnull
    public static ProjectId generate() {
        return valueOf(UUID.randomUUID().toString());
    }

    public static ProjectId getNil() {
        return valueOf("00000000-0000-0000-0000-000000000000");
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
