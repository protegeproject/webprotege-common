package edu.stanford.protege.webprotege.common;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-08-17
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public interface Event extends HasChannel {

    @Nonnull
    EventId eventId();
}
