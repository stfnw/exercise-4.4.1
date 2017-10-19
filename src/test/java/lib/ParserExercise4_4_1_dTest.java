package lib;

import main.ParseTables;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(Parameterized.class)
public class ParserExercise4_4_1_dTest {

  @Parameterized.Parameters
  public static Iterable<Object[]> data() {
    return Arrays.asList(new Object[][]{
        {"", false},
        {"a+", false},
        {"+*", false},
        {"*", false},
        {"a", true},
        {"(a+a)*a", true},
        {"(((a)))*)", true}
    });
  }

  private Parser parser;
  private boolean expected;


  public ParserExercise4_4_1_dTest(String input, boolean expected) {
    ParseTables tables = new main.ParseTables();
    this.parser = new Parser(tables.getParseTable('d'),
        tables.getStartNonterminal('d'),
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