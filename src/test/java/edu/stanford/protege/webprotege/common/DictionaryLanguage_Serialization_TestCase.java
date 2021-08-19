package edu.stanford.protege.webprotege.common;

import edu.stanford.protege.webprotege.common.AnnotationAssertionDictionaryLanguage;
import edu.stanford.protege.webprotege.common.DictionaryLanguage;
import edu.stanford.protege.webprotege.common.LocalNameDictionaryLanguage;
import edu.stanford.protege.webprotege.common.OboIdDictionaryLanguage;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class DictionaryLanguage_Serialization_TestCase {

    @Autowired
    private JacksonTester<DictionaryLanguage> tester;

    @Test
    public void shouldSerializeLocalNameDictionaryLanguage() throws IOException {
        var dictionaryLanguage = LocalNameDictionaryLanguage.get();
        var json = tester.write(dictionaryLanguage);
        assertThat(json).extractingJsonPathStringValue("type").isEqualTo("LocalName");
        assertThat(json).extractingJsonPathStringValue("propertyIri").isNull();
        assertThat(json).extractingJsonPathStringValue("lang").isNull();
    }

    @Test
    public void shouldSerializeOboIdDictionaryLanguage() throws IOException {
        var dictionaryLanguage = OboIdDictionaryLanguage.get();
        var json = tester.write(dictionaryLanguage);
        assertThat(json).extractingJsonPathStringValue("type").isEqualTo("OboId");
        assertThat(json).extractingJsonPathStringValue("propertyIri").isNull();
        assertThat(json).extractingJsonPathStringValue("lang").isNull();

    }

    @Test
    public void shouldSerializeAnnotationBasedDictionaryLanguage() throws IOException {
        var dictionaryLanguage = AnnotationAssertionDictionaryLanguage.rdfsLabel("en");
        var json = tester.write(dictionaryLanguage);
        assertThat(json).extractingJsonPathStringValue("type").isEqualTo("AnnotationAssertion");
        assertThat(json).extractingJsonPathStringValue("propertyIri").isEqualTo("rdfs:label");
        assertThat(json).extractingJsonPathStringValue("lang").isEqualTo("en");

    }

    @Test
    public void shouldDeserializeLocalNameLegacySerialization() throws IOException {
        var localName = LocalNameDictionaryLanguage.get();
        var serialization = "{}";
        var parsedObjectContent = tester.parse(serialization);
        var parsedDictionaryLanguage = parsedObjectContent.getObject();
        assertThat(parsedDictionaryLanguage).isEqualTo(localName);
    }

    @Test
    public void shouldDeserializeAnnotationAssertionWithEmptyLanguageTagLegacySerialization() throws IOException {
        var iriString = "http://example.org/prop";
        var dictionaryLanguage = AnnotationAssertionDictionaryLanguage.get(iriString, "");
        var serialization = String.format("{\"propertyIri\":\"%s\"}", iriString);
        var parsedObjectContent = tester.parse(serialization);
        var parsedDictionaryLanguage = parsedObjectContent.getObject();
        assertThat(parsedDictionaryLanguage).isEqualTo(dictionaryLanguage);
    }

    @Test
    public void shouldDeserializeAnnotationAssertionWithNonEmptyLanguageTagLegacySerialization() throws IOException {
        var iriString = "http://example.org/prop";
        var dictionaryLanguage = AnnotationAssertionDictionaryLanguage.get(iriString, "en");
        var serialization = String.format("{\"propertyIri\":\"%s\", \"lang\":\"en\"}", iriString);
        var parsedObjectContent = tester.parse(serialization);
        var parsedDictionaryLanguage = parsedObjectContent.getObject();
        assertThat(parsedDictionaryLanguage).isEqualTo(dictionaryLanguage);

    }
}
