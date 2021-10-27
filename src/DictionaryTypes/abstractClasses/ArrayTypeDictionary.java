package DictionaryTypes.abstractClasses;

import DictionaryTypes.ArrayDictionary;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class ArrayTypeDictionary extends Dictionary
{

    public void insertionSort(String[] arr, int finalIndexForSort)
    {
        int index = 0;
        if (finalIndexForSort < arr.length)
        {
            index = finalIndexForSort;
        }
        else if (finalIndexForSort >= arr.length)
        {
            index = arr.length - 1;
        }
        String tempString = "";
        for (int i = 0; i < index; i++)
        {
            for (int j = i + 1; j < index + 1; j++)
            {
                if (arr[i].compareToIgnoreCase(arr[j]) > 0)
                {
                    tempString = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tempString;
                }
            }
        }
    }

    public void mergeSort(String[] arr, int finalIndexForSort)
    {
        int index = finalIndexForSort;
        if (finalIndexForSort >= arr.length)
        {
            index = arr.length - 1;
        }
        mergeSortUtil(arr, 0, index);
    }

    private void mergeSortUtil(String[] arr, int start, int end)
    {
        if (start == end)
        {
            return;
        }
        int mid = (start + end) / 2;
        // sort the first and the second half
        mergeSortUtil(arr, start, mid);
        mergeSortUtil(arr, mid + 1, end);
        merge(arr, start, mid, end);
    }

    private void merge(String[] a, int from, int mid, int to)
    {
        int range = to - from + 1;       // size of the range to be merged
        String[] tempArr = new String[range];   // merge both halves into a temporary array tempArr
        int nextElementR1 = from;               // next element to consider in the first range
        int nextElementR2 = mid + 1;            // next element to consider in the second range
        int next = 0;                   // next open position in b

        //as long as neither nextElementR1 nor nextElementR2 past the end, move the smaller into tempArr
        while (nextElementR1 <= mid && nextElementR2 <= to)
        {
            if (a[nextElementR1].compareTo(a[nextElementR2]) < 0)
            {
                tempArr[next] = a[nextElementR1];
                nextElementR1++;
            }
            else
            {
                tempArr[next] = a[nextElementR2];
                nextElementR2++;
            }
            next++;
        }
        // note that only one of the two while loops below is executed
        // copy any remaining entries of the first half
        while (nextElementR1 <= mid)
        {
            tempArr[next] = a[nextElementR1];
            nextElementR1++;
            next++;
        }
        // copy any remaining entries of the second half
        while (nextElementR2 <= to)
        {
            tempArr[next] = a[nextElementR2];
            nextElementR2++;
            next++;
        }
        // copy back from the temporary array
        for (next = 0; next < range; next++)
        {
            a[from + next] = tempArr[next];
        }
    }

    public void hybridSort(String[] arr, int size, int finalIndexForSort)
    {
        if (finalIndexForSort <= finalIndexForSort)
        {

        }
    }
}
