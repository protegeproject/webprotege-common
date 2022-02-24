package edu.stanford.protege.webprotege.common;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2022-02-23
 */
public interface ContentChangeRequestEvent extends ProjectEvent {

    /**
     * Get the {@link ChangeRequestId} of the {@link ContentChangeRequest} that
     * initiated the content change that caused this event to be generated.
     */
    ChangeRequestId changeRequestId();
}
