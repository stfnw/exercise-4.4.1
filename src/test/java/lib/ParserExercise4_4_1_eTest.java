package lib;

import main.ParseTables;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParserExercise4_4_1_eTest {

  @Parameterized.Parameters
  public static Iterable<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {"", false},
        {"()", false},
        {"(", false},
        {"((a)", false},
        {"(a,a,)", false},
        {"a", true},
        {"(a)", true},
        {"((a))", true},
        {"(a,a)", true},
        {"((a,a),a,(a))", true},
    });
  }

  private Parser parser;
  private boolean expected;


  public ParserExercise4_4_1_eTest(String input, boolean expected) {
    ParseTables tables = new main.ParseTables();
    this.parser = new Parser(tables.getParseTable('e'),
        tables.getStartNonterminal('e'),
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