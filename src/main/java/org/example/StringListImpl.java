package org.example;

import org.example.exception.ElementNotFaund;
import org.example.exception.FullArrayException;
import org.example.exception.IndexException;
import org.example.exception.NullItemException;

import java.util.Arrays;

public class StringListImpl implements StringList {
    private Integer[] array;
    private int size;


    public StringListImpl() {
        array = new Integer[10];
    }

    public StringListImpl(int sizeArray) {
        array = new Integer[sizeArray];
    }

    @Override
    public Integer add(Integer item) {
        checkItem(item);
        checkSize();
        array[size++] = item;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        checkItem(item);
        checkSize();
        checkIndex(index);
        if (index == size) {
            array[size++] = item;
        }
        System.arraycopy(array, index, array, index = +1, size - index);
        array[index] = item;
        size++;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        checkIndex(index);
        checkItem(item);

        array[index] = item;
        return item;
    }

    @Override
    public Integer remove(Integer item) {
        checkItem(item);
        int index = indexOf(item);

        if (index == -1) {
            throw new ElementNotFaund();
        }
        if (index != size) {
            System.arraycopy(array, index + 1, array, index, size - index);
        }
        size--;
        return item;
    }


    @Override
    public boolean contains(Integer item) {
        return indexOf(item) != -1;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        checkIndex(index);
        return array[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        return Arrays.equals(this.toArray(), otherList.toArray());
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
    public void clear() {
        size = 0;
    }

    @Override
    public Integer[] toArray() {
        return Arrays.copyOf(array, size);
    }

    public void sort() {
        for (int i = size - 1; i >= 0; i--) {
            int max = Integer.MIN_VALUE;
            int idMax = 0;
            for (int j = 0; j <= i; j++) {
                if (max < get(j)) {
                    max = get(j);
                    idMax = j;
                }
            }
            int itemI = get(i);
            set(i,max);
            set(idMax,itemI);
        }
    }
    public void sortBin() {
        mergeSort(array);
    }

    public int containsBin(Integer item) {
        int index =-1;
        int low = 0;
        int high = size;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] < item) {
                low = mid + 1;
            } else if (array[mid] > item) {
                high = mid - 1;
            } else if (array[mid] == item) {
                index = mid;
                break;
            }
        }
        return index+1;
    }


    private void checkItem(Integer item) {
        if (item == null) {
            throw new NullItemException();
        }
    }
    private void grow() {
            array=Arrays.copyOf(array,size+size/2);
    }

    private void checkSize() {
        if (size == array.length) {
            grow();
        }
    }


    private void checkIndex(int index) {
        if (index < 0 || index > array.length) {
            throw new IndexException();
        }
    }

    private static void mergeSort(Integer[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        Integer[] left = new Integer[mid];
        Integer[] right = new Integer[arr.length - mid];

        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }

        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i];
        }

        mergeSort(left);
        mergeSort(right);

        merge(arr, left, right);
    }
    public static void merge(Integer[] arr, Integer[] left, Integer[] right) {

        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                arr[mainP++] = left[leftP++];
            } else {
                arr[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            arr[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            arr[mainP++] = right[rightP++];
        }
    }

}
