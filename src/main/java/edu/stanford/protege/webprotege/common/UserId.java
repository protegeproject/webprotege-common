package edu.stanford.protege.webprotege.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-07-30
 */
public record UserId(@Nonnull String id) implements ValueObject, Comparable<UserId> {

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

    @JsonIgnore
    @Override
    public String value() {
        return id;
    }

    @Override
    public int compareTo(UserId o) {
        var diff = this.id.compareToIgnoreCase(o.id);
        if(diff != 0) {
            return diff;
        }
        return this.id.compareTo(o.id);
    }
}
