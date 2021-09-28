package edu.stanford.protege.webprotege.common;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 24/07/2013
 */
public enum DocumentFormatExtension {

    owl("RDF/XML"),

    ttl("Turtle"),

    owx("OWL/XML"),

    omn("Manchester OWL Syntax"),

    ofn("Functional OWL Syntax");

    private final String displayName;

    DocumentFormatExtension(String displayName) {
        this.displayName = displayName;
    }

    @JsonIgnore
    public String getDisplayName() {
        return displayName;
    }

    public String getExtension() {
        return name();
    }
}
