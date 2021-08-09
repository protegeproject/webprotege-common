package edu.stanford.protege.webprotege.common;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-07-30
 */
public interface Request<R extends Response> {

    String getChannel();
}
