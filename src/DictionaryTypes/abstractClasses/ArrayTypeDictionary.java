package DictionaryTypes.abstractClasses;

public abstract class ArrayTypeDictionary extends Dictionary {

    /* ARRAY SPECIFIC METHODS handin2*/
    public void insertionSort(String[] arr, int finalIndexForSort)
    {
        String tempString = "";
        for( int i = 0; i < finalIndexForSort; i++)
        {
            for( int j = i + 1; j < finalIndexForSort; j++)
            {
                if(arr[i].compareToIgnoreCase(arr[j])>0)
                {
                    tempString = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tempString;
                }
            }
        }
    }


    public void mergeSort(String[] arr, int finalIndexForSort) {
        //todo
    }

    public void hybridSort(String[] arr, int size, int finalIndexForSort) {
        //todo
    }
}
