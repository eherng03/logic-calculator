package INCO;

import java.lang.String;

public class XOROperation implements Operation {
  private String name;

  public XOROperation() {
    this.name = "XOR";
  }

  public String getName() {
    return name;
  }

  public Operand operate(Operand a, Operand b) {
    Operand result = new Operand();
    ;
        ;
        OROperation operation = new OROperation();
        operation.operate(a, b);
        ;
        OROperation operation = new OROperation();
        operation.operate(a, b);
    return result;
  }
}
