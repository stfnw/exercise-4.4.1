package lib;

public class Production {
  private final char head;
  private final String body;


  public Production(char head, String body) {
    this.head = head;
    this.body = body;
  }


  public String getBody() {
    return body;
  }


  @Override
  public String toString() {
    return head + " -> " + body;
  }
}
