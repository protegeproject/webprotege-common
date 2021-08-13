package edu.stanford.protege.webprotege.common;

import com.fasterxml.jackson.annotation.*;
import org.semanticweb.owlapi.vocab.SKOSVocabulary;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static edu.stanford.protege.webprotege.common.AnnotationAssertionDictionaryLanguage.LANG;
import static edu.stanford.protege.webprotege.common.AnnotationAssertionDictionaryLanguage.PROPERTY_IRI;
import static org.semanticweb.owlapi.vocab.OWLRDFVocabulary.RDFS_LABEL;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 5 Apr 2018
 */

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type", defaultImpl = DictionaryLanguage.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = AnnotationAssertionDictionaryLanguage.class, name = AnnotationAssertionDictionaryLanguage.TYPE_NAME),
        @JsonSubTypes.Type(value = AnnotationAssertionPathDictionaryLanguage.class, name = AnnotationAssertionPathDictionaryLanguage.TYPE_NAME),
        @JsonSubTypes.Type(value = LocalNameDictionaryLanguage.class, name = LocalNameDictionaryLanguage.TYPE_NAME),
        @JsonSubTypes.Type(value = OboIdDictionaryLanguage.class, name = OboIdDictionaryLanguage.TYPE_NAME),
        @JsonSubTypes.Type(value = PrefixedNameDictionaryLanguage.class, name = PrefixedNameDictionaryLanguage.TYPE_NAME)
})
public abstract class DictionaryLanguage {

    /**
     * Legacy serialization factory method.
     *
     * @param annotationPropertyIri The annotation property
     * @param lang                  The language.  May be empty.  This will be normalised to a lower case string
     */
    @JsonCreator
    @Nonnull
    private static DictionaryLanguage createFromJson(@Nullable @JsonProperty(PROPERTY_IRI) String annotationPropertyIri,
                                                     @Nullable @JsonProperty(LANG) String lang) {

        if(annotationPropertyIri == null) {
            return LocalNameDictionaryLanguage.get();
        }
        else {
            return AnnotationAssertionDictionaryLanguage.get(annotationPropertyIri, lang);
        }
    }

    @Nonnull
    public static DictionaryLanguage rdfsLabel(@Nonnull String lang) {
        return AnnotationAssertionDictionaryLanguage.get(RDFS_LABEL.getIRI(), lang);
    }

    @Nonnull
    public static DictionaryLanguage skosPrefLabel(@Nonnull String lang) {
        return AnnotationAssertionDictionaryLanguage.get(SKOSVocabulary.PREFLABEL.getIRI().toString(),
                                                         lang);
    }

    @Nonnull
    public static DictionaryLanguage localName() {
        return LocalNameDictionaryLanguage.get();
    }

    @Nonnull
    public static DictionaryLanguage prefixedName() {
        return PrefixedNameDictionaryLanguage.get();
    }

    @Nonnull
    public static DictionaryLanguage oboId() {
        return OboIdDictionaryLanguage.get();
    }

    @JsonIgnore
    public abstract boolean isAnnotationBased();

    public static String lowerCaseLangTag(String langTag) {
        for(int i = 0; i < langTag.length(); i++) {
            char ch = langTag.charAt(i);
            if((ch < 'a' || ch > 'z') && ch != '-') {
                return langTag.toLowerCase();
            }
        }
        return langTag;
    }

    public abstract <R> R accept(@Nonnull DictionaryLanguageVisitor<R> visitor);

    /**
     * Return the language tag for this dictionary language
     * @return The language tag.  For certain languages this will always be the empty string
     */
    @Nonnull
    public abstract String getLang();
}
