package main;

import lib.Key;
import lib.Production;

import java.util.HashMap;


/**
 * Contains the predictive parsing tables for the exercises.
 */
public class ParseTables {
  private HashMap<Character, HashMap<Key, Production>> tables;
  private HashMap<Character, String> descriptions;
  private HashMap<Character, Character> startNonterminals;

  public ParseTables() {
    tables = new HashMap<>();
    descriptions = new HashMap<>();
    startNonterminals = new HashMap<>();

    HashMap<Key, Production> parseTable;


    parseTable = new HashMap<>();
    parseTable.put(new Key('S', '0'), new Production('S', "0A"));
    parseTable.put(new Key('A', '0'), new Production('A', "0A1"));
    parseTable.put(new Key('A', '1'), new Production('A', "1"));
    tables.put('a', parseTable);
    descriptions.put('a',
        getDescription("S -> 0S1 | 01", "\n\tS -> 0A\n\tA -> 0A1 | 1")
    );
    startNonterminals.put('a', 'S');

    parseTable = new HashMap<>();
    parseTable.put(new Key('S', '+'), new Production('S', "+SS"));
    parseTable.put(new Key('S', '*'), new Production('S', "*SS"));
    parseTable.put(new Key('S', 'a'), new Production('S', "a"));
    tables.put('b', parseTable);
    descriptions.put('b',
        getDescription("S -> +SS | *SS | a", "S -> +SS | *SS | a")
    );
    startNonterminals.put('b', 'S');


    /*
     * since there are two production-entries in the parsing table
     * for the nonterminal 'A' on the input '(',
     * this is not a ll(1) grammar and therefore can't be parsed by a
     * predictive parser
     */
    /*
    parseTable = new HashMap<>();
    parseTable.put(new Key('S', '('), new Production('S', "A"));
    parseTable.put(new Key('S', ')'), new Production('S', "A"));
    parseTable.put(new Key('S', '$'), new Production('S', "A"));
    parseTable.put(new Key('A', '('), new Production('A', "(S)SA"));
    parseTable.put(new Key('A', '('), * new Production('A', ""));
    parseTable.put(new Key('A', ')'), new Production('A', ""));
    parseTable.put(new Key('A', '$'), new Production('A', ""));
    tables.put('c', parseTable);
    startNonterminals.put('c', 'S');
    */

    /* not predictive (multiple ε-Productions missing) */
    /*
    parseTable = new HashMap<>();
    parseTable.put(new Key('A', '('), new Production('A', ""));
    parseTable.put(new Key('A', ')'), new Production('A', ""));
    parseTable.put(new Key('A', '+'), new Production('A', ""));
    parseTable.put(new Key('A', '*'), new Production('A', ""));
    parseTable.put(new Key('A', 'a'), new Production('A', ""));
    parseTable.put(new Key('A', '$'), new Production('A', ""));
    parseTable.put(new Key('S', '('), new Production('S', "(S)A"));
    parseTable.put(new Key('S', 'a'), new Production('S', "aA"));
    parseTable.put(new Key('A', '('), new Production('A', "SA"));
    parseTable.put(new Key('A', ')'), new Production('A', "SA"));
    parseTable.put(new Key('A', '+'), new Production('A', "+SA"));
    parseTable.put(new Key('A', '*'), new Production('A', "*A"));
    parseTable.put(new Key('A', 'a'), new Production('A', "SA"));
    tables.put('d', parseTable);
    startNonterminals.put('d', 'S');
    */

    parseTable = new HashMap<>();
    parseTable.put(new Key('S', '('), new Production('S', "(L)"));
    parseTable.put(new Key('S', 'a'), new Production('S', "a"));
    parseTable.put(new Key('L', '('), new Production('L', "(L)A"));
    parseTable.put(new Key('L', 'a'), new Production('L', "aA"));
    parseTable.put(new Key('A', ')'), new Production('A', ""));
    parseTable.put(new Key('A', ','), new Production('A', ",SA"));
    tables.put('e', parseTable);
    descriptions.put('e',
        getDescription("\n\tS -> (L) | a\n\tL -> L,S | S",
            "\n\tS -> (L) | a\n\tL -> (L) A | aA\n\tA -> ,SA | ε")
    );
    startNonterminals.put('e', 'S');

    parseTable = new HashMap<>();
    parseTable.put(new Key('E', '!'), new Production('E', "TA"));
    parseTable.put(new Key('E', '('), new Production('E', "TA"));
    parseTable.put(new Key('E', '0'), new Production('E', "TA"));
    parseTable.put(new Key('E', '1'), new Production('E', "TA"));
    parseTable.put(new Key('T', '!'), new Production('T', "FB"));
    parseTable.put(new Key('T', '('), new Production('T', "FB"));
    parseTable.put(new Key('T', '0'), new Production('T', "FB"));
    parseTable.put(new Key('T', '1'), new Production('T', "FB"));
    parseTable.put(new Key('A', '$'), new Production('A', ""));
    parseTable.put(new Key('A', '|'), new Production('A', "|TA"));
    parseTable.put(new Key('A', ')'), new Production('A', ""));
    parseTable.put(new Key('F', '!'), new Production('F', "!F"));
    parseTable.put(new Key('F', '('), new Production('F', "(E)"));
    parseTable.put(new Key('F', '0'), new Production('F', "0"));
    parseTable.put(new Key('F', '1'), new Production('F', "1"));
    parseTable.put(new Key('B', '$'), new Production('B', ""));
    parseTable.put(new Key('B', '|'), new Production('B', ""));
    parseTable.put(new Key('B', '&'), new Production('B', "&FB"));
    parseTable.put(new Key('B', ')'), new Production('B', ""));
    tables.put('f', parseTable);
    descriptions.put('f',
        getDescription("('|' - without space as delimiter - is used for logical or)" +
                "\n\tE -> E|T | T\n\tT -> T&F | F\n\tF -> !F | (E) | 0 | 1",
            "\n\tE -> TA\t\tA -> |TA | ε\n\tT -> FB\t\tB -> &FB | ε\n\t" +
                "F -> !F | (E) | 0 | 1")
    );
    startNonterminals.put('f', 'E');
  }

  public HashMap<Key, Production> getParseTable(char exercise) {
    return tables.get(exercise);
  }

  private String getDescription(String original, String processed) {
    return "Parsing the following grammar:\n" +
        "original: " + original + "\n" +
        "without left recursion and left factored (i.e. LL(1) grammar " +
        "suitable for predictive parsing): " + processed + "\n";
  }

  public String getDescription(char exercise) {
    return descriptions.get(exercise);
  }

  public char getStartNonterminal(char exercise) {
    return startNonterminals.get(exercise);
  }
}
