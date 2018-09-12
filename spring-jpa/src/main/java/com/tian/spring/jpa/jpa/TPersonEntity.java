package com.tian.spring.jpa.jpa;

import javax.persistence.*;

/**
 * @author tianbeiping
 * @Title: TPersonEntity
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/10下午4:01
 */
@Entity
@Table(name = "t_person", schema = "tian", catalog = "")
public class TPersonEntity {
    private int id;
    private String name;
    private Integer age;
    private String pwd;

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "pwd")
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "TPersonEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}