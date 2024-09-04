package com.solvd.laba.kaminski.collections;


public class Node<T>{

        public T data;
        public Node<T> previous;
        public Node<T> next;

        public Node(T data){
            this.data = data;
        }

}
