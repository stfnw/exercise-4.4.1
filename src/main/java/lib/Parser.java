package lib;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * The predictive parser (a top-down parser), which can parse LL(1)-grammars.
 */
public class Parser {

  String input;
  private Deque<Character> stack;

  private Map<Key, Production> parseTable;


  public Parser(HashMap<Key, Production> parseTable, char startNonterminal, String input) {
    this.input = input + "$";

    stack = new LinkedList<>();
    stack.push(startNonterminal);

    this.parseTable = parseTable;
  }

  /**
   * Perform syntactical analysis on the given input.
   * @return true - if it was successful, i.e. the input
   *                is part of the language generated by the grammar
   *         false - otherwise
   */
  public boolean parse() {
    // iterate over the input characterwise
    CharacterIterator it = new StringCharacterIterator(input);

    Character c = it.first();
    Character X = stack.peek();

    while (!stack.isEmpty()) {
      if (X == c) {
        System.out.println("\u001b[32m[+] matched terminal " + c + "\u001b[39m");
        stack.pop();
        c = it.next();
      } else if ('a' <= X && X <= 'z' || '0' <= X && X <= '9')
        // terminal
        return false;
      else if (!parseTable.containsKey(new Key(X, c)))
        return false;
      else {
        Production t = parseTable.get(new Key(X, c));
        System.out.println("\tchose production " + t);
        stack.pop();

        for (int i = t.getBody().length() - 1; i >= 0; i--)
          // push the body on the stack in reverse order
          stack.push(t.getBody().charAt(i));
      }

      X = stack.peek();
    }

    // return true only if input completely consumed (caution: end-marker $)
    return (it.getIndex() + 1 == it.getEndIndex());
  }
}
