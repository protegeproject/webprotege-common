package edu.stanford.protege.webprotege.common;

import com.fasterxml.jackson.annotation.*;
import com.google.auto.value.AutoValue;

import javax.annotation.Nonnull;
import java.io.Serializable;

@AutoValue
public abstract class EntityStatus implements Serializable, Comparable<EntityStatus> {

    public static final String STATUS = "status";

    @JsonCreator
    public static EntityStatus get(@Nonnull @JsonProperty(STATUS) String status) {
        return new AutoValue_EntityStatus(status);
    }


    @JsonProperty(STATUS)
    @Nonnull
    public abstract String getStatus();

    @Override
    public int compareTo(EntityStatus o) {
        return this.getStatus().compareToIgnoreCase(o.getStatus());
    }
}