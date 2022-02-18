package edu.stanford.protege.webprotege.common;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2022-02-18
 *
 * A ChangeRequest that modifies the content of a project
 */
public interface ContentChangeRequest extends ChangeRequest {

    /**
     * Gets the project that this ChangeRequest is associated with
     */
    @Nonnull
    ProjectId projectId();
}
