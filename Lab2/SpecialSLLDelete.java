package Lab2;
//Дадена е еднострано поврзана листа чии што јазли содржат по еден природен број. Дополнително, даден е и уште еден природен број M (M>1). Треба од дадената листа да се избрише секој M-ти елемент.
//
//Влез: Во првиот ред од влезот е даден бројот на елементи во листата, па во следниот ред се дадени самите елементи од листата. На крај, во последниот ред е даден бројот M.
//
//Излез: На излез треба да се испечати променетата листа, со избришан секој M-ти елемент.
//
//Внимавајте:
//
//1. Даден е целосниот код на структурата којашто треба да се користи. Дадена е и тест класата SpecialSLLDelete.java, со целосно имплементиран input и output. Потребно е да се менува само во рамки на void specialDelete(SLL<Integer> list, int m) функцијата.
//
//2. Не смее да менувате во main функцијата !
//
///
//
//You are given a single linked list with integer nodes. Additionally, you are given one more integer M (M>1). You need to delete every M-th element from the list.
//
//Input: In the first line from the input the number of elements in the list is given, and then in the next line the elements themselves. In the last line, the integer M is given.
//
//Output: The transformed list (with every M-th element deleted) should be printed at the output.
//
//Pay attention:
//
//1. All the needed code for the structure that you need to use is given. The test class SpecialSLLDelete.java is also given, with completely implemented input and output. You only need to change the code of the void specialDelete(SLL<Integer> list, int m) method.
//
//
//2. You must not change the main method !
//For example:
//
//Input	Result
//7
//1 2 5 6 3 4 7
//3

//1 2 6 3 7
import java.util.Scanner;
import static java.util.Collections.list;

public class SpecialSLLDelete<E> {

    public void specialDelete(SLL<E> list, int m) {
        SLLNode <E> next = list.getFirst();
        SLLNode <E> p = next;
        for (int i = 0; i <= list.size(); i++){
            if (next != null){
                p = next;
                next = next.succ;
                if(i+1 == m){
                    m+=m;
                    list.delete(p);
                }
            }
        }
    }


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();

        SLL<Integer> list = new SLL<>();
        for(int i=0;i<n;i++) {
            list.insertLast(input.nextInt());
        }

        int m = input.nextInt();

        SpecialSLLDelete<Integer> tmp = new SpecialSLLDelete<>();

        tmp.specialDelete(list, m);

        System.out.println(list);
    }

}
