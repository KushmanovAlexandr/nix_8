package ua.com.alevel.util;

import java.util.Objects;

public class MathSet<Nums extends Number & Comparable<Nums>> {

    private int size;
    private int capacity;
    private Nums[] mathSet;

    public MathSet() {
        capacity = 10;
        mathSet = (Nums[]) new Number[capacity];
        size = 0;
    }

    public MathSet(int capacity) {
        this.mathSet = (Nums[]) new Number[capacity];
    }

    public MathSet(Nums[] numbers) {
        mathSet = removeDuplicates(numbers);
        size = numbers.length;
    }

    public MathSet(Nums[]... numbers) {
        capacity = 10;
        mathSet = (Nums[]) new Number[capacity];
        for (Nums[] x : numbers) {
            add(x);
        }
    }

    public void add(Nums n) {
        if (!contains(n)) {
            if (size == capacity) {
                Nums[] temp = (Nums[]) new Number[capacity * 2];
                System.arraycopy(mathSet, 0, temp, 0, capacity);
                capacity = capacity * 2;
                mathSet = temp;
            }
        }
        mathSet[size++] = n;
    }


    public void add(Nums... n) {
        for (Nums number : n) {
            add(number);
        }
    }


    public MathSet(MathSet numbers) {
        capacity = numbers.capacity;
        mathSet = (Nums[]) new Number[capacity];
        for (int i = 0; i < numbers.size; i++) {
            add((Nums) numbers.mathSet[i]);
        }
        size = numbers.size;
    }

    public MathSet(MathSet... numbers) {
        capacity = 10;
        mathSet = (Nums[]) new Number[capacity];
        for (MathSet<Nums> number : numbers) {
            for (int i = 0; i < number.size; i++) {
                add(number.mathSet[i]);
            }
        }
    }

    public void join(MathSet ms) {
        for (int i = 0; i < ms.size; i++) {
            add((Nums) ms.mathSet[i]);
        }
    }

    public void join(MathSet... ms) {
        for (MathSet number : ms) {
            for (int i = 0; i < number.size; i++) {
                add((Nums) number.mathSet[i]);
            }
        }
    }

    public void intersection(MathSet ms) {
        MathSet<Nums> newMathSet = new MathSet<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < ms.size; j++) {
                if (Objects.equals(this.mathSet[i], ms.mathSet[j])) {
                    newMathSet.add((Nums) ms.mathSet[j]);
                }
            }
        }
        clear();
        join(new MathSet(newMathSet));
    }

    public void intersection(MathSet... ms) {
        for (MathSet mathSet : ms) {
            intersection(mathSet);
        }
    }

    public void sortDesc() {
        sortDesc(0, size);
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        if ((lastIndex - firstIndex) > 0) {
            for (int i = firstIndex; i < lastIndex - 1; i++) {
                for (int k = i + 1; k < lastIndex; k++) {
                    if ((mathSet[i].compareTo(mathSet[k]) < 0)) {
                        Nums temp = mathSet[i];
                        mathSet[i] = mathSet[k];
                        mathSet[k] = temp;
                    }
                }
            }
        }
    }

    public void sortDesc(Nums value) {
        if (getIndex(value) != -1) {
            sortDesc(getIndex(value), size);
        }
    }


    public void sortAsc() {
        sortAsc(0, size);
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        if ((lastIndex - firstIndex) > 0) {
            for (int i = firstIndex; i < lastIndex - 1; i++) {
                for (int k = i + 1; k < lastIndex; k++) {
                    if (mathSet[i].compareTo(mathSet[k]) > 0) {
                        Nums temp = mathSet[i];
                        mathSet[i] = mathSet[k];
                        mathSet[k] = temp;
                    }
                }
            }
        }
    }

    public void sortAsc(Nums value) {
        if (getIndex(value) != -1) {
            sortAsc(getIndex(value), size);
        }
    }

    public Nums get(int index) {
        if (index < 0 || index > size) throw new IllegalArgumentException("Incorrect index");
        return mathSet[index];
    }

    public Nums getMax() {
        Nums max = mathSet[0];
        for (int i = 1; i < size; i++) {
            if (mathSet[i].compareTo(max) > 0) max = mathSet[i];
        }
        return max;
    }

    public Nums getMin() {
        Nums min = mathSet[0];
        for (int i = 1; i < size; i++) {
            if (mathSet[i].compareTo(min) < 0) min = mathSet[i];
        }
        return min;
    }

    public Number getAverage() {
        double sum = 0d;
        for (int i = 0; i < size; i++) {
            sum += mathSet[i].doubleValue();
        }
        return sum / size;
    }

    public Number getMedian() {
        if (size != 0) {
            sortAsc();
            if (size % 2 != 0) {
                return mathSet[size / 2];
            } else {
                Double first = mathSet[size / 2].doubleValue();
                Double second = mathSet[size / 2 - 1].doubleValue();
                return (first + second) / 2;
            }
        } else {
            return null;
        }
    }

    public Nums[] toArray() {
        return mathSet;
    }

    public Nums[] toArray(int firstIndex, int lastIndex) {
        Nums[] returnedArray = (Nums[]) new Number[firstIndex - lastIndex + 1];
        if (returnedArray.length >= 0) System.arraycopy(mathSet, firstIndex, returnedArray, 0, returnedArray.length);
        return returnedArray;
    }

    public MathSet cut(int firstIndex, int lastIndex) {
        MathSet<Nums> cutMathSet = new MathSet<>();
        for (int i = 0; i < capacity; i++) {
            if (i >= firstIndex && i <= lastIndex) cutMathSet.add(mathSet[i]);
        }
        return cutMathSet;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            mathSet[i] = null;
        }
        size = 0;
    }

    public void clear(Nums[] numbers) {
        for (Nums number : numbers) {
            for (int i = 0; i < size; i++) {
                if (number.equals(mathSet[i])) {
                    for (int k = i; k < size; k++) {
                        mathSet[k] = mathSet[k + 1];
                        mathSet[k + 1] = null;
                    }
                    size--;
                }
            }
        }
    }


    public Nums[] removeDuplicates(Nums[] numbers) {
        int end = numbers.length;
        for (int i = 0; i < end; i++) {
            for (int j = i + 1; j < end; j++) {
                if (numbers[i] == numbers[j]) {
                    int shiftLeft = j;
                    for (int k = j + 1; k < end; k++, shiftLeft++) {
                        numbers[shiftLeft] = numbers[k];
                    }
                    end--;
                    j--;
                }
            }
        }
        if (end != numbers.length) {
            Nums[] b = (Nums[]) new Number[end];
            System.arraycopy(numbers, 0, b, 0, end);
            numbers = b;
        }
        return numbers;
    }

    int getIndex(Nums value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (mathSet[i].equals(value)) {
                index = i;
            }
        }
        return index;
    }

    private boolean contains(Nums n) {
        boolean contained = false;
        for (int i = 0; i < capacity; i++) {
            if (n.equals(mathSet[i])) {
                contained = true;
                break;
            }
        }
        return contained;
    }

    public int size() {
        return size;
    }


}