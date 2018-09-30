package com.tian.spring.jvm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Arrays;

/**
 * @author tianbeiping
 * @Title: PidMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/9/30下午1:34
 */
public class PidMainTest {


    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println(getProcessID());


        Runtime runtime = Runtime.getRuntime();

        Process exec = runtime.exec("jps -l");

        int i = exec.waitFor();

        InputStream inputStream = exec.getInputStream();


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        bufferedReader.lines().forEach(e -> {
            if (e.contains("QuorumPeerMain")) {
                String[] split = e.split(" ");
                System.out.println(String.join("---[|]---", split));
            }
        });


    }


    public static final int getProcessID() {
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println(runtimeMXBean.getName());
        return Integer.valueOf(runtimeMXBean.getName().split("@")[0])
                .intValue();
    }
}
