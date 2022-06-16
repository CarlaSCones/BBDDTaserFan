package server.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ResponseTransformer;


public class JsonTransformer<T> implements ResponseTransformer {

    private Gson gson = new GsonBuilder().setDateFormat("YYYY-MM-DD").create();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

    public T getObjet(String json, Class<T> tClass) {
        return gson.fromJson(json,tClass);
    }
}
