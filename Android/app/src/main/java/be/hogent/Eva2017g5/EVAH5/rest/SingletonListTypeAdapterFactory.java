package be.hogent.Eva2017g5.EVAH5.rest;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

/**
 * Created by sofie
 */

//Adapter to always deserialize to a list of objects if the content is either:
//- an (empty) Array
//- an Object
public class SingletonListTypeAdapterFactory implements TypeAdapterFactory {
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {

        Type type = typeToken.getType();
        if (typeToken.getRawType() != List.class
                || !(type instanceof ParameterizedType)) {
            return null;
        }
        Type elementType = ((ParameterizedType) type).getActualTypeArguments()[0];
        TypeAdapter<?> elementAdapter = gson.getAdapter(TypeToken.get(elementType));
        TypeAdapter<T> arrayAdapter = gson.getDelegateAdapter(this, typeToken);
        return (TypeAdapter<T>) newSingletonListAdapter((TypeAdapter<Object>) elementAdapter, (TypeAdapter<List<Object>>) arrayAdapter);
    }

    private <E> TypeAdapter<List<E>> newSingletonListAdapter(
            final TypeAdapter<E> elementAdapter,
            final TypeAdapter<List<E>> arrayAdapter) {
        return new TypeAdapter<List<E>>() {

            public void write(JsonWriter out, List<E> value) throws IOException {
                if(value == null || value.isEmpty()) {
                    out.nullValue();
                } else if(value.size() == 1) {
                    elementAdapter.write(out, value.get(0));
                } else {
                    arrayAdapter.write(out, value);
                }
            }

            public List<E> read(JsonReader in) throws IOException {
                if (in.peek() != JsonToken.BEGIN_ARRAY) {
                    E obj = elementAdapter.read(in);
                    return Collections.singletonList(obj);
                }
                return arrayAdapter.read(in);
            }
        };
    }
}