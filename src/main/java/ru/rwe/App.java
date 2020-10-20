package ru.rwe;

import org.apache.log4j.Logger;
import java.util.Arrays;

public class App
{
    private static final Logger log = Logger.getLogger(App.class);

    public static void main( String[] args )
    {
        log.info( App.class.getName() +" by mode " + Property.TEST_MODE);

        int [] arr = new int[]{ 78, 22, 145, 8, 96, 37, 88, 112, 377, 256};
        log.info(arrToString(arr));

        measureTime(() -> quickSort(arr, 0, arr.length - 1) );
    }

    private static void quickSort(int[] arr, int from, int to){

        if( from < to){
            int divideIndex = partition(arr, from, to);

            printSortStep(arr, from, to, divideIndex);

            quickSort(arr, from, divideIndex -1 );
            quickSort(arr, divideIndex, to);
        }
    }

    private static int partition(int[] arr, int from, int to){
        int rightIndex = to;
        int leftIndex = from;

        int pivot = arr[from + (to -from) /2];
        while(leftIndex <= rightIndex){

            while (arr[leftIndex] < pivot){
                leftIndex++;
            }

            while(arr[rightIndex] > pivot){
                rightIndex--;
            }

            if(leftIndex <= rightIndex){
                swap(arr, rightIndex, leftIndex);
                leftIndex++;
                rightIndex--;
            }

        }

        return leftIndex;
    }

    private static void swap(int[] arr, int idx1, int idx2){
        int tmp = arr[idx1];

        arr[idx1] = arr[idx2];
        arr[idx2] = tmp;
    }

    private static String arrToString(int[] arr){
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for(int i = 0; i < arr.length; i++){
            if(i >0) sb.append(", ");

            sb.append( arr[i] );
        }

        sb.append("]");

        return sb.toString();
    }

    private static void printSortStep(int[] arr, int from, int to, int partitionIndex){
        log.info(arrToString(arr));
        log.info("partition at index: " + partitionIndex);
        log.info("Left: " + arrToString( Arrays.copyOfRange(arr, from, partitionIndex) ));
        log.info("Right: " + arrToString( Arrays.copyOfRange(arr, partitionIndex, to+1 ))  );

    }


    private static void measureTime(Runnable task){
        long startTime = System.currentTimeMillis();
        task.run();

        long elapsed = System.currentTimeMillis() - startTime;
        log.info("used time: " + elapsed + " ms");
    }

}
