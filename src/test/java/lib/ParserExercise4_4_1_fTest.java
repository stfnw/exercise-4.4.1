package lib;

import main.ParseTables;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParserExercise4_4_1_fTest {

  @Parameterized.Parameters
  public static Iterable<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {"", false},
        {"|", false},
        {"&|", false},
        {"01", false},
        {"0|(0", false},
        {"!((0)|(1))&1)", false},
        {"0", true},
        {"1", true},
        {"0|!0", true},
        {"((0))", true},
        {"!(!0|1&0)", true},
        {"0|1|0|1&1&1&0", true},
        {"!((0)|(1))&1", true}
    });
  }

  private Parser parser;
  private boolean expected;


  public ParserExercise4_4_1_fTest(String input, boolean expected) {
    ParseTables tables = new main.ParseTables();
    this.parser = new Parser(tables.getParseTable('f'),
        tables.getStartNonterminal('f'),
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
