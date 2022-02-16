package edu.stanford.protege.webprotege.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.UUID;

/**
 * An EventId which is a wrapper around the string representation of a UUID
 */
public record EventId(String id) {

    public EventId(@Nonnull String id) {
        this.id = Objects.requireNonNull(id);
    }

    /**
     * Create an EventId from a string representation of a UUID
     * @param id The UUID
     * @return The Event Id
     */
    @Nonnull
    @JsonCreator
    public static EventId valueOf(@Nonnull @JsonProperty("id") String id) {
        return new EventId(id);
    }

    /**
     * Generate a random EventId
     * @return The generated event id
     */
    @Nonnull
    public static EventId generate() {
        return valueOf(UUID.randomUUID().toString());
    }
}
