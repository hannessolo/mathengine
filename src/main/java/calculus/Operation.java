package calculus;

public interface Operation {
    double eval(Expression a, Expression b, Identity[] vars);
    String getOperator();
}
