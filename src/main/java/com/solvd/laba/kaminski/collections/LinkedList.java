package com.solvd.laba.kaminski.collections;

import com.solvd.laba.kaminski.exception.ValidationException;

import java.util.*;

public class LinkedList<T> implements List<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size;


    @Override
    public void clear(){
        head = null;
        tail = null;
        size = 0;
    }



    @Override
    public int indexOf(Object t) {
        Node<T> temp = head;
        int counter = 0;
        do{
            if(t.equals(temp.data)){
                return counter;
            }
            temp = temp.next;
            counter++;
        }while (temp != null);
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<T> temp = tail;
        int counter = 1;
        do{
            if(o.equals(temp.data)){
                return size-counter;
            }
            temp = temp.previous;
            counter++;
        }while (temp != null);
        return -1;
    }

    @Override
    public ListIterator<T> listIterator() {
        List<T> temporaryList = new ArrayList<>();
        Node<T> temp = head;
        do{
            temporaryList.add(temp.data);
            temp = temp.next;
        }while (temp != null);
        return temporaryList.listIterator();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        List<T> temporaryList = new ArrayList<>();
        Node<T> temp = head;
        int counter = 0;
        do{
            if(counter >= index) {
                temporaryList.add(temp.data);
            }
            temp = temp.next;
        }while (temp != null);
        return temporaryList.listIterator(index);
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if(toIndex < 0 || toIndex >= size){
            throw new IndexOutOfBoundsException("toIndex out of bounds");
        }
        if(fromIndex < 0 || fromIndex > size){
            throw new IndexOutOfBoundsException("fromIndex out of bounds");
        }
        if(fromIndex > toIndex){
            throw new ValidationException("fromIndex cannot be larger than toIndex");
        }
        List<T> subList = new ArrayList<>();
        Node<T> temp = head;
        int counter = 0;
        do{
            if(counter >= fromIndex && counter <= toIndex){
                subList.add(temp.data);
            }
            temp = temp.next;
            counter++;
        }while (temp.next != null);
        return subList;
    }

    @Override
    public T get(int index) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node<T> temp = head;
        for(int i = 0; i < index; i++){
            temp = temp.next;
        }
        return temp.data;
    }

    @Override
    public T set(int index, T element) {
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node<T> temp = head;
        for(int i = 0; i < index; i++){
            temp = temp.next;
        }
        temp.data = element;
        return temp.data;
    }

    @Override
    public void add(int index, T element) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        Node<T> newNode = new Node<>(element);
        if(index == 0){
            Node<T> temp = head;
            newNode.next = temp;
            head = newNode;
            temp.previous = newNode;
        } else if(index == size-1) {
            Node<T> temp = tail;
            newNode.previous = temp;
            tail = newNode;
            temp.next = newNode;
        } else{
            Node<T> temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            newNode.next = temp.next;
            newNode.previous = temp;
            temp.next = newNode;
            newNode.next.previous = newNode;
        }
        size++;

    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            Node<T> temp = head;
            head = temp.next;
            temp.next.previous = null;
            temp.next = null;
        } else if (index == size - 1) {
            Node<T> temp = tail;
            tail = temp.previous;
            temp.previous.next = null;
            temp.previous = null;
        } else {
            Node<T> temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            temp.previous.next = temp.next;
            temp.next.previous = temp.previous;
            temp.next = null;
            temp.previous = null;

            size--;
            return temp.data;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    @Override
    public boolean contains(Object o) {
        Node<T> temp = head;
        do{
            if(o.equals(temp.data)){
                return true;
            }
            temp = temp.next;
        }while(temp.next != null);
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        List<T> temporaryList = new ArrayList<>();
        Node<T> temp = head;
        do{
            temporaryList.add(temp.data);
        }while(temp.next != null);

        return temporaryList.iterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int counter = 0;
        Node<T> temp = head;
        do{
            array[counter] = temp.data;
            counter++;
            temp = temp.next;
        }while(temp.next != null);
        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        int counter = 0;
        Node<T> temp = head;
        do {
            a[counter] = (T1)temp.data;
            counter++;
        }while (temp.next != null);
        return a;
    }

    @Override
    public boolean add(T t) {
        Node<T> temp = new Node<T>(t);
        if(tail == null){
            tail = temp;
            head = temp;
        }else {
            tail.next = temp;
            temp.previous = tail;
            tail = temp;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<T> temp = head;
        do{
            if(o.equals(temp.data)){
                temp.previous.next = temp.next;
                temp.next.previous = temp.previous;
                temp.next = null;
                temp.previous = null;
                size--;
                return true;
            }
        }while(temp.next != null);
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for(Object o : c){
            if(!contains(o)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Node temp = tail;
        for(T t : c){
            Node<T> newNode = new Node<>(t);
            temp.next = newNode;
            newNode.previous = temp;
            temp = newNode;
            size++;
            tail=newNode;
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if(index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        if(index == size){
            addAll(c);
        }
        Node<T> temp = head;
        for(int i = 0; i < index; i++){
            temp = temp.next;
        }
        for(T t : c){
            Node<T> newNode = new Node<>(t);
            newNode.next = temp.next;
            newNode.previous = temp;
            temp.next = newNode;
            temp = newNode;
            size++;
        }
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Node<T> temp = head;
        boolean changed = false;
        do{
            if(c.contains(temp.data)){
                if(temp.previous == null){
                    head = temp.next;
                }
                if(temp.next == null){
                    tail = temp.previous;
                }
                if(temp.previous != null) {
                    temp.previous.next = temp.next;
                }
                temp.next.previous = temp.previous;
                temp.next = null;
                temp.previous = null;
                size--;
                changed = true;
            }
            temp = temp.next;
        }while(temp.next != null);
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Node<T> temp = head;
        boolean changed = false;
        do{
            if(!c.contains(temp.data)){
                if(temp.previous == null){
                    head = temp.next;
                }
                if(temp.next == null){
                    tail = temp.previous;
                }
                if(temp.previous != null) {
                    temp.previous.next = temp.next;
                }
                temp.next.previous = temp.previous;
                temp.next = null;
                temp.previous = null;
                size--;
                changed = true;
            }
            temp = temp.next;
        }while(temp.next != null);
        return changed;
    }
}
