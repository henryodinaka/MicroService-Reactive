package leo.henry.co;

import java.util.Arrays;
import java.util.Collections;

public  class Permutation {

    public static <T> void printAllRecursive(T[] elements, char delimiter) {
        printAllRecursive(elements.length, elements, delimiter);
    }

    public static <T> void printAllRecursive(int n, T[] elements, char delimiter) {

        if(n == 1) {
            outPutArray(elements, delimiter);
        } else {
            for(int i = 0; i < n-1; i++) {
                printAllRecursive(n - 1, elements, delimiter);
                if(n % 2 == 0) {
                    interChangePosition(elements, i, n-1);
                } else {
                    interChangePosition(elements, 0, n-1);
                }
            }
            printAllRecursive(n - 1, elements, delimiter);
        }
    }

    public static <T> void printAllIterative(int n, T[] elements, char delimiter) {

        int[] indexes = new int[n];
        for (int i = 0; i < n; i++) {
            indexes[i] = 0;
        }

        outPutArray(elements, delimiter);

        int i = 0;
        while (i < n) {
            if (indexes[i] < i) {
                interChangePosition(elements, i % 2 == 0 ?  0: indexes[i], i);
                outPutArray(elements, delimiter);
                indexes[i]++;
                i = 0;
            }
            else {
                indexes[i] = 0;
                i++;
            }
        }
    }

    public static <T extends Comparable<T>> void printAllOrdered(T[] inputArray, char del) {

        Arrays.sort(inputArray);
        boolean hasNext = true;

        while(hasNext) {
            outPutArray(inputArray, del);
            int m = 0, n = 0;
            hasNext = false;
            for (int i = inputArray.length - 1; i > 0; i--) {
                if (inputArray[i].compareTo(inputArray[i - 1]) > 0) {
                    m = i - 1;
                    hasNext = true;
                    break;
                }
            }

            for (int i = inputArray.length - 1; i > m; i--) {
                if (inputArray[i].compareTo(inputArray[m]) > 0) {
                    n = i;
                    break;
                }
            }

            interChangePosition(inputArray, m, n);
            Collections.reverse(Arrays.asList(inputArray).subList(m + 1, inputArray.length));
        }
    }

    public static <T> void printRandom(T[] elements, char delimiter) {

        Collections.shuffle(Arrays.asList(elements));
        outPutArray(elements, delimiter);
    }

    private static <T> void interChangePosition(T[] elements, int a, int b) {

        T tmp = elements[a];
        elements[a] = elements[b];
        elements[b] = tmp;
    }

    private static <T> void outPutArray(T[] elements, char delimiter) {

        String delimiterSpace = delimiter + " ";
        for(int i = 0; i < elements.length; i++) {
            System.out.print(elements[i] + delimiterSpace);
        }
        System.out.print('\n');
    }

    public static void main(String[] argv) {

        String[] elements = {"b","a","c",""};

//        System.out.println("Rec:");
//        printAllRecursive(elements, ';');
//
//        System.out.println("Iter:");
//        printAllIterative(elements.length, elements, ';');

//        System.out.println("Orderes:");
//        printAllOrdered(elements, ';');
        String baca = rearrangeWord("xy");
        System.out.println(baca);
//
//        System.out.println("Random:");
//        printRandom(elements, ';');
//
//        System.out.println("Random:");
//        printRandom(elements, ';');
    }
    public static String rearrangeWord(String word) {
        StringBuilder builder = new StringBuilder();
        String [] inputArray = word.split("");
        Arrays.sort(inputArray);
        boolean hasNext = true;

        while(hasNext) {
            for(int i = 0; i < inputArray.length; i++) {
                builder.append(inputArray[i] + ' ');
            }
            builder.append('\n');
            int m = 0, n = 0;
            hasNext = false;
            for (int i = inputArray.length - 1; i > 0; i--) {
                if (inputArray[i].compareTo(inputArray[i - 1]) > 0) {
                    m = i - 1;
                    hasNext = true;
                    break;
                }
            }

            for (int i = inputArray.length - 1; i > m; i--) {
                if (inputArray[i].compareTo(inputArray[m]) > 0) {
                    n = i;
                    break;
                }
            }

            String  tmp = inputArray[m];
            inputArray[m] = inputArray[n];
            inputArray[n] = tmp;
            Collections.reverse(Arrays.asList(inputArray).subList(m + 1, inputArray.length));
        }
        return builder.toString();
    }
}