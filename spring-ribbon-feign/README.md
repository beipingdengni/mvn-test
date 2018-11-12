## mvn-test


#### Hystrix 使用
https://www.jianshu.com/p/b9af028efebb
```java
   public class HystrixContextMainTest extends HystrixCommand<String> {
    
       private String name;
   
       public HystrixContextMainTest(String name) {
           super(Setter.withGroupKey(
                   HystrixCommandGroupKey.Factory.asKey("group-name")
           ).andCommandKey(HystrixCommandKey.Factory.asKey("command-key"))
                   .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                           .withCoreSize(100)
                   ).andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
                           .withCircuitBreakerEnabled(true)
                           .withCircuitBreakerErrorThresholdPercentage(50)
                           .withRequestCacheEnabled(true)
                           .withRequestLogEnabled(true)
                           .withExecutionTimeoutEnabled(true)
                           .withExecutionTimeoutInMilliseconds(30000) //30 秒
                   ));
           this.name = name;
       }
       // 处理逻辑
       @Override
       protected String run() throws Exception {
           System.out.println("run  Exception");
           throw new Exception("failure");
       }
       // 熔断默认返回
       @Override
       protected String getFallback() {
           System.out.println("getFallback");
           return "fallback ==========>" + System.currentTimeMillis();
       }
       // 获取缓存key
       @Override
       protected String getCacheKey() {
           System.out.println("getCacheKey : " + name);
           return this.name + ":key";
       }
   
       public static void main(String[] args) throws IOException {
           HystrixRequestContext context = HystrixRequestContext.initializeContext();
           try {
               HystrixContextMainTest test = null;
               for (int i = 0; i < 5; i++) {
                   test = new HystrixContextMainTest("test");
                   String execute = test.execute();
                   // 第一次请求，不应该从缓存中获取
                   //没有执行excute()，isResponseFromCache()永远返回是true
                   System.out.println("isResponseFromCache====>" + test.isResponseFromCache());
   
                   System.out.println(execute);
               }
               HystrixContextMainTest test1 = new HystrixContextMainTest("test");
           } finally {
               context.shutdown();
           }
       }
   }
```

