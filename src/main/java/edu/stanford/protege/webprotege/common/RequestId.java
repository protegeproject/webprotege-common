package edu.stanford.protege.webprotege.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.annotation.Nonnull;
import java.util.UUID;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2022-01-26
 */
public record RequestId(String id) implements ValueObject {

    @JsonCreator
    @Nonnull
    public static RequestId valueOf(String id) {
        return new RequestId(UUID.fromString(id).toString());
    }

    /**
     * Creates a fresh project id that has a randonly generated UUID
     */
    @Nonnull
    public static RequestId generate() {
        return valueOf(UUID.randomUUID().toString());
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
