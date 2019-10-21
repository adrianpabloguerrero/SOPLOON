package isistan.soploon.services.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Consumes({MediaType.APPLICATION_JSON, "text/json", "application/*+json"})
@Produces({MediaType.APPLICATION_JSON, "text/json", "application/*+json"})
public class GsonProvider implements MessageBodyReader<Object>, MessageBodyWriter<Object> {

	private final String UTF8 = "UTF-8";

	private static final Gson GSON = new GsonBuilder()
    		//.registerTypeHierarchyAdapter(Calendar.class, (JsonDeserializer<Calendar>) (json, typeOfT, context) -> { Calendar out = Calendar.getInstance(); out.setTimeInMillis(json.getAsJsonPrimitive().getAsLong()); return out; } )
    	    //.registerTypeHierarchyAdapter(Calendar.class, (JsonSerializer<Calendar>) (date, type, jsonSerializationContext) -> { return new JsonPrimitive(date.getTimeInMillis()); })   
    		.setLongSerializationPolicy(LongSerializationPolicy.STRING)
    		.create();

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
			throws IOException, WebApplicationException {

		InputStreamReader streamReader = new InputStreamReader(entityStream, UTF8);

		try {
			Type jsonType;
			if (type.equals(genericType)) {
				jsonType = type;
			} else {
				jsonType = genericType;
			}

			return GSON.fromJson(streamReader, jsonType);
		} finally {
			streamReader.close();
		}
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	@Override
	public long getSize(Object o, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	@Override
	public void writeTo(Object o, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
			throws IOException, WebApplicationException {

		OutputStreamWriter writer = new OutputStreamWriter(entityStream, UTF8);

		try {
			Type jsonType;
			if (type.equals(genericType)) {
				jsonType = type;
			} else {
				jsonType = genericType;
			}

			GSON.toJson(o, jsonType, writer);
		} finally {
			writer.close();
		}
	}
}