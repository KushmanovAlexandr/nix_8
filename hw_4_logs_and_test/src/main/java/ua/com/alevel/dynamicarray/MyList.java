package ua.com.alevel.dynamicarray;

import java.util.Arrays;

public class MyList <T>{

    private int size = 0;

    Object[] array = new Object[1];
    public int length = array.length;

    public MyList<Integer> add(T el) {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
        array[size] = el;
        size++;
        return null;
    }

    public void remove(int index) {
        System.arraycopy(array, index + 1, array, index, array.length - 1 - index);
    }

    public T get(int index) {
        return (T) array[index];
    }

    public int getLength() {
        return array.length;
    }

    @Override
    public String toString() {
        return "MyList{" +
                "size=" + size +
                ", array=" + Arrays.toString(array) +
                ", length=" + length +
                '}';
    }
}