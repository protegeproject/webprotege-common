package edu.stanford.protege.webprotege.handler;

import edu.stanford.protege.webprotege.cmd.Request;
import edu.stanford.protege.webprotege.cmd.Response;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-08-04
 */
public interface CommandHandler<Q extends Request<R>, R extends Response> {

    R handleRequest(Q request);
}
