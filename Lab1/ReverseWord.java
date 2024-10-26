package Lab1;
//За даден збор кој се внесува од стандарден влез, да се испечати истиот превртен. На влез во првиот ред се дава број на зборови кои ќе се внесуваат. Во наредните линии се внесуваат самите зборови.
//
///
//
//For a given word entered from standard input, print it reversed. On input in the first line, the number of words that will be entered is given. In the following lines, the words are entered.
//
//For example:
//
//Input	Result
//3
//one
//two
//three

//eno
//owt
//eerht
import java.util.Scanner;

public class ReverseWord {

    public static void printReversed(String word) {
        StringBuilder reversed = new StringBuilder(word);
        reversed.reverse();
        System.out.println(reversed);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] p = new String[n];
        for (int i = 0; i < n; i++){
            p[i] = scanner.next();
            printReversed(p[i]);
        }
    }
}

