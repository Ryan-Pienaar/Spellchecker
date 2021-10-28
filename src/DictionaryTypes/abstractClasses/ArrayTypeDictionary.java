package DictionaryTypes.abstractClasses;
import DictionaryTypes.ArrayDictionary;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class ArrayTypeDictionary extends Dictionary
{

    /**
     * Uses the insertion sorting method to sort words in an array type dictionary alphabetically.
     * @param arr Input array of the dictionary to be sorted.
     * @param finalIndexForSort Index value where insertion sort should be used up until.
     */
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

    /**
     * Uses the merge sorting method to sort an array type dictionary by splitting the array
     * into smaller segments and then sorting the words alphabetically
     * @param arr Input array of the dictionary to be sorted.
     * @param finalIndexForSort Index value where insertion sort should be used up until.
     */
    public void mergeSort(String[] arr, int finalIndexForSort)
    {
        int index = finalIndexForSort;
        if (finalIndexForSort >= arr.length)
        {
            index = arr.length - 1;
        }
        mergeSortUtil(arr, 0, index);
    }

    /**
     * This is a utility method for the mergeSort method.
     * @param arr String array input.
     * @param start Start index of the array to be sorted.
     * @param end End index of the array to be sorted.
     */
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

    /**
     * Merges all the sub arrays into the final sorted array.
     * @param arr String array input.
     * @param from Start index value of the array to be merged.
     * @param mid Midpoint index value of the array to be sorted.
     * @param to End index value of the array to merged.
     */
    private void merge(String[] arr, int from, int mid, int to)
    {
        int range = to - from + 1;       // size of the range to be merged
        String[] tempArr = new String[range];   // merge both halves into a temporary array tempArr
        int nextElementR1 = from;               // next element to consider in the first range
        int nextElementR2 = mid + 1;            // next element to consider in the second range
        int next = 0;                   // next open position in b

        //as long as neither nextElementR1 nor nextElementR2 past the end, move the smaller into tempArr
        while (nextElementR1 <= mid && nextElementR2 <= to)
        {
            if (arr[nextElementR1].compareTo(arr[nextElementR2]) < 0)
            {
                tempArr[next] = arr[nextElementR1];
                nextElementR1++;
            }
            else
            {
                tempArr[next] = arr[nextElementR2];
                nextElementR2++;
            }
            next++;
        }
        // note that only one of the two while loops below is executed
        // copy any remaining entries of the first half
        while (nextElementR1 <= mid)
        {
            tempArr[next] = arr[nextElementR1];
            nextElementR1++;
            next++;
        }
        // copy any remaining entries of the second half
        while (nextElementR2 <= to)
        {
            tempArr[next] = arr[nextElementR2];
            nextElementR2++;
            next++;
        }
        // copy back from the temporary array
        for (next = 0; next < range; next++)
        {
            arr[from + next] = tempArr[next];
        }
    }

    /**
     * Uses a combination of mergeSort and insertionSort depending of the size input value.
     * @param arr The array to be sorted.
     * @param size Size of the subarrays the array needs to be split into.
     * @param finalIndexForSort The index value of where the array will be sorted up until.
     */
    public void hybridSort(String[] arr, int size, int finalIndexForSort)
    {
        int index = finalIndexForSort;
        if (finalIndexForSort >= arr.length)
        {
            index = arr.length - 1;
        }

        if (size <= arr.length)
        {
            mergeSort(arr, index);
        }
        else if (size > arr.length)
        {
            insertionSort(arr, arr.length);
        }

    }
}
