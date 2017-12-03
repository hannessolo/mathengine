package calculus;

import static calculus.BinaryOperation.*;

class BinaryApplication implements Expression {

  private BinaryOperation op;
  private Expression arg1;
  private Expression arg2;

  BinaryApplication(BinaryOperation op, Expression e1, Expression e2) {
    this.op = op;
    arg1 = e1;
    arg2 = e2;
  }

  @Override
  public double eval(Identity[] vars) {
    return op.eval(arg1, arg2, vars);
  }

  @Override
  public String getPrintable() {
    return "(".concat(arg1.getPrintable()).concat(op.getOperator())
        .concat(arg2.getPrintable()).concat(")");
  }

  @Override
  public Expression differentiate(String x) {
    switch (op) {
      case ADD:
        return new BinaryApplication(ADD, arg1.differentiate(x), arg2.differentiate(x));
      case SUBTRACT:
        return new BinaryApplication(SUBTRACT, arg1.differentiate(x), arg2.differentiate(x));
      case MULTIPLY:
        return new BinaryApplication(ADD, new BinaryApplication(op, arg1.differentiate(x), arg2),
            new BinaryApplication(op, arg1, arg2.differentiate(x)));
      case DIVIDE:
        return new BinaryApplication(DIVIDE, new BinaryApplication(SUBTRACT,
            new BinaryApplication(op, arg1.differentiate(x), arg2),
            new BinaryApplication(op, arg1, arg2.differentiate(x))),
            new BinaryApplication(MULTIPLY, arg2, arg2));
      case POWER:
        return new BinaryApplication(MULTIPLY,
          new BinaryApplication(MULTIPLY, arg2,
              new BinaryApplication(POWER, arg1,
                  new BinaryApplication(SUBTRACT, arg2, new Identity(1)))),
            arg1.differentiate(x)
        );
      default:
        return null;

    }
  }
}
