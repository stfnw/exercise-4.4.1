# Top-down parser using predictive parsing tables

This is a basic Java-implementation of table-driven predictive parsers for the various grammars given in Exercise 4.4.1 of the Dragon Book<sup>[1](#drg)</sup>.

For the sake of simplicity, the parser operates directly on the input.
Therefore, the lexical analyzer and other essential parts of a compiler-frontend are missing.
Furthermore, only single-letter tokens without white space or another delimiter in-between can be processed.

Given an exercise and an input string, the program:

  * prints the original grammar of the exercise,
  * prints the pre-processed grammar (without left recursion; left factored),
  * parses the input string according to the grammar
    (during parsing the chosen productions and matched terminals are printed), and
  * prints, whether the input could be derived from the start-nonterminal or not.


## Build

To build the project, enter:

    mvn package

To run only the tests, enter:

    mvn test

To run the project after building, enter:

    java -jar target/exercise-4.4.1-1.0-SNAPSHOT-jar-with-dependencies.jar exercise string-to-parse


<a id="drg">[1]</a>: "Compilers: Principles, Techniques, and Tools (2Nd Edition)" by Alfred V. Aho, Monica S. Lam, Ravi Sethi, and Jeffrey D. Ullman.
