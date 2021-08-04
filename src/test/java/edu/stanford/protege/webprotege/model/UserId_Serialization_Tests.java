package edu.stanford.protege.webprotege.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

/**
 * Matthew Horridge
 * Stanford Center for Biomedical Informatics Research
 * 2021-07-30
 */
@SpringBootTest
@AutoConfigureJsonTesters
public class UserId_Serialization_Tests {

    public static final String ID = "CreateClasses";

    @Autowired
    private JacksonTester<UserId> tester;

    @Test
    void shouldSerializeToJson() throws IOException {
        var userId = new UserId(ID);
        var written = tester.write(userId);
        var json = written.getJson();
        Assertions.assertThat(json).isEqualTo("\"" + ID + "\"");
    }

    @Test
    void shouldDeserializeFromJson() throws IOException {
        var json = "\"" + ID + "\"";
        var parsed = tester.parse(json);
        var userId = parsed.getObject();
        Assertions.assertThat(userId.id()).isEqualTo(ID);
    }
}
