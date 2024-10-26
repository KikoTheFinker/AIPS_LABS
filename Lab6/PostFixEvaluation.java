package Lab6;
//<p>Да се напише алгоритам кој ќе врши евалуација на израз во постфикс нотација.</p>
//
//<p>На влез се чита низа од знаци за изразот (стринг), а на излез се печати вредноста на изразот по евалуацијата.</p>
//
//<p>Име на класата (Java): PostFixEvaluation</p>
//
//----------
//
//<p>Write an algorithm that will evaluate an expression in postfix notation.</p>
//
//<p>A sequence of characters for the expression (string) is read at the input, and the value of the expression after evaluation is printed at the output.</p>
//
//<p>Class Name (Java): PostFixEvaluation</p>
//
//For example:
//
//Input	Result
//1 2 +
//3

//28 72 * 13 + 20 67 * +
//3369
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class PostFixEvaluation {

    static int evaluatePostfix(char [] izraz, int n) {
        Stack<Integer> nums = new Stack<Integer>();
        Stack<Character> chars = new Stack<Character>();

        int brojac = 0;
        for (int i = 0; i < n; i++) {
            if (Character.isDigit(izraz[i])) {
                nums.push(Character.getNumericValue(izraz[i]));
                if (Character.isDigit(izraz[i + 1])) {
                    nums.push(nums.pop() * 10 + Character.getNumericValue(izraz[i+1]));
                    i++;
                    if (Character.isDigit(izraz[i+1])){
                        nums.push(nums.pop() * 10 + Character.getNumericValue(izraz[i+1]));
                        i++;
                    }
                }

            } else {
                if (!Character.isSpaceChar(izraz[i]) && !Character.isDigit(izraz[i])) {
                    chars.push(izraz[i]);
                }
            }
            if (!chars.isEmpty()) {
                if (chars.peek() == '*') {
                    int result = nums.pop() * nums.pop();
                    nums.push(result);
                    chars.pop();
                }
                if (!chars.isEmpty() && chars.peek() == '+') {
                    int result = nums.pop() + nums.pop();
                    nums.push(result);
                    chars.pop();
                }
                if (!chars.isEmpty() && chars.peek() == '-') {
                    int p = nums.pop();
                    int result = nums.pop() - p;
                    nums.push(result);
                    chars.pop();
                }
                if (!chars.isEmpty() && chars.peek() == '/') {
                    int p = nums.pop();
                    int result = nums.pop() / p;
                    nums.push(result);
                    chars.pop();
                }
            }
        }
        return nums.pop();
    }




    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String expression = br.readLine();
        char exp[] = expression.toCharArray();

        int rez = evaluatePostfix(exp, exp.length);
        System.out.println(rez);

        br.close();

    }

}
