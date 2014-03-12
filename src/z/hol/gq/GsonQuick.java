package z.hol.gq;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

/**
 * Gson Quick 工具, 方便序列化对象, 对象列表
 * @author holmes
 *
 */
public final class GsonQuick {
    
    /**
     * Json解析器
     */
    private static final JsonParser PARSER = new JsonParser();
    
    /**
     * Gson 单例
     */
    private static Gson sGson = null;
    
    /**
     * 设置一个通用的Gson
     * @param gson
     */
    public static void setGson(Gson gson){
        sGson = gson;
    }
    
    /**
     * 获取通用GSON
     * @return
     */
    public static Gson getGson(){
        if (sGson == null){
            GsonBuilder builder = new GsonBuilder();
            builder.serializeNulls();
            builder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY);
            Gson gson = builder.create();
            sGson = gson;
        }
        return sGson;
    }

    /**
     * 获取Json对象里面的键, 并将其值以String返回.
     * 当值为json对象或者json数组时,返回其json格式的字符串
     * @param json
     * @param key
     * @return
     */
    public static String getString(String json, String key) {
        JsonElement element = PARSER.parse(json);
        if (element.isJsonObject()){
            JsonObject obj = element.getAsJsonObject();
            JsonElement value = obj.get(key);
            if (value != null){
                if (value instanceof JsonPrimitive){
                    return value.getAsString();
                }else if (value instanceof JsonNull){
                    return null;
                }else{
                    return value.toString();
                }
            }
        }
        return null;
    }
    
    /**
     * 将json解析为一个JsonObject
     * @param json
     * @return
     */
    public static JsonObject toJsonObject(String json){
        JsonElement element = PARSER.parse(json);
        return element.getAsJsonObject();
    }
    
    /**
     * 将json解析为一个JsonArray
     * @param json
     * @return
     */
    public static JsonArray toJsonArray(String json){
        JsonElement element = PARSER.parse(json);
        return element.getAsJsonArray();
    }
    
    /**
     * 将json对象序列化为一个对象实例
     * @param json
     * @param clss 要序列化为的对象
     * @return
     */
    public static <T> T toObject(String json, Class<T> clss){
        Gson gson = getGson();
        return gson.fromJson(json, clss);
    }
    
    /**
     * 将json数组序列化为一个对象的List实例
     * @param json
     * @param clss 对象类型
     * @return
     */
    public static <T> List<T> toList(String json, Class<T> clss){
        JsonElement element = PARSER.parse(json);
        if (element.isJsonArray()){
            Gson gson = getGson();
            JsonArray array = element.getAsJsonArray();
            int size = array.size();
            ArrayList<T> result = new ArrayList<T>(size);
            for (int i = 0; i < size; i ++){
                JsonElement item = array.get(i);
                T t = gson.fromJson(item, clss);
                result.add(t);
            }
            return result;
        }
        return null;
    }
    
}
