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
        int range = to - from + 1;
        String[] tempArr = new String[range];
        int nextElementRange1 = from;
        int nextElementRange2 = mid + 1;
        int next = 0;

        while (nextElementRange1 <= mid && nextElementRange2 <= to)
        {
            if (arr[nextElementRange1].compareTo(arr[nextElementRange2]) < 0)
            {
                tempArr[next] = arr[nextElementRange1];
                nextElementRange1++;
            }
            else
            {
                tempArr[next] = arr[nextElementRange2];
                nextElementRange2++;
            }
            next++;
        }

        while (nextElementRange1 <= mid)
        {
            tempArr[next] = arr[nextElementRange1];
            nextElementRange1++;
            next++;
        }

        while (nextElementRange2 <= to)
        {
            tempArr[next] = arr[nextElementRange2];
            nextElementRange2++;
            next++;
        }

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
