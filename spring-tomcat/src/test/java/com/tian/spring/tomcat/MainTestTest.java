package com.tian.spring.tomcat;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author tianbeiping
 * @Title: MainTestTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/3下午4:33
 */
public class MainTestTest {

    @Test
    public void main() {

        String content = "您关注的%s已经可以手机取号啦，<a href='%s'>点击取号</a>。\n\n您也可以<a href='%s'>点击下载</a>美味不用等APP，寻找吃货灵感，获取美味优惠~[]您关注的%s已经开始排队啦，但餐厅目前不支持手机取号，请直接到店取号就餐，查看详情>>>";

        String[] split = content.split("\\[\\]");
        System.out.println(split.length);

        System.out.println(split[0]);
        System.out.println(split[1]);
    }
}