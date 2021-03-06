package edu.stanford.protege.webprotege.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.google.auto.value.AutoValue;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2020-08-05
 */

@AutoValue
@JsonTypeName(OboIdDictionaryLanguage.TYPE_NAME)
public abstract class OboIdDictionaryLanguage extends DictionaryLanguage {

    public static final String TYPE_NAME = "OboId";

    @JsonCreator
    public static OboIdDictionaryLanguage get() {
        return new AutoValue_OboIdDictionaryLanguage();
    }

    @Override
    public boolean isAnnotationBased() {
        return false;
    }

    @Override
    public <R> R accept(@Nonnull DictionaryLanguageVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Nonnull
    @Override
    public String getLang() {
        return "";
    }
}
