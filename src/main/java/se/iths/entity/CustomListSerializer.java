package se.iths.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.sun.mail.imap.protocol.Item;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomListSerializer extends StdSerializer<List<Subject>> {

    public CustomListSerializer() {
        this(null);
    }

public CustomListSerializer(Class<List<Subject>> t) {
        super(t);
    }

    @Override
    public void serialize(
            List<Subject> subjects,
            JsonGenerator generator,
            SerializerProvider provider)
            throws IOException, JsonProcessingException {

        List<Long> ids = new ArrayList<>();
        for (Subject subject : subjects) {
            ids.add(subject.getId());
        }
        generator.writeObject(ids);
    }

}