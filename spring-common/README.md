


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

#### 堆排序 参考博客:[堆排序](http://www.cnblogs.com/jingmoxukong/p/4303826.html)
```text
堆是一棵顺序存储的完全二叉树。

其中每个结点的关键字都不大于其孩子结点的关键字，这样的堆称为小根堆。

其中每个结点的关键字都不小于其孩子结点的关键字，这样的堆称为大根堆。

举例来说，对于n个元素的序列{R0, R1, ... , Rn}当且仅当满足下列关系之一时，称之为堆：

(1) Ri <= R2i+1 且 Ri <= R2i+2 (小根堆)

(2) Ri >= R2i+1 且 Ri >= R2i+2 (大根堆)

其中i=1,2,…,n/2向下取整; 

```
