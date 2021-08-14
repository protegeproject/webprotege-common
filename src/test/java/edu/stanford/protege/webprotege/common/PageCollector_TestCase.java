package edu.stanford.protege.webprotege.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.stream.Stream;

import static edu.stanford.protege.webprotege.common.PageCollector.toPageNumber;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 17 Jun 2017
 */
@SuppressWarnings("OptionalGetWithoutIsPresent")
public class PageCollector_TestCase {

    private Stream<Integer> stream;

    @BeforeEach
    public void setUp() {
        stream = Stream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    @Test
    public void shouldCollectPage() {
        Optional<Page<Integer>> pg = stream.collect(toPageNumber(3).forPageSize(2));
        assertThat(pg.isPresent(), is(true));
        Page<Integer> page = pg.get();
        assertThat(page.getPageCount(), is(5));
        assertThat(page.getPageElements(), is(asList(4, 5)));
        assertThat(page.getTotalElements(), is(10L));
        assertThat(page.getPageNumber(), is(3));
    }

    @Test
    public void shouldCollectFirstPage() {
        Optional<Page<Integer>> pg = stream.collect(toPageNumber(1).withDefaultPageSize());
        assertThat(pg.isPresent(), is(true));
        Page<Integer> page = pg.get();
        assertThat(page.getPageCount(), is(1));
        assertThat(page.getPageElements(), is(asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));
        assertThat(page.getTotalElements(), is(10L));
        assertThat(page.getPageNumber(), is(1));
    }

    @Test
    public void shouldCollectLastPage() {
        Optional<Page<Integer>> pg = stream.collect(toPageNumber(10).forPageSize(1));
        assertThat(pg.isPresent(), is(true));
        Page<Integer> page = pg.get();
        assertThat(page.getPageCount(), is(10));
        assertThat(page.getPageElements(), is(singletonList(9)));
        assertThat(page.getTotalElements(), is(10L));
        assertThat(page.getPageNumber(), is(10));
    }

    @Test
    public void shouldReturnEmptyForPageNumberOutOfRange() {
        Optional<Page<Integer>> page = stream.collect(toPageNumber(2).withDefaultPageSize());
        assertThat(page.isPresent(), is(false));
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForPageNumberLessThanOne() {
        assertThrows(IllegalArgumentException.class, () -> {
            stream.collect(toPageNumber(0).withDefaultPageSize());
        });
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionForPageSizeLessThanOne() {
        assertThrows(IllegalArgumentException.class, () -> {
            stream.collect(toPageNumber(1).forPageSize(0));
        });
    }
}
