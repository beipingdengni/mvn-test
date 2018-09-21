package com.tian.spring.spi;

/**
 * @author tianbeiping
 * @Title: JavaMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/20下午4:17
 */
public class JavaMainTest {

    public static void main(String[] args) {

        String str = new String("公众号：Java面试通关手册");
        System.out.println(str);
        change2(str);
        System.out.println(str);


        Person p = new Person("张三");

        change(p);

        System.out.println(p.hashCode());

        System.out.println(p);
    }

    public static void change(Person p) {
        Person person = new Person("李四");
        System.out.println(p.hashCode());
        p = person;
        System.out.println(person.hashCode());
        System.out.println(p);
    }

    public static void change2(String str) {
         str="abc"; //输出：公众号：Java面试通关手册
//        str = new String("abc"); //输出：公众号：Java面试通关手册
        System.out.println(str);
    }



    static class Person {
        String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

}
