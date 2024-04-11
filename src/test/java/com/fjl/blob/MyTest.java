package com.fjl.blob;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

//@SpringBootTest
public class MyTest {
    class MyPair<T, V> implements Comparable<MyPair<T, V>> {
        private T first;
        private V second;
        public MyPair() {
        }

        public MyPair(T first, V second) {
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public void setFirst(T first) {
            this.first = first;
        }

        public V getSecond() {
            return second;
        }

        public void setSecond(V second) {
            this.second = second;
        }

        @Override
        public int compareTo(@NotNull MyPair<T, V> o) {
            if ((int) this.getFirst() == (int) o.getFirst()) {
                return 0;
            }
            return (int) this.getFirst() > (int) o.getFirst() ? 1 : -1;
        }

        @Override
        public String toString() {
            return "MyPair{" +
                    "first=" + first +
                    ", second=" + second +
                    '}';
        }
    }

    @Test
    public void acm() {
        PriorityQueue<MyPair<Integer, Integer>> pq = new PriorityQueue<>();
        pq.add(new MyPair<>(3, 4));
        pq.add(new MyPair<>(1, 4));
        pq.add(new MyPair<>(5, 4));
        System.out.println(pq.peek());
        ArrayList<MyPair<Integer, Integer>> al = new ArrayList<>();
        al.add(new MyPair<>(3, 4));
        al.add(new MyPair<>(1, 4));
        al.add(new MyPair<>(5, 4));
        Collections.sort(al);
        System.out.println(al);
    }
}
