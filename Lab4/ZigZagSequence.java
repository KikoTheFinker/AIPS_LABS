package Lab4;
//Секвенца од броеви се смета за цик цак секвенца ако броевите во секвенцата се наизменично позитивни и негативни т.е. за секој пар од последователни броеви важи дека едниот е позитивен, а другиот е негативен.
//
//На пример -1 2 -9 8 -4 е цик цак секвенца, но -1 9 7 -3 8 -3 не е, затоа што 9 и 7 се соседни броеви, но и двата се позитивни. Цик цак секвенцата може да почне или со позитивен или со негативен број. Секвенца од само еден ненулти број се смета како цик цак секвенца. За дадена низа од броеви да се напише алгоритам кој ќе ја врати должината на најдолгата подниза која претставува цик цак секвенца.
//
//Во првиот ред од влезот даден е бројот N за должината на низата. Во секој од следните N редови е даден по еден број од оригиналната низа. На излез треба да се испечати должината на најдолгата подниза која е цик цак секвенца од оригиналната низа.
//
//Име на класата: ZigZagSequence
//
//=================================================================
//A sequence of numbers is considered a zigzag sequence if the numbers in the sequence are alternately positive and negative i.e. for every pair of consecutive numbers, one is positive and the other is negative.
//
//For example -1 2 -9 8 -4 is a zigzag sequence, but -1 9 7 -3 8 -3 is not, because 9 and 7 are adjacent numbers, but both are positive. A zigzag sequence can start with either a positive or a negative number. A sequence of only one non-zero number is considered a zigzag sequence. For a given sequence of numbers, write an algorithm that will return the length of the longest subarray that represents a zigzag sequence.
//
//In the first line of the input, the number N is given for the length of the string. In each of the next N rows, one number from the original sequence is given. The output should print the length of the longest subarray that is a zigzag sequence of the original array.
//
//
//Name of Java class: ZigZagSequence
//For example:
//
//Input	Result
//4
//-1
//4
//-3
//2

//4
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ZigZagSequence{

    static int najdiNajdolgaCikCak(int a[]) {
        int max = 1;
        int promax = 0;
        for (int i = 0; i < a.length-1; i++){
            if (a[i] < 0 && a[i+1] > 0){
                max++;
            }
            else if (a[i] > 0 && a[i+1] < 0){
                max++;
            }
            else {
                max = 1;
            }
            if (max > promax){
                promax =max;
            }
        }
        return promax;
    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int a[] = new int[N];
        for (i=0;i<N;i++)
            a[i] = Integer.parseInt(br.readLine());

        int rez = najdiNajdolgaCikCak(a);
        System.out.println(rez);

        br.close();

    }

}
