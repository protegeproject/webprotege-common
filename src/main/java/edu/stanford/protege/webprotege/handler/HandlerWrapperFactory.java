package edu.stanford.protege.webprotege.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.stanford.protege.webprotege.cmd.Request;
import edu.stanford.protege.webprotege.cmd.Response;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-08-04
 */
public class HandlerWrapperFactory {

    private final ObjectMapper objectMapper;

    public HandlerWrapperFactory(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <Q extends Request<R>, R extends Response> HandlerWrapper<Q, R> create(CommandHandler<Q, R> commandHandler,
                                                                           Class<Q> requestClass) {
        return new HandlerWrapper<>(commandHandler, requestClass, objectMapper);
    }
}
