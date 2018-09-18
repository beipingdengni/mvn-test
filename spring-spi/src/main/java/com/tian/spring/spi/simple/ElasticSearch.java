package com.tian.spring.spi.simple;

/**
 * @author tianbeiping
 * @Title: ElasticSearch
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/18下午6:05
 */
public class ElasticSearch implements Search {
    @Override
    public String getName(String name) {

        return "ElasticSearch  ==>" + name;
    }
}
