package ua.com.alevel;

import java.lang.Number;
import java.lang.reflect.Array;
import java.math.BigDecimal;

public class MathSet<T extends Number> {

    private Class<T> tClass = (Class<T>) Number.class;
    private int capacity = 3;
    private int size;
    private Object[] numberArray;

    public MathSet() {
        this.capacity = 10;
        this.numberArray = new Object[this.capacity];
    }

    public MathSet(int capacity) {
        this.capacity = capacity;
        this.numberArray = new Object[this.capacity];
    }

    public MathSet(T[] numbers) {
        this.capacity = 10;
        this.numberArray = new Object[this.capacity];
        this.add(numbers);
    }

    public MathSet(T[]... numbers) {
        this.capacity = 10;
        this.numberArray = new Object[this.capacity];
        for (T[] number : numbers) {
            this.add(number);
        }
    }

    public MathSet(MathSet numbers) {
        this.capacity = 10;
        this.numberArray = new Object[this.capacity];
        this.join(numbers);
    }

    public MathSet(MathSet... numbers) {
        this.capacity = 10;
        this.numberArray = new Object[capacity];
        this.join(numbers);
    }

    public static void main(String... args) {
//        Number[] numbers = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        Number[] numbers1 = new Integer[]{50,70,0};
//        MathSet<Integer> x1 = new MathSet<>();
//        x1.add(1, 2, 3, 4, 5, 6, 7, 8, 9);
//        MathSet<Number> x = new MathSet<>(x1,x1,x1,x1);
//
//        System.out.println(x.capacity);
//        x.add(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
//
//        System.out.println(x);
//        MathSet<Integer> mathSet = new MathSet<>();
//        mathSet.add(1, 2, 100);
//        MathSet<Integer> mathSet1 = new MathSet<>();
//        mathSet1.add(1, 2, 400, 600);
//        System.out.println(x.get(17));
//        System.out.println(x.isContain(100));
//        x.join(mathSet);
//        System.out.println(x);
//        x.join(mathSet, mathSet1);
//        System.out.println(x);
//        x1.join(mathSet1);
//        x.intersection(x);
//        x.replace();
//        x.intersection(x1, mathSet);
//        System.out.println(x);
//        x.clear();
//        Integer[] numArray = {5, 9, 400};
//        x.clear(numArray);
//
//        System.out.println(x);
//        x.sortAsc();
//        System.out.println(x);
//        x.sortDesc(1);
//        System.out.println(x);
//        x.clear(x.cut(0, 2).toArray());
//        System.out.println(x.getMedian());
//        System.out.println(x.getAverage());
    }

    public void add(T n) {
        if (!isContain(n)) {
            if (size == capacity) {
                capacity = capacity * 3 / 2 + 1;
                T[] numbers1 = (T[]) Array.newInstance(tClass, capacity);
                for (int i = 0; i < numberArray.length; i++) {
                    numbers1[i] = (T) numberArray[i];
                }
                numberArray = numbers1;
            }
            numberArray[size] = n;
            size++;
        }
    }

    public void add(T... n) {
        for (int i = 0; i < n.length; i++) {
            add(n[i]);
        }
    }

    public void join(MathSet ms) {
        for (int i = 0; i < ms.size; i++) {
            add((T) ms.get(i));
        }
    }

    public void join(MathSet... ms) {
        for (int i = 0; i < ms.length; i++) {
            join(ms[i]);
        }
    }

    public void intersection(MathSet ms) {
        for (int i = 0; i < ms.size; i++) {
            if (isContain((T) ms.get(i))) {
                del((T) ms.get(i));
            }
        }
        replace();
    }

    public void intersection(MathSet... ms) {
        for (int i = 0; i < ms.length; i++) {
            intersection(ms[i]);
        }
    }

    public void sortDesc() {
        sort(false, 0, size);
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        sort(false, firstIndex, lastIndex);
    }

    public void sortDesc(T value) {
        if (isContain(value)) {
            for (int i = 0; i < size; i++) {
                if (compareTo(value, get(i)) == 0) {
                    sort(false, i, size);
                }
            }
        }
    }

