package se.iths.entity;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomListDeserializer extends StdDeserializer<List<Subject>> {

        public CustomListDeserializer() {
            this(null);
        }

        public CustomListDeserializer(Class<?> vc) {
            super(vc);
        }

        @Override
        public List<Subject> deserialize(
                JsonParser jsonparser,
                DeserializationContext context)
                throws IOException, JsonProcessingException {

            return new ArrayList<>();
        }
    }

