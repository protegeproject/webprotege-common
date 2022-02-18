package edu.stanford.protege.webprotege.common;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2022-02-18
 *
 * A marker for a change request.  A change request modifies the state of a project
 */
public interface ChangeRequest {

    ChangeRequestId changeRequestId();
}
