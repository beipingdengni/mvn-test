

#### BeanFactory 实现类
```java
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {
    
     protected <T> T doGetBean(String name, @Nullable Class<T> requiredType, @Nullable Object[] args, boolean typeCheckOnly) throws BeansException {
          String beanName = this.transformedBeanName(name);
          Object sharedInstance = this.getSingleton(beanName);
          Object bean;
          if (sharedInstance != null && args == null) {
              if (this.logger.isDebugEnabled()) {
                  if (this.isSingletonCurrentlyInCreation(beanName)) {
                      this.logger.debug("Returning eagerly cached instance of singleton bean '" + beanName + "' that is not fully initialized yet - a consequence of a circular reference");
                  } else {
                      this.logger.debug("Returning cached instance of singleton bean '" + beanName + "'");
                  }
              }
  
              bean = this.getObjectForBeanInstance(sharedInstance, name, beanName, (RootBeanDefinition)null);
          } else {
              if (this.isPrototypeCurrentlyInCreation(beanName)) {
                  throw new BeanCurrentlyInCreationException(beanName);
              }
  
              BeanFactory parentBeanFactory = this.getParentBeanFactory();
              if (parentBeanFactory != null && !this.containsBeanDefinition(beanName)) {
                  String nameToLookup = this.originalBeanName(name);
                  if (parentBeanFactory instanceof AbstractBeanFactory) {
                      return ((AbstractBeanFactory)parentBeanFactory).doGetBean(nameToLookup, requiredType, args, typeCheckOnly);
                  }
  
                  if (args != null) {
                      return parentBeanFactory.getBean(nameToLookup, args);
                  }
  
                  return parentBeanFactory.getBean(nameToLookup, requiredType);
              }
  
              if (!typeCheckOnly) {
                  this.markBeanAsCreated(beanName);
              }
  
              try {
                  RootBeanDefinition mbd = this.getMergedLocalBeanDefinition(beanName);
                  this.checkMergedBeanDefinition(mbd, beanName, args);
                  String[] dependsOn = mbd.getDependsOn();
                  String[] var11;
                  if (dependsOn != null) {
                      var11 = dependsOn;
                      int var12 = dependsOn.length;
  
                      for(int var13 = 0; var13 < var12; ++var13) {
                          String dep = var11[var13];
                          if (this.isDependent(beanName, dep)) {
                              throw new BeanCreationException(mbd.getResourceDescription(), beanName, "Circular depends-on relationship between '" + beanName + "' and '" + dep + "'");
                          }
  
                          this.registerDependentBean(dep, beanName);
  
                          try {
                              this.getBean(dep);
                          } catch (NoSuchBeanDefinitionException var24) {
                              throw new BeanCreationException(mbd.getResourceDescription(), beanName, "'" + beanName + "' depends on missing bean '" + dep + "'", var24);
                          }
                      }
                  }
                  /**
                  * 处理单例  核心关键在  this.createBean(beanName, mbd, args);
                  */
                  if (mbd.isSingleton()) {
                      // 返回 bean 和 注入 bean map中
                      sharedInstance = this.getSingleton(beanName, () -> {
                          try {
                              //  创建bean
                              return this.createBean(beanName, mbd, args);
                          } catch (BeansException var5) {
                              this.destroySingleton(beanName);
                              throw var5;
                          }
                      });
                      bean = this.getObjectForBeanInstance(sharedInstance, name, beanName, mbd);
                  } else if (mbd.isPrototype()) {
                      var11 = null;
  
                      Object prototypeInstance;
                      try {
                          this.beforePrototypeCreation(beanName);
                          prototypeInstance = this.createBean(beanName, mbd, args);
                      } finally {
                          this.afterPrototypeCreation(beanName);
                      }
  
                      bean = this.getObjectForBeanInstance(prototypeInstance, name, beanName, mbd);
                  } else {
                      String scopeName = mbd.getScope();
                      Scope scope = (Scope)this.scopes.get(scopeName);
                      if (scope == null) {
                          throw new IllegalStateException("No Scope registered for scope name '" + scopeName + "'");
                      }
  
                      try {
                          Object scopedInstance = scope.get(beanName, () -> {
                              this.beforePrototypeCreation(beanName);
  
                              Object var4;
                              try {
                                  var4 = this.createBean(beanName, mbd, args);
                              } finally {
                                  this.afterPrototypeCreation(beanName);
                              }
  
                              return var4;
                          });
                          bean = this.getObjectForBeanInstance(scopedInstance, name, beanName, mbd);
                      } catch (IllegalStateException var23) {
                          throw new BeanCreationException(beanName, "Scope '" + scopeName + "' is not active for the current thread; consider defining a scoped proxy for this bean if you intend to refer to it from a singleton", var23);
                      }
                  }
              } catch (BeansException var26) {
                  this.cleanupAfterBeanCreationFailure(beanName);
                  throw var26;
              }
          }
  
          if (requiredType != null && !requiredType.isInstance(bean)) {
              try {
                  T convertedBean = this.getTypeConverter().convertIfNecessary(bean, requiredType);
                  if (convertedBean == null) {
                      throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
                  } else {
                      return convertedBean;
                  }
              } catch (TypeMismatchException var25) {
                  if (this.logger.isDebugEnabled()) {
                      this.logger.debug("Failed to convert bean '" + name + "' to required type '" + ClassUtils.getQualifiedName(requiredType) + "'", var25);
                  }
  
                  throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
              }
          } else {
              return bean;
          }
     }
}

```

