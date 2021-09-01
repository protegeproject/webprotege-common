package edu.stanford.protege.webprotege.common;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-08-30
 */
public interface ProjectRequest<R extends Response> extends Request<R>{

    @Nonnull
    ProjectId projectId();
}
