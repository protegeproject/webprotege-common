package edu.stanford.protege.webprotege.common;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-09-28
 */
@JsonTest
public class DocumentFormat_TestCase {

    @Autowired
    private JacksonTester<DocumentFormat> tester;

    @Test
    void shouldSerialize() throws IOException {
        var json = tester.write(DocumentFormat.RDF_XML);
        assertThat(json.getJson()).isEqualTo("\"application/rdf+xml\"");
    }


    @Test
    void shouldDeserializeRdfXml() throws IOException {
        var json = """
                    "application/rdf+xml"
                """;
        var content = tester.parse(json);
        assertThat(content.getObject()).isEqualTo(DocumentFormat.RDF_XML);
    }

    @Test
    void shouldDeserializeTurtle() throws IOException {
        var json = """
                    "text/turtle"
                """;
        var content = tester.parse(json);
        assertThat(content.getObject()).isEqualTo(DocumentFormat.TURTLE);
    }


    @Test
    void shouldDeserializeFunctionalSyntax() throws IOException {
        var json = """
                    "text/owl-functional"
                """;
        var content = tester.parse(json);
        assertThat(content.getObject()).isEqualTo(DocumentFormat.FUNCTIONAL_SYNTAX);
    }


    @Test
    void shouldDeserializeOwlXmlSyntax() throws IOException {
        var json = """
                    "application/owl+xml"
                """;
        var content = tester.parse(json);
        assertThat(content.getObject()).isEqualTo(DocumentFormat.OWL_XML);
    }

    @Test
    void shouldDeserializeManchesterSyntax() throws IOException {
        var json = """
                    "text/owl-manchester"
                """;
        var content = tester.parse(json);
        assertThat(content.getObject()).isEqualTo(DocumentFormat.MANCHESTER_SYNTAX);
    }
}
