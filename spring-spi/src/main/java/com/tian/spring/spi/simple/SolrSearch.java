package com.tian.spring.spi.simple;

/**
 * @author tianbeiping
 * @Title: SolrSearch
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/18下午6:04
 */
public class SolrSearch implements Search {

    @Override
    public String getName(String name) {

        return "solr  ==>"+name;
    }
}
