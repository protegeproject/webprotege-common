package edu.stanford.protege.webprotege.handler;

import edu.stanford.protege.webprotege.cmd.Request;
import edu.stanford.protege.webprotege.cmd.Response;
import reactor.core.publisher.Mono;

import javax.annotation.Nonnull;
import java.util.concurrent.CompletableFuture;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-08-06
 */
public interface CommandHandler<Q extends Request<R>, R extends Response> {

    /**
     * Get the channel name that this handler handles requests from
     * @return The channel name
     */
    @Nonnull
    String getChannelName();

    /**
     * Gets the class of requests that this handler handles
     * @return The class of requests
     */
    Class<Q> getRequestClass();

    /**
     * Handle a request and return a response
     * @param request
     * @return
     */
    Mono<R> handleRequest(Q request);
}
