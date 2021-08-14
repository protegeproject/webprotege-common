package edu.stanford.protege.webprotege.common;

import edu.stanford.protege.webprotege.common.Page;
import edu.stanford.protege.webprotege.common.Pager;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 08/10/2013
 */
public class PagerTestCase {

    @Test
    public void getPagerForPageSizeWithNullSourceDataThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            Pager.getPagerForPageSize(null, 1);
        });
    }

    @Test
    public void getPagerForPageSizeWithZeroPageSizeThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Pager.getPagerForPageSize(Collections.<String>emptyList(), 0);
        });
    }

    @Test
    public void getPagerForPageSizeWithNegativePageSizeThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            Pager.getPagerForPageSize(Collections.<String>emptyList(), -1);
        });
    }

    @Test
    public void getPageCountReturnsOneForEmptySourceData() {
        final int pageSize = 10;
        Pager<String> pager = Pager.getPagerForPageSize(Collections.emptyList(), pageSize);
        assertEquals(1, pager.getPageCount());
    }

    @Test
    public void getPageOfEmptyListReturnsFirstPageWhichIsEmpty() {
        final int pageSize = 10;
        Pager<String> pager = Pager.getPagerForPageSize(Collections.emptyList(), pageSize);
        Page<String> page = pager.getPage(1);
        assertNotNull(page);
        assertEquals(1, page.getPageNumber());
        assertEquals(1, page.getPageCount());
        assertEquals(0, page.getPageElements().size());
    }

    @Test
    public void getPageThrowsIndexOutOfBoundsExceptionForZeroPageNumber() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            final int pageSize = 10;
            Pager<String> pager = Pager.getPagerForPageSize(Collections.emptyList(), pageSize);
            pager.getPage(0);
        });
    }

    @Test
    public void getPageThrowsIndexOutOfBoundsExceptionForNegativePageNumber() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            final int pageSize = 10;
            Pager<String> pager = Pager.getPagerForPageSize(Collections.emptyList(), pageSize);
            pager.getPage(-1);
        });
    }

    @Test
    public void getPageThrowsIndexOutOfBoundsExceptionForEmptySourceData() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            final int pageSize = 10;
            Pager<String> pager = Pager.getPagerForPageSize(Collections.emptyList(), pageSize);
            pager.getPage(2);
        });
    }

    @Test
    public void getPagerReturnsCorrectPagesForNonEmptyData() {
        List<String> data = Arrays.asList("A", "B", "C", "D", "E");
        final int pageSize = 2;
        Pager<String> pager = Pager.getPagerForPageSize(data, pageSize);
        assertEquals(3, pager.getPageCount());
        Page<String> page1 = pager.getPage(1);
        assertEquals(Arrays.asList("A", "B"), page1.getPageElements());
        Page<String> page2 = pager.getPage(2);
        assertEquals(Arrays.asList("C", "D"), page2.getPageElements());
        Page<String> page3 = pager.getPage(3);
        assertEquals(Collections.singletonList("E"), page3.getPageElements());
    }
}
