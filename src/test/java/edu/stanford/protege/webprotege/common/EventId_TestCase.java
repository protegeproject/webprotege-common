package edu.stanford.protege.webprotege.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2022-02-15
 */
@JsonTest
public class EventId_TestCase {

    private String uuid = "abcd1234-abcd-abcd-abcd-123456789abc";

    private EventId eventId;

    @Autowired
    private JacksonTester<EventId> tester;

    @BeforeEach
    void setUp() {
        eventId = EventId.valueOf(uuid);
    }

    @Test
    void shouldSerialize() throws IOException {
        var jsonContent = tester.write(eventId);
        assertThat(jsonContent).hasJsonPathStringValue("id", uuid);
    }

    @Test
    void shouldDeserialize() throws IOException {
        var json = """
                {
                    "id" : "abcd1234-abcd-abcd-abcd-123456789abc"
                }
                """;
        var parsed = tester.parse(json);
        assertThat(parsed.getObject().id()).isEqualTo(uuid);

    }
}
