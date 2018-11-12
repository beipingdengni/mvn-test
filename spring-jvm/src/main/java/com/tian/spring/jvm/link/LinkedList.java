package com.tian.spring.jvm.link;

/**
 * @author tianbeiping
 * @Title: LinkedMainTest
 * @ProjectName mvn-test
 * @Description:
 * @date 18/10/8上午11:16
 */
public class LinkedList {

    public class Node {
        public Node next;
        public Object val = null;

        public Node(Object val) {
            this.val = val;
        }

        public Node(Node next, Object val) {
            this.next = next;
            this.val = val;
        }
    }

    private Node first;

    public void insertFirst(Object obj) {
        Node node = new Node(obj);
        node.next = first;
        first = node;
    }

    public Object deleteFirst() {
        if (first == null) {
            System.out.println("isEmpty");
            return null;
        }
        Node cur = first;
        first = cur.next;
        return cur.val;
    }


    public Object find(Object val) {
        if (first == null) {
            System.out.println("isEmpty");
            return null;
        }
        Node cur = first;

        while (cur != null) {
            if (cur.val.equals(val)) {
                return cur.val;
            }
            cur = cur.next;
        }
        return null;
    }


    public void remove(Object val) {
        if (first == null) {
            System.out.println("isEmpty");
            return;
        }
        if (first.val.equals(val)) {
            first = first.next;
        } else {
            Node pre = first;
            Node cur = first.next;
            while (cur != null) {
                if (cur.val.equals(val)) {
                    pre.next = cur.next;
                }
                pre = cur;
                cur = cur.next;
            }
        }

    }

    public void display() {
        if (first == null) {
            System.out.println("isEmpty");
            return;
        }
        Node cur = first;
        while (cur != null) {
            System.out.print(cur.val.toString() + " -> ");
            cur = cur.next;
        }
        System.out.print("\n");
    }


    public static void main(String[] args) {

        LinkedList linked = new LinkedList();

        linked.insertFirst(1);
        linked.insertFirst(3);
        linked.insertFirst(13);
        linked.insertFirst(19);
        linked.display();
        System.out.println("============");
        linked.remove(13);
        linked.display();
        System.out.println("============");
        linked.deleteFirst();
        linked.display();
        System.out.println("============");
        System.out.println(linked.find(1));


    }


}
