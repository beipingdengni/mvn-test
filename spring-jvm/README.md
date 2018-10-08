## mvn-test

```xml
    <!--配置 spring  线程池-->
    <bean id="taskExecutor" class="org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor">
        <property name="corePoolSize" value="8"/> <!--核心线程数 -->
        <property name="maxPoolSize" value="16"/> <!--最大线程数 -->
        <property name="keepAliveSeconds" value="3000"/> <!--线程最大空闲时间 -->
        <property name="queueCapacity" value="200"/> <!-- 队列大小 -->
        <property name="threadNamePrefix" value="TASK_EXECUTOR"/>
        <property name="rejectedExecutionHandler">
            <bean class="java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy"/>
        </property>
    </bean>

```

#### 动态代理接口 无实现类使用
```java
    public interface Person {
        void say(String ok);
    }
    
    public class MainTest{
        public static void main(String[] args) {
            Person person = newProxy(Person.class);
            
                    person.say("hello world");
        }
    }
    
```
#### 代理类实现
```java
    public static class FacadeProxy implements InvocationHandler {

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("接口方法调用开始");
            //执行方法
            System.out.println("method toGenericString:" + method.toGenericString());
            System.out.println("method name:" + method.getName());
            System.out.println("method args:" + (String) args[0]);
            Object invoke = method.invoke(args);
            System.out.println("接口方法调用结束");
            return invoke;
        }
        
        public static <T> T newProxy(Class<T> mapperInterface) {
                ClassLoader classLoader = mapperInterface.getClassLoader();
                Class<?>[] classes = new Class[]{mapperInterface};
                FacadeProxy facadeProxy = new FacadeProxy();
                return (T) Proxy.newProxyInstance(classLoader, classes, facadeProxy);
            }
    }

```

#### LocalDateTime LocalDate LocalTime 
```java
public class MainTest {
    public static void main(String[] args) {
        String strDate="yyyy-mm-dd HH:mm:ss.SSS";
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern(strDate);
        // 当前时间
        LocalDateTime now = LocalDateTime.now();
        // 正常输出
        String format = now.format(dateTimeFormatter);
        System.out.println(format);
        // 减一天
        String format1 = now.minusDays(1).format(dateTimeFormatter);
        // 加一天
        String format2 = now.plusDays(1).format(dateTimeFormatter);
        // 加减一天
        String format3 = now.plus(1,ChronoUnit.DAYS).format(dateTimeFormatter);
        String format4 = now.minus(1,ChronoUnit.DAYS).format(dateTimeFormatter);
    }
}
```

