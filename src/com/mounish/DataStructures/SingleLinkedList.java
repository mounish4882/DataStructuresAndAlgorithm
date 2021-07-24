package com.mounish.DataStructures;

public class SingleLinkedList {

    private static ListNode head;

    private static class ListNode {
        private final long data;
        private ListNode next;

        public ListNode(long data) {
            this.data = data;
            this.next = null;
        }

    }

    public static void insert(long data) {
        ListNode new_node = new ListNode(data);
        new_node.next = null;

        if(head == null) {
            head = new_node;
        } else {
            ListNode last = head;
            while(last.next != null) {
                last = last.next;
            }
            last.next = new_node;
        }
    }

    public static void delete(long key) {
        ListNode current = head,prev = null;
        if(current != null && current.data == key) {
            head = current.next;
            System.out.println(key + " found and deleted");
            return;
        }
        while (current != null && current.data != key) {
            prev = current;
            current = current.next;
        }
        if(current != null) {
            prev.next = current.next;
            System.out.println(key + " found and deleted");
        } else {
            System.out.println(key + " not found");
        }
    }

    public static void printAll() {
        ListNode current = head;
        while(current != null){
            System.out.print(current.data + " --> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void count() {
        int count = 0;
        ListNode current = head;
        while(current != null){
            count++;
            current = current.next;
        }
        System.out.println(count);
    }

    public static void printAt(long pos) throws IndexOutOfBoundsException {
        int count = 0;
        ListNode current = head;
        while(current != null){
            count++;
            if (count == pos) System.out.println(current.data);
            current = current.next;
        }
        if(pos > count) throw new IndexOutOfBoundsException(String.valueOf(pos));
    }

}
