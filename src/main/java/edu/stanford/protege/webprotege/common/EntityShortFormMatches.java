package edu.stanford.protege.webprotege.common;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableList;

import org.semanticweb.owlapi.model.OWLEntity;

import javax.annotation.Nonnull;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2020-07-13
 */
@AutoValue
public abstract class EntityShortFormMatches {

    public static EntityShortFormMatches get(@Nonnull OWLEntity entity,
                                             @Nonnull ImmutableList<ShortFormMatch> shortFormMatches) {
        checkShortFormMatchEntities(entity, shortFormMatches);
        return new AutoValue_EntityShortFormMatches(entity, shortFormMatches);
    }

    private static void checkShortFormMatchEntities(@Nonnull OWLEntity entity,
                                                   @Nonnull ImmutableList<ShortFormMatch> shortFormMatches) {
        for(var shortFormMatch : shortFormMatches) {
            if(!shortFormMatch.getEntity().equals(entity)) {
                throw new IllegalArgumentException(String.format("Short form term (%s) does not match main term (%s)",
                                                                 shortFormMatch.getEntity(),
                                                                 entity));
            }
        }
    }

    /**
     * Get the term whose short forms was matched
     */
    @Nonnull
    public abstract OWLEntity getEntity();

    /**
     * Gets the short form matches for the term
     */
    @Nonnull
    public abstract ImmutableList<ShortFormMatch> getShortFormMatches();
}
