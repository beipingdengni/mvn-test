

#### BeanFactory 实现类
[BeanFactory具体分析](BeanFactory.md)

#### beanfactory 实现关键类 关键类
```java
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements ConfigurableListableBeanFactory, BeanDefinitionRegistry, Serializable {
}
```
##### 类结构继承关系
![defaultlistbeanfactory.jpg](img/defaultlistbeanfactory.jpg)
![tuopu-defaultlistbeanfactory.jpg](img/tuopu-defaultlistbeanfactory.jpg)


### spring 启动关键位置
```java
public abstract class AbstractApplicationContext extends DefaultResourceLoader
		implements ConfigurableApplicationContext {
    
        @Override
    	public void refresh() throws BeansException, IllegalStateException {
    		synchronized (this.startupShutdownMonitor) {
    			// Prepare this context for refreshing.
    			prepareRefresh();
    			
    			// Tell the subclass to refresh the internal bean factory.
    			ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();
    
    			// Prepare the bean factory for use in this context.
    			prepareBeanFactory(beanFactory);
    
    			try {
    				// Allows post-processing of the bean factory in context subclasses.
    				postProcessBeanFactory(beanFactory);
    
    				// Invoke factory processors registered as beans in the context.
    				invokeBeanFactoryPostProcessors(beanFactory);
    
    				// Register bean processors that intercept bean creation.
    				registerBeanPostProcessors(beanFactory);
    
    				// Initialize message source for this context.
    				initMessageSource();
    
    				// Initialize event multicaster for this context.
    				initApplicationEventMulticaster();
    
    				// Initialize other special beans in specific context subclasses.
    				onRefresh();
    
    				// Check for listener beans and register them.
    				registerListeners();
    
    				// Instantiate all remaining (non-lazy-init) singletons.
    				finishBeanFactoryInitialization(beanFactory);
    
    				// Last step: publish corresponding event.
    				finishRefresh();
    			}
    
    			catch (BeansException ex) {
    				if (logger.isWarnEnabled()) {
    					logger.warn("Exception encountered during context initialization - " +
    							"cancelling refresh attempt: " + ex);
    				}
    
    				// Destroy already created singletons to avoid dangling resources.
    				destroyBeans();
    
    				// Reset 'active' flag.
    				cancelRefresh(ex);
    
    				// Propagate exception to caller.
    				throw ex;
    			}
    
    			finally {
    				// Reset common introspection caches in Spring's core, since we
    				// might not ever need metadata for singleton beans anymore...
    				resetCommonCaches();
    			}
    		}
    	}
}


```


#### 类定义前，定义后  对bean 的处理
```java
public interface BeanPostProcessor {
    @Nullable
    default Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Nullable
    default Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
```

#### 注册类自定义时候，可以修改注册信息
```java
@FunctionalInterface
public interface BeanFactoryPostProcessor {
    void postProcessBeanFactory(ConfigurableListableBeanFactory var1) throws BeansException;
}
```

#### 自定注册
```java
public interface BeanDefinitionRegistryPostProcessor extends BeanFactoryPostProcessor {
    void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry var1) throws BeansException;
}
/**
# 可以扫描包注册类  可参考mybatis  org.mybatis.spring.mapper.MapperScannerConfigurer 类
public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        String basePackage = "com.tian.spring.analysis.dao";
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(registry);
        scanner.scan(StringUtils.tokenizeToStringArray(basePackage, ConfigurableApplicationContext.CONFIG_LOCATION_DELIMITERS));
    }
*/

```