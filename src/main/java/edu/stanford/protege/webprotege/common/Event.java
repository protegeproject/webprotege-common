package edu.stanford.protege.webprotege.common;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-08-17
 */
public interface Event {

    /**
     * Gets the channel name that this type of event is published to
     */
    String getChannel();
}
