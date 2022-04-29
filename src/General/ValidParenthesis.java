package General;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParenthesis {

        public boolean isValid(String s) {

            if(s==null) return true;
            s=s.trim();
            if(s.length()==0) return true;

            Map<Character,Character> map = new HashMap<Character,Character>();
            map.put(')','(');
            map.put('}','{');
            map.put(']','[');

            Stack<Character> stack = new Stack<>();

            for(int i=0;i<s.length();i++) {
                char curr = s.charAt(i);

                if(map.containsKey(curr)) {
                    char val = stack.isEmpty()? '#' : stack.pop();
                    if(val!=map.get(curr)) {
                        return false;
                    }

                }
                else {
                    stack.push(curr);
                }
            }
            return stack.isEmpty();
        }

        public static void main(String [] args){
            ValidParenthesis validParenthesis = new ValidParenthesis();
            System.out.println(validParenthesis.isValid("()"));
            System.out.println(validParenthesis.isValid("()[]{}"));
            System.out.println(validParenthesis.isValid("(]"));
            System.out.println(validParenthesis.isValid("([)]"));
            System.out.println(validParenthesis.isValid("{[]}"));
        }

}
