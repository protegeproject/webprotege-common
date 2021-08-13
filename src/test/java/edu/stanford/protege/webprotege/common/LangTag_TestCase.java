package edu.stanford.protege.webprotege.common;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2020-04-26
 */
public class LangTag_TestCase {

    @Test
    public void shouldConvertToLowerCase() {
        LangTag langTag = LangTag.get("EN");
        assertThat(langTag.getLanguageCode(), is("en"));
    }

    @Test
    public void shouldRemoveWhitespace() {
        LangTag langTag = LangTag.get("en ");
        assertThat(langTag.getLanguageCode(), is("en"));
    }

    @Test
    public void shouldAcceptEmptyLangTag() {
        LangTag langTag = LangTag.get("");
        assertThat(langTag.getLanguageCode(), is(""));
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    public void shouldThrowNpeForNullLangTag() {
        assertThrows(NullPointerException.class, () -> {
            LangTag.get(null);
        });
    }

    @Test
    public void shouldFormatLangTag() {
        LangTag langTag = LangTag.get("en-us");
        String formatted = langTag.format();
        assertThat(formatted, is("en-US"));
    }
}
