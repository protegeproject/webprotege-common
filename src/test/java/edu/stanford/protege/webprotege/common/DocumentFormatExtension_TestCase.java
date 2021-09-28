package edu.stanford.protege.webprotege.common;

import org.junit.jupiter.api.BeforeEach;
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
public class DocumentFormatExtension_TestCase {

    @Autowired
    private JacksonTester<DocumentFormatExtension> tester;

    private DocumentFormatExtension extension;

    @BeforeEach
    void setUp() {
        extension = DocumentFormatExtension.owl;
    }

    @Test
    void shouldSerialize() throws IOException {
        var json = tester.write(extension);
        assertThat(json.getJson()).isEqualTo("\"owl\"");
    }

    @Test
    void shouldDeserialize() throws IOException {
        var json = """
                    "owl"
                """;
        var content = tester.parse(json);
        var object = content.getObject();
        assertThat(object).isEqualTo(DocumentFormatExtension.owl);
    }
}
