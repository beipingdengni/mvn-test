package com.tian.spring.spi;

import com.tian.spring.spi.simple.Search;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * @author tianbeiping
 * @Title: MainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/18下午6:02
 */
@Slf4j
public class MainTest {

    public static void main(String[] args) {

        ServiceLoader<Search> load = ServiceLoader.load(Search.class);
        Iterator<Search> iterator = load.iterator();

        log.info(Search.class.getName());
        log.info(Search.class.getSimpleName());
        log.info(Search.class.getTypeName());

        while (iterator.hasNext()) {
            Search next = iterator.next();
            String result = next.getName("开始");
            log.info(result);
        }

        log.info("开心的笑===》");
    }
}
