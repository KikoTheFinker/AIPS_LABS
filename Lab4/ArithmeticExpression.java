package Lab4;
//Даден е некој аритметички израз. Аритметичкиот израз е во облик (A+B) или (A-B) каде што А и B истовремено се други аритметички изрази или цифри од 0-9. Потребно е да го евалуирате дадениот израз.
//
//Име на класата (Java): ArithmeticExpression
//
//=================================================================
//
//An arithmetic expression is given. An arithmetic expression is of the form (A+B) or (A-B) where A and B are both other arithmetic expressions or digits from 0-9. You need to evaluate the given expression.
//
//Name of Java class: ArithmeticExpression
//For example:
//
//Input	Result
//(1+2)

//3

//(((1-1)+(2-2))-((1-1)+(2-2)))
//0

//((((1+5)-(2-3))-(2-8))+((1+2)-((5+6)-(3-7))))
//1

//(((1+6)-(6-1))+(2+0))
//4

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class ArithmeticExpression {

    // funkcija za presmetuvanje na izrazot pocnuvajki
    // od indeks l, zavrsuvajki vo indeks r
    static int presmetaj(char c[], int l, int r) {
        Stack<Character> Brackets = new Stack<>();
        Stack<Integer> Numbers = new Stack<>();
        Stack<Character> Aritmetic = new Stack<>();
        int result = 0;
        for (int i = l; i <= r; i++) {
            if (c[i] == '(') {
                Brackets.push(c[i]);
            } else if (c[i] == ')') {
                Brackets.push(c[i]);
            } else if (Character.isDigit(c[i])) {
                Numbers.push(Character.getNumericValue(c[i]));
            } else {
                Aritmetic.push(c[i]);
            }
            if (Brackets.peek() == ')'){
                if (Aritmetic.peek() == '+') {
                    result = Numbers.pop() + Numbers.pop();
                }
                else {
                    result = Numbers.pop();
                    result = Numbers.pop() - result;
                }
                Numbers.push(result);
                Aritmetic.pop();
                Brackets.pop();
            }
        }
        Brackets.empty();
        Aritmetic.empty();

        return Numbers.pop();

    }

    public static void main(String[] args) throws Exception {
        int i,j,k;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = presmetaj(exp, 0, exp.length-1);
        System.out.println(rez);

        br.close();

    }

}

