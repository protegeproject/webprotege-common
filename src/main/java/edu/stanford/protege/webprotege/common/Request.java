package edu.stanford.protege.webprotege.common;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-07-30
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
public interface Request<R extends Response> extends HasChannel {

}
