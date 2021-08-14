package edu.stanford.protege.webprotege.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: Matthew Horridge<br>
 * Stanford University<br>
 * Bio-Medical Informatics Research Group<br>
 * Date: 08/10/2013
 */
public class PageRequestTestCase {

    @Test
    public void requestSinglePageDoesNotReturnNull() {
        assertNotNull(PageRequest.requestSinglePage());
    }

    @Test
    public void requestSinglePageReturnsRequestWithPageNumberOfOneAndMaxSize() {
        PageRequest request = PageRequest.requestSinglePage();
        assertTrue(1 == request.getPageNumber());
        assertTrue(PageRequest.MAX_PAGE_SIZE == request.getPageSize());
    }

    @Test
    public void requestFirstPageDoesNotReturnNull() {
        assertNotNull(PageRequest.requestFirstPage());
    }

    @Test
    public void getFirstPageReturnsRequestWithAPageNumberOfOneAndDefaultPageSize() {
        PageRequest request = PageRequest.requestFirstPage();
        assertTrue(1 == request.getPageNumber());
        assertTrue(PageRequest.DEFAULT_PAGE_SIZE == request.getPageSize());
    }

    @Test
    public void requestPageDoesNotReturnNull() {
        assertNotNull(PageRequest.requestPage(2));
    }

    @Test
    public void requestPageWithSizeDoesNotReturnNull() {
        assertNotNull(PageRequest.requestPageWithSize(2, 10));
    }

    @Test
    public void requestPageReturnsRequestWithSpecifiedPageNumberAndDefaultSize() {
        PageRequest request = PageRequest.requestPage(2);
        assertTrue(2 == request.getPageNumber());
        assertTrue(PageRequest.DEFAULT_PAGE_SIZE == request.getPageSize());
    }

    @Test
    public void requestPageWithSizeReturnsRequestWithSpecifiedPageAndSize() {
        PageRequest request = PageRequest.requestPageWithSize(2, 10);
        assertTrue(2 == request.getPageNumber());
        assertTrue(10 == request.getPageSize());
    }

    @Test
    public void requestPageWithPageNumberOfZeroThrowsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            PageRequest.requestPage(0);
        });
    }

    @Test
    public void requestPageWithNegativePageNumberThrowsIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            PageRequest.requestPage(-1);
        });
    }

    @Test
    public void requestPageWithZeroPageSizeThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            PageRequest.requestPageWithSize(1, 0);
        });
    }

    @Test
    public void requestPageWithNegativePageSizeThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> {
            PageRequest.requestPageWithSize(1, -1);
        });
    }

    @Test
    public void equalPageNumberAndSizeAreEqualRequestsWithTheSameHashCode() {
        PageRequest requestA = PageRequest.requestPageWithSize(3, 10);
        PageRequest requestB = PageRequest.requestPageWithSize(3, 10);
        assertEquals(requestA, requestB);
        assertEquals(requestA.hashCode(), requestB.hashCode());
    }

    @Test
    public void shouldSkipNothingForFirstPage() {
        PageRequest request = PageRequest.requestFirstPage();
        assertEquals(request.getSkip(), 0);
    }

    @Test
    public void shouldSkipOverPages() {
        PageRequest request = PageRequest.requestPageWithSize(3, 10);
        assertEquals(request.getSkip(), 20);
    }
}
