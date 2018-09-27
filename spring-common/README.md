


#### jacksjon [配置信息](jackjson.md)
> 基础demo
```text
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonVo {
    private String name;
    private String address;
}

ObjectMapper mapper = new ObjectMapper();
PersonVo personVo = new PersonVo();
personVo.setName("123123");
personVo.setAddress("shang hai hui jia");
// 序列化
String s = mapper.writeValueAsString(personVo);
// 反序列化，忽略不知道字段
String json = "{\"name\":\"123123\",\"id\":\"123123\",\"address\":\"shang hai hui jia\"}";
PersonVo personVo1 = mapper.readValue(json, PersonVo.class);
```
#### fastjson 
```text
SerializerFeature有用的一些枚举值
QuoteFieldNames———-输出key时是否使用双引号,默认为true 
WriteMapNullValue——–是否输出值为null的字段,默认为false 
WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null 
WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null 
WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null 
WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
```
