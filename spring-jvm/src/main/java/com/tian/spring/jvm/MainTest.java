package com.tian.spring.jvm;

import com.google.common.collect.Lists;
import com.tian.spring.jvm.vo.PersonVo;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author tianbeiping
 * @Title: IconLabelData
 * @ProjectName mwee_paidui_soa
 * @Description: TODO
 * @date 2018/9/12 20:21
 */
public class MainTest {


    /**
     * -Xms10m -Xmx20m -XX:+PrintGC -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -XX:+PrintHeapAtGC
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

        List<PersonVo> list = Lists.newArrayList();

        int i = 0;
        while (i < Integer.MAX_VALUE) {
            i++;
            PersonVo vo = new PersonVo();
            vo.setAge(18);
            vo.setName("tian");
            vo.setSex(0);
            list.add(vo);
//            TimeUnit.MICROSECONDS.sleep(10);
        }
        System.out.println(list);

    }

}
