


#### jacksjon [配置信息](jackjson.md)
> 基础demo
```txt
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
