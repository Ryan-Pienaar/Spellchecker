package DictionaryTypes.abstractClasses;

public abstract class ArrayTypeDictionary extends Dictionary {

    /* ARRAY SPECIFIC METHODS handin2*/
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

    //public void mergeSort(String[] arr, int finalIndexForSort) {
    //    //todo
    //}

    public void mergeSort(String[] arr, int finalIndexForSort)
    {
        if (arr.length > 1 && finalIndexForSort <= arr.length)
        {
            String[] leftArr = new String[finalIndexForSort / 2];
            String[] rightArr = new String[finalIndexForSort - finalIndexForSort / 2];
            for (int i = 0; i < leftArr.length; i++)
            {
                leftArr[i] = arr[i];
            }
            for (int i = 0; i < rightArr.length; i++)
            {
                rightArr[i] = arr[i + finalIndexForSort / 2];
            }
            mergeSort(leftArr, leftArr.length);
            mergeSort(rightArr, rightArr.length);
            merge(arr, leftArr, rightArr);
        }
    }

    private void merge(String[] nameH, String[] leftH, String[] rightH)
    {
        int as = 0;
        int bs = 0;
        for (int i = 0; i < nameH.length; i++)
        {
            if (bs >= rightH.length || (as < leftH.length && leftH[as].compareToIgnoreCase(rightH[bs]) < 0))
            {
                nameH[i] = leftH[as];
                as++;
            }
            else
            {
                nameH[i] = rightH[bs];
                bs++;
            }
        }
    }


    public void hybridSort(String[] arr, int size, int finalIndexForSort) {
        //todo
    }
}
