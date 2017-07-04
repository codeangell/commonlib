package common.lib;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2015/11/18
 * Time: 14:29
 * Introduction:json解析
 */
public class JsonParser {
    private JsonParser() {
    }

    private static ObjectMapper objectMapper = new ObjectMapper();


    static {
        //序列化配置
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS, true);
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {

            @Override
            public void serialize(
                    Object value,
                    JsonGenerator jg,
                    SerializerProvider sp) throws IOException {
                jg.writeString("");
            }
        });
        //反序列化配置
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
    }


    /**
     * 把JSON数据转换成单个的javabean
     *
     * @param jsonString
     * @param beanClazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T getSingleBean(String jsonString, Class<T> beanClazz) throws Exception {
        return objectMapper.readValue(jsonString, beanClazz);
    }

    /**
     * 把JSON数据转换成单个的javabean
     *
     * @param jsonString
     * @param beanClazz
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T getBean(String jsonString, Class<T> beanClazz) {
        try {
            return objectMapper.readValue(jsonString, beanClazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 把JSON数据转换成一个列表
     *
     * @param jsonString
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> List<T> getListBean(String jsonString, Class<T> beanClazz) throws Exception {
        return objectMapper.readValue(jsonString, getCollectionType(ArrayList.class, beanClazz));
    }

    /**
     * 把JSON数据转换成一个数组
     *
     * @param jsonString
     * @param obj
     * @return
     * @throws Exception
     */
    public static Object[] getArrayBean(String jsonString, Object[] obj) throws Exception {
        return objectMapper.readValue(jsonString, obj.getClass());
    }

    /**
     * 把一个Javabean或javabeand列表转换成一段JSON字符串
     *
     * @param obj
     * @return
     * @throws Exception
     */
    public static String toJsonString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}

//object JsonParser {
//
//    private val objectMapper = ObjectMapper()
//
//
//    init {
//        //序列化配置
//        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
//        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
////        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        objectMapper.serializerProvider.setNullValueSerializer(object : JsonSerializer<Any>() {
//            @Throws(IOException::class)
//            override fun serialize(
//                    value: Any,
//                    jg: JsonGenerator,
//                    sp: SerializerProvider) {
//                jg.writeString("")
//            }
//        })
//        //反序列化配置
//        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        objectMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
//        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
//    }
//
//
//    /**
//     * 把JSON数据转换成单个的javabean
//     *
//     * @param jsonString
//     * @param beanClazz
//     * @param <T>
//     * @return
//     * @throws Exception
//     */
//    @Throws(IOException::class, JsonParseException::class, JsonMappingException::class)
//    fun <T> getSingleBean(jsonString: String, beanClazz: Class<T>): T = objectMapper.readValue(jsonString, beanClazz)
//
//
//    /**
//     * 把JSON数据转换成一个列表
//     *
//     * @param jsonString
//     * @param <T>
//     * @return
//     * @throws Exception
//     */
//    @Throws(IOException::class, JsonParseException::class, JsonMappingException::class)
//    fun <T> getListBean(jsonString: String, beanClazz: Class<T>): List<T> = objectMapper.readValue(jsonString, getCollectionType(ArrayList::class.java, beanClazz))
//
//
//    /**
//     * 把一个Javabean或javabeand列表转换成一段JSON字符串
//
//     * @param obj
//     * *
//     * @return
//     * *
//     * @throws Exception
//     */
//    fun toJsonString(obj: Any): String? {
//        try {
//            return objectMapper.writeValueAsString(obj)
//        } catch (e: JsonProcessingException) {
//            e.printStackTrace()
//            return null
//        }
//
//    }
//
//
//    private fun getCollectionType(collectionClass: Class<*>, vararg elementClasses: Class<*>): JavaType {
//        return objectMapper.typeFactory.constructParametricType(collectionClass, *elementClasses)
//    }
//}
