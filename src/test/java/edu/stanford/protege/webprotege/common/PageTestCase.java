package edu.stanford.protege.webprotege.common;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 13/09/2013
 */
public class PageTestCase {

    @Test
    public void constructorThrowsIllegalArgumentExceptionIfPageNumberIsGreaterThanPageCount() {
        assertThrows(IllegalArgumentException.class, () -> {
            Page.create(2, 1, Collections.emptyList(), 100);
        });
    }

    @Test
    public void constructorThrowsIndexOfOutBoundsExceptionForPageNumberOfZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            Page.create(0, 1, Collections.emptyList(), 100);
        });
    }

    @Test
    public void constructorThrowsIllegalArgumentExceptionForPageCountOfZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            Page.create(1, 0, Collections.emptyList(), 100);
        });
    }

    @Test
    public void constructorThrowsNullPointerExceptionForNullElements() {
        assertThrows(NullPointerException.class, () -> {
            Page.create(1, 1, null, 100);
        });
    }


    @Test
    public void getPageNumberReturnsSuppliedPageNumber() {
        Page<String> p = Page.create(2, 2, Collections.emptyList(), 100);
        assertEquals(2, p.getPageNumber());
    }
}
