package com.tian.spring.jvm.link;

/**
 * @author tianbeiping
 * @Title: LinkedMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/10/8上午11:16
 */
public class LinkedMainTest {

    public class Node {
        public Node next;
        public Object val;

        public Node(Object val) {
            this.val = val;
        }

        public Node(Node next, Object val) {
            this.next = next;
            this.val = val;
        }
    }

    private Node fist;
    private Node last;


    public boolean add(Object val) {
        Node node = new Node(val);

        if (fist == null) {
            fist = node;
        } else {
            last.next = node;
        }
        last = node;
        return true;
    }

    public boolean remove(Object val) {

        return true;
    }


    public void print() {
        if (fist == null) {
            System.out.println("null");
        } else {
            System.out.println(fist.val);
            Node val = fist.next;
            while (val != null) {
                System.out.println(val.val);
                val = val.next;
            }
        }
    }


    public static void main(String[] args) {

        LinkedMainTest linked = new LinkedMainTest();
        linked.add("kais");
        linked.add(1231231);
        linked.add("q2e212");
        linked.print();
        System.out.println("============");

    }


}