    public void sortAsc() {
        sort(true, 0, size);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        sort(true, firstIndex, lastIndex);
    }

    public void sortAsc(T value) {
        if (isContain(value)) {
            for (int i = 0; i < size; i++) {
                if (compareTo(value, get(i)) == 0) {
                    sort(true, i, size);
                }
            }
        }
    }

    public T get(int index) {
        if (index <= size - 1) {
            T number = (T) numberArray[index];
            return (T) number;
        }
        return null;
    }

    public T getMax() {
        T tmp = (T) get(0);
        for (int i = 0; i < size; i++) {
            if (compareTo(tmp, get(i)) < 0) {
                tmp = (T) get(i);
            }
        }
        return tmp;
    }

    public T getMin() {
        T tmp = get(0);
        for (int i = 0; i < size; i++) {
            if (compareTo(tmp, get(i)) > 0) {
                tmp = get(i);
            }
        }
        return tmp;
    }

    public T getAverage() {
        Double num = 0.0;
        Number number;
        for (int i = 0; i < size; i++) {
            number = (Number) numberArray[i];
            num += number.doubleValue();
        }
        Number v = num / size;
        return (T) v;
    }

    public T getMedian() {
        MathSet ms;
        sortAsc();
        if (size % 2 == 1) {
            return (T) numberArray[size / 2];
        } else {
            ms = new MathSet((Number[]) toArray((size / 2) - 1, (size / 2) + 1));
            return (T) ms.getAverage();
        }
    }

    public T[] toArray() {
        return toArray(0, size);
    }

    public <T> T[] toArray(int firstIndex, int lastIndex) {
        T[] numbers = (T[]) Array.newInstance(tClass, lastIndex - firstIndex);
        for (int i = firstIndex, count = 0; i < lastIndex; i++, count++) {
            numbers[count] = (T) numberArray[i];
        }
        return numbers;
    }

    public MathSet cut(int firstIndex, int lastIndex) {
        MathSet mathSet = new MathSet();
        T[] numbers = toArray(firstIndex, lastIndex);
        for (int i = 0; i < numbers.length; i++) {
            mathSet.add((T) numbers[i]);
        }
        return mathSet;
    }

    public void clear() {
        size = 0;
    }

    public <T> void clear(T[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            del((T) numbers[i]);
            replace();
        }
    }

    private void sort(boolean actionNormal, int firstIndex, int lastIndex) {
        for (int i = firstIndex; i < lastIndex - 1; i++) {
            for (int j = lastIndex - 1; j > i; j--) {
                if (compareTo(numberArray[j - 1], numberArray[j]) > 0 && actionNormal) {
                    T tmp = (T) numberArray[j - 1];
                    numberArray[j - 1] = numberArray[j];
                    numberArray[j] = tmp;
                } else if (compareTo(numberArray[j - 1], numberArray[j]) < 0 && !actionNormal) {
                    T tmp = (T) numberArray[j - 1];
                    numberArray[j - 1] = numberArray[j];
                    numberArray[j] = tmp;
                }
            }
        }
    }

    private <T> int compareTo(T n1, T n2) {
        Number number1 = (Number) n1;
        Number number2 = (Number) n2;
        BigDecimal b1 = new BigDecimal(number1.doubleValue());
        BigDecimal b2 = new BigDecimal(number2.doubleValue());
        return b1.compareTo(b2);
    }

    private <T> boolean isContain(T n) {
        if (n == null) {
            return false;
        }
        for (int i = 0; i < numberArray.length; i++) {
            if (n.equals(numberArray[i])) {
                return true;
            }
        }
        return false;
    }

    private <T> void del(T n) {
        for (int i = 0; i < size; i++) {
            if (n.equals(numberArray[i])) {
                numberArray[i] = null;
                return;
            }
        }
    }

    private void replace() {
        for (int i = 0; i < size; i++) {
            if (numberArray[i] == null) {
                numberArray[i] = numberArray[size - 1];
                size--;
                replace();
                return;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < size; i++) {
            builder.append(numberArray[i]);
            if (i < size - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}