package edu.stanford.protege.webprotege.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-08-17
 *
 * Represents an object that has a channel associated with it.
 */
public interface HasChannel {

    /**
     * Gets the channel name that this type of object is published to
     *
     */
    @JsonIgnore
    String getChannel();
}
