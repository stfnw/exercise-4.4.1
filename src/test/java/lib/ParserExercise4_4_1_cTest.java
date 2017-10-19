package lib;

import main.ParseTables;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
@Ignore // not a ll(1) grammar
public class ParserExercise4_4_1_cTest {

  @Parameterized.Parameters
  public static Iterable<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {"", true},
        {"()", true},
        {"(())", true},
        {"(()())", true},
        {"((())())", true},
        {"(", false},
        {")", false},
        {"())", false},
        {"(()", false},
        {"(()((()()))", false}
    });
  }

  private Parser parser;
  private boolean expected;


  public ParserExercise4_4_1_cTest(String input, boolean expected) {
    ParseTables tables = new main.ParseTables();
    this.parser = new Parser(tables.getParseTable('c'),
        tables.getStartNonterminal('c'),
        input);
    this.expected = expected;
  }


  @Test
  public void parseTest() {
    assertEquals(expected, parser.parse());
    System.out.println("Correctly classified input \"" + parser.input + "\""
        + " as " + (expected ? "" : "not ") + "part of the language" +
        " produced by the grammar");
    System.out.println();
  }
}