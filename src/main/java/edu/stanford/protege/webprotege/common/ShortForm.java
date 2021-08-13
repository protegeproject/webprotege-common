package edu.stanford.protege.webprotege.common;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@AutoValue

public abstract class ShortForm {

    public static final String DICTIONARY_LANGUAGE = "dictionaryLanguage";

    public static final String SHORT_FORM = "shortForm";

    @Nonnull
    public static ShortForm get(@Nonnull @JsonProperty(DICTIONARY_LANGUAGE) DictionaryLanguage language,
                                @Nonnull @JsonProperty(SHORT_FORM) String shortForm) {
        return new AutoValue_ShortForm(language, shortForm);
    }

    @JsonCreator
    @Nonnull
    protected static ShortForm getFromJson(@Nullable @JsonProperty(DICTIONARY_LANGUAGE) DictionaryLanguage language,
                                @Nonnull @JsonProperty(SHORT_FORM) String shortForm) {
        return new AutoValue_ShortForm(language == null ? LocalNameDictionaryLanguage.get() : language, shortForm);
    }

    @JsonProperty(DICTIONARY_LANGUAGE)
    @Nonnull
    public abstract DictionaryLanguage getDictionaryLanguage();

    @Nonnull
    @JsonProperty(SHORT_FORM)
    public abstract String getShortForm();
}
