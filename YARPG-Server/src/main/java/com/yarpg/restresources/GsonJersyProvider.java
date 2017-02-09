package com.yarpg.restresources;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.Scanner;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;

@Provider
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GsonJersyProvider<T> implements MessageBodyWriter<Object>, MessageBodyReader<Object> {

	@Override
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// here could be a test for Annotations (GsonSerilizable)
		return true;
	}

	/**
	 * gets a json from InputStream and deserialize it back to the Object
	 */
	@Override
	public T readFrom(Class<Object> type, Type genericType, Annotation[] annotations, MediaType mediaType,
			MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
					throws IOException, WebApplicationException {
		Scanner scanner = new Scanner(entityStream).useDelimiter("\\A");
		return new Gson().<T> fromJson((scanner.hasNext()) ? scanner.next() : "", type);
	}

	@Override
	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		// here could be a test for Annotations (GsonSerilizable)
		return true;
	}

	@Override
	public long getSize(Object t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return -1;
	}
	/**
	 * gets an Object serialize it to json and write it to the InputStream
	 */
	@Override
	public void writeTo(Object toSerialize, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
					throws IOException, WebApplicationException {
		entityStream.write(getJsonStringFromObject(toSerialize).getBytes(Charset.forName("UTF-8")));
	}

	private String getJsonStringFromObject(Object t) {
		return new Gson().toJsonTree(t).getAsJsonObject().get("_value").toString();
	}
}
