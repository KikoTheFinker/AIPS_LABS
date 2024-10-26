package Lab1;
//За дадена низа од случајни броеви кои се внесуваат од стандарден влез, да се направи преместување на сите нули на почеток на низата. На стандарден излез да се испечати трансформираната низа.
//
///
//
//For a given array of random numbers given from standard input, perform a shift of all zeros at the beginning of the sequence. Print the transformed array to standard output.
//
//For example:
//12
//1 9 8 4 0 0 2 7 0 6 0 9
//Transformiranata niza e:
//0 0 0 0 1 9 8 4 2 7 6 9
import java.util.Scanner;
import java.lang.Math;
public class PushZero
{
    static void pushZerosToBeginning(int arr[], int n)
    {
        int k = n-1;
        int counter = 1;
        for (int i = n-1; i >= 0; i--){
            if (arr[i] != 0){
                arr[k--] = arr[i];
                counter++;
            }
            if (i <= Math.abs(counter-n)){
                arr[i] = 0;
            }
        }
        System.out.println("Transformiranata niza e:");
        for (int i = 0; i < n; i++){
            System.out.printf("%d ", arr[i]);
        }
    }

    public static void main (String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int []arr = new int[n];
        for (int i = 0; i < n; i++){
            arr[i] = scanner.nextInt();
        }
        pushZerosToBeginning(arr, n);
    }
}