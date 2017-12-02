package calculus;

public interface Expression {

    double eval(Identity[] vars);
    String getPrintable();
    Expression differentiate(String x);

}
