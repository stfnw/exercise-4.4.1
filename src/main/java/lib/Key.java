package lib;

/**
 * The key of the predictive parsing table used to choose,
 * which production to apply given...
 */
public class Key {
  /** ...a certain nonterminal on the stack and... */
  final private char nonterminal;

  /** ...a certain terminal/input symbol. */
  final private char inputSymbol;


  public Key(char nonterminal, char inputSymbol) {
    this.nonterminal = nonterminal;
    this.inputSymbol = inputSymbol;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Key key = (Key) o;

    if (nonterminal != key.nonterminal) return false;
    return inputSymbol == key.inputSymbol;
  }

  @Override
  public int hashCode() {
    int result = (int) nonterminal;
    result = 31 * result + (int) inputSymbol;
    return result;
  }
}
