import java.util.Stack;
import java.util.Deque;
import java.util.LinkedList;

public class PalindromeCheckerApp {

    public static void main(String[] args) {

        String input = "madam";

        PalindromeStrategy strategy = new StackStrategy();
        PalindromeService service = new PalindromeService(strategy);

        boolean result = service.check(input);

        if (result) {
            System.out.println("Using Stack Strategy:");
            System.out.println("The given string \"" + input + "\" is a Palindrome.");
        } else {
            System.out.println("Using Stack Strategy:");
            System.out.println("The given string \"" + input + "\" is NOT a Palindrome.");
        }

        strategy = new DequeStrategy();
        service = new PalindromeService(strategy);

        result = service.check(input);

        if (result) {
            System.out.println("Using Deque Strategy:");
            System.out.println("The given string \"" + input + "\" is a Palindrome.");
        } else {
            System.out.println("Using Deque Strategy:");
            System.out.println("The given string \"" + input + "\" is NOT a Palindrome.");
        }
    }
}

interface PalindromeStrategy {
    boolean isPalindrome(String input);
}

class StackStrategy implements PalindromeStrategy {

    public boolean isPalindrome(String input) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));
        }

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) != stack.pop()) {
                return false;
            }
        }

        return true;
    }
}

class DequeStrategy implements PalindromeStrategy {

    public boolean isPalindrome(String input) {

        Deque<Character> deque = new LinkedList<>();

        for (int i = 0; i < input.length(); i++) {
            deque.addLast(input.charAt(i));
        }

        while (deque.size() > 1) {
            if (deque.removeFirst() != deque.removeLast()) {
                return false;
            }
        }

        return true;
    }
}

class PalindromeService {

    private PalindromeStrategy strategy;

    public PalindromeService(PalindromeStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean check(String input) {
        return strategy.isPalindrome(input);
    }
}