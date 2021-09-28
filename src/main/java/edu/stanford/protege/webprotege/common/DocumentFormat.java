package edu.stanford.protege.webprotege.common;

import com.fasterxml.jackson.annotation.JsonValue;
import org.semanticweb.owlapi.formats.*;
import org.semanticweb.owlapi.model.OWLDocumentFormat;

import java.util.function.Supplier;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 24/07/2013
 */
public enum DocumentFormat {

    RDF_XML(RDFXMLDocumentFormat::new, "application/rdf+xml", DocumentFormatExtension.owl),

    TURTLE(TurtleDocumentFormat::new, "text/turtle", DocumentFormatExtension.ttl),

    OWL_XML(OWLXMLDocumentFormat::new, "application/owl+xml", DocumentFormatExtension.owx),

    MANCHESTER_SYNTAX(ManchesterSyntaxDocumentFormat::new, "text/owl-manchester", DocumentFormatExtension.omn),

    FUNCTIONAL_SYNTAX(FunctionalSyntaxDocumentFormat::new, "text/owl-functional", DocumentFormatExtension.ofn);


    private final Supplier<OWLDocumentFormat> documentFormatSupplier;

    private final String mimeType;

    private final DocumentFormatExtension extension;

    DocumentFormat(Supplier<OWLDocumentFormat> documentFormatSupplier, String mimeType, DocumentFormatExtension extension) {
        this.documentFormatSupplier = documentFormatSupplier;
        this.mimeType = mimeType;
        this.extension = extension;
    }


    public String getParameterValue() {
        return extension.getExtension();
    }

    public OWLDocumentFormat getDocumentFormat() {
        return documentFormatSupplier.get();
    }

    @JsonValue
    public String getMimeType() {
        return mimeType;
    }

    public String getExtension() {
        return extension.getExtension();
    }

    /**
     * Gets the default format.
     * @return The default format.  Not {@code null}.
     */
    public static DocumentFormat getDefaultFormat() {
        return RDF_XML;
    }
}
