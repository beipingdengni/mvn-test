

## JackJson 具体配置描述

```txt
//这个特性，决定了解析器是否将自动关闭那些不属于parser自己的输入源。
// 如果禁止，则调用应用不得不分别去关闭那些被用来创建parser的基础输入流InputStream和reader；
//默认是true
objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
//是否允许解析使用Java/C++ 样式的注释（包括'/'+'*' 和'//' 变量）
objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);

//设置为true时，属性名称不带双引号
objectMapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, false);
//反序列化是是否允许属性名称不带双引号
objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

//是否允许单引号来包住属性名称和字符串值
objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);

//是否允许JSON字符串包含非引号控制字符（值小于32的ASCII字符，包含制表符和换行符）
objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

//是否允许JSON整数以多个0开始
objectMapper.configure(JsonParser.Feature.ALLOW_NUMERIC_LEADING_ZEROS, true);

//null的属性不序列化
objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

//按字母顺序排序属性,默认false
objectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY,true);

//是否以类名作为根元素，可以通过@JsonRootName来自定义根元素名称,默认false
objectMapper.configure(SerializationFeature.WRAP_ROOT_VALUE,true);

//是否缩放排列输出,默认false
objectMapper.configure(SerializationFeature.INDENT_OUTPUT,false);

//序列化Date日期时以timestamps输出，默认true
objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,true);

//序列化枚举是否以toString()来输出，默认false，即默认以name()来输出
objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);

//序列化枚举是否以ordinal()来输出，默认false
objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_INDEX,false);

//序列化单元素数组时不以数组来输出，默认false
objectMapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING,true);

//序列化Map时对key进行排序操作，默认false
objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS,true);

//序列化char[]时以json数组输出，默认false
objectMapper.configure(SerializationFeature.WRITE_CHAR_ARRAYS_AS_JSON_ARRAYS,true);

//序列化BigDecimal时是输出原始数字还是科学计数，默认false，即以toPlainString()科学计数方式来输出
objectMapper.configure(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN,true);

//当遇到未知属性（没有映射到属性，没有任何setter或者任何可以处理它的handler，是否应该抛出JsonMappingException异常
objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

//该特性决定对于json浮点数，是否使用BigDecimal来序列化。如果不允许，则使用Double序列化。 默认为false
objectMapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, false);

//该特性决定对于json整形（非浮点），是否使用BigInteger来序列化。如果不允许，则根据数值大小来确定 是使用Integer或者Long
objectMapper.configure(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS, false);

//该特性决定JSON ARRAY是映射为Object[]还是List<Object>。如果开启，都为Object[]，false时，则使用List  默认为false
objectMapper.configure(DeserializationFeature.USE_JAVA_ARRAY_FOR_JSON_ARRAY, false);

//是否使用Enum.toString()的值对json string进行反序列化。这个的设置和WRITE_ENUMS_USING_TO_STRING需要一致。
objectMapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);

1. @JsonIgnore：作用在字段或方法上，用来完全忽略被注解的字段和方法对应的属性。
2. @JsonProperty：作用在字段或方法上，用来对属性的序列化/反序列化，可以用来避免遗漏属性，同时提供对属性名称重命名。
	对属性添加了@JsonProperty注解后，即使该属性为private且没有getter和setter方法，也会进行序列化。
3. @JsonIgnoreProperties
　　作用在类上，用来说明有些属性在序列化/反序列化时需要忽略掉，可以将它看做是@JsonIgnore的批量操作，它还有一个重要的功能是作用在反序列化时解析字段时过滤一些未知的属性，否则通常情况下解析到我们定义的类不认识的属性便会抛出异常。
　　　　可以注明是想要忽略的属性列表如@JsonIgnoreProperties({"name","age","title"})，
　　　　也可以注明过滤掉未知的属性如@JsonIgnoreProperties(ignoreUnknown=true)
4. @JsonUnwrapped作用在属性字段或方法上，用来将子JSON对象的属性添加到封闭的JSON对象。
5. @JsonSerialize和@JsonDeserialize:作用于方法和字段上，通过 using(JsonSerializer)和using(JsonDeserializer)来指定序列化和反序列化的实现。下面的例子中自定义了日期的序列化和反序列化方式，可以将Date和指定日期格式字符串之间相互转换。
6. @JsonPropertyOrder:作用在类上，被用来指明当序列化时需要对属性做排序。@jsonPropertyOrder(alphabetic = true)
7. @JsonView:视图模板，作用于方法和属性上，用来指定哪些属性可以被包含在JSON视图中，在前面我们知道已经有@JsonIgnore和@JsonIgnoreProperties可以排除过滤掉不需要序列化的属性，可是如果一个POJO中有h很多个属性，而我们可能只需要概要简单信息即序列化时只想输出其中几个属性，此时使用@JsonIgnore和@JsonIgnoreProperties就显得非常繁琐，而使用@JsonView便会非常方便，只许在你想要输出的属性(或对应的getter)上添加@JsonView即可。
8. @JsonFilter:Json属性过滤器，作用于类，作用同上面的@JsonView，都是过滤掉不想要的属性，输出自己想要的属性。和@FilterView不同的是@JsonFilter可以动态的过滤属性。
9. @JsonAnySetter:作用于方法，在反序列化时用来处理遇到未知的属性的时候调用，在本文前面我们知道可以通过注解@JsonIgnoreProperties(ignoreUnknown=true)来过滤未知的属性，但是如果需要这些未知的属性该如何是好?那么@JsonAnySetter就可以派上用场了，它通常会和map属性配合使用用来保存未知的属性

```
