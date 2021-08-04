package edu.stanford.protege.webprotege.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-07-30
 */
public record UserId(@Nonnull String id) {

    public static final String GUEST_USERNAME = "guest";

    @JsonValue
    @Override
    public String id() {
        return id;
    }

    public boolean isGuest() {
        return id.equals(GUEST_USERNAME);
    }

    public static UserId getGuest() {
        return new UserId(GUEST_USERNAME);
    }

    @JsonCreator
    public static UserId valueOf(String id) {
        if(id == null) {
            return getGuest();
        }
        return new UserId(id);
    }
}
