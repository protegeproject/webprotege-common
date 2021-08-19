package edu.stanford.protege.webprotege.common;

import edu.stanford.protege.webprotege.common.DictionaryLanguage;
import edu.stanford.protege.webprotege.common.ShortForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;


import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class ShortForm_Serialization_TestCase {

    @Autowired
    private JacksonTester<ShortForm> tester;

    @Test
    public void shouldSerializeLocalNameShortForm() throws IOException {
        var shortForm = ShortForm.get(
                DictionaryLanguage.localName(),
                "Hello"
        );
        var json = tester.write(shortForm);
        assertThat(json).extractingJsonPathStringValue("dictionaryLanguage.type").isEqualTo("LocalName");
        assertThat(json).extractingJsonPathStringValue("shortForm").isEqualTo("Hello");

    }

    @Test
    public void shouldSerializeAnnotationBasedShortFormWithEmptyLangTag() throws IOException {
        var shortForm = ShortForm.get(
                DictionaryLanguage.rdfsLabel(""),
                "Hello"
        );
        var json = tester.write(shortForm);
        assertThat(json).extractingJsonPathStringValue("dictionaryLanguage.type").isEqualTo("AnnotationAssertion");
        assertThat(json).extractingJsonPathStringValue("dictionaryLanguage.propertyIri").isEqualTo("rdfs:label");
        assertThat(json).extractingJsonPathStringValue("dictionaryLanguage.langTag").isNull();
        assertThat(json).extractingJsonPathStringValue("shortForm").isEqualTo("Hello");
    }

    @Test
    public void shouldSerializeAnnotationBasedShortFormWithNonEmptyLangTag() throws IOException {
        var shortForm = ShortForm.get(
                DictionaryLanguage.rdfsLabel("en"),
                "Hello"
        );
        var json = tester.write(shortForm);
        assertThat(json).extractingJsonPathStringValue("dictionaryLanguage.type").isEqualTo("AnnotationAssertion");
        assertThat(json).extractingJsonPathStringValue("dictionaryLanguage.propertyIri").isEqualTo("rdfs:label");
        assertThat(json).extractingJsonPathStringValue("dictionaryLanguage.lang").isEqualTo("en");
        assertThat(json).extractingJsonPathStringValue("shortForm").isEqualTo("Hello");
        
    }
}
