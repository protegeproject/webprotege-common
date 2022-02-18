package edu.stanford.protege.webprotege.common;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class ChangeRequestId_Test {

    private String uuid = "abcd1234-abcd-abcd-abcd-123456789abc";

    private ChangeRequestId changeRequestId;

    @Autowired
    private JacksonTester<ChangeRequestId> tester;

    @BeforeEach
    void setUp() {
        changeRequestId = ChangeRequestId.valueOf(uuid);
    }

    @Test
    void shouldSerialize() throws IOException {
        var jsonContent = tester.write(changeRequestId);
        assertThat(jsonContent.getJson().trim()).isEqualTo("\"" + uuid + "\"");
    }

    @Test
    void shouldDeserialize() throws IOException {
        var json = """
                "abcd1234-abcd-abcd-abcd-123456789abc"
                """;
        var parsed = tester.parse(json);
        assertThat(parsed.getObject().id()).isEqualTo(uuid);

    }
}