package com.tian.spring.common;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author tianbeiping
 * @Title: JsonMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/27下午1:12
 */
public class JsonMainTest {


    public static void main(String[] args) throws IOException {

        /**
         * // 美化输出
         * mapper.enable(SerializationFeature.INDENT_OUTPUT);
         * // 允许序列化空的POJO类
         * // （否则会抛出异常）
         * mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
         *
         * // 把java.util.Date, Calendar输出为数字（时间戳）
         * mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
         *
         * // 在遇到未知属性的时候不抛出异常
         * mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
         *
         * // 强制JSON 空字符串("")转换为null对象值:
         * mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
         *
         * // 在JSON中允许C/C++ 样式的注释(非标准，默认禁用)
         * mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
         *
         * // 允许没有引号的字段名（非标准）、反序列化是是否允许属性名称不带双引号
         * mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
         *
         * // 允许单引号（非标准） 、是否允许单引号来包住属性名称和字符串值
         * mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
         *
         * // 强制转义非ASCII字符
         * mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
         *
         * // 将内容包裹为一个JSON属性，属性名由@JsonRootName注解指定
         * mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
         *
         * //设置为false时，属性名称不带双引号,默认true
         * mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, true);
         *
         * //null的属性不序列化
         * mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
         *
         * //按字母顺序排序属性,默认false
         * mapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY,true);
         *
         * //是否以类名作为根元素，可以通过@JsonRootName来自定义根元素名称,默认false
         * objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE,true);
         *
         * //是否缩放排列输出,默认false
         * objectMapper.configure(SerializationFeature.INDENT_OUTPUT,false);
         *
         * //序列化Date日期时以timestamps输出，默认true
         * objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,true);
         *
         * //序列化枚举是否以toString()来输出，默认false，即默认以name()来输出
         * objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
         *
         * //序列化枚举是否以ordinal()来输出，默认false
         * objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX,false);
         *
         * //序列化单元素数组时不以数组来输出，默认false
         * objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING,true);
         *
         * //序列化Map时对key进行排序操作，默认false
         * objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS,true);
         *
         * //序列化char[]时以json数组输出，默认false
         * objectMapper.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS,true);
         *
         * //序列化BigDecimal时是输出原始数字还是科学计数，默认false，即以toPlainString()科学计数方式来输出
         * objectMapper.configure(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN,true);
         */
        ObjectMapper mapper = new ObjectMapper();

        PersonVo personVo = new PersonVo();
        personVo.setName("123123");
        personVo.setAddress("shang hai hui jia");


        String s = mapper.writeValueAsString(personVo);


        String json = "{\"name\":\"123123\",\"id\":\"123123\",\"address\":\"shang hai hui jia\"}";
        System.out.println(s);

        PersonVo personVo1 = mapper.readValue(json, PersonVo.class);

        System.out.println(personVo1);


    }

}
