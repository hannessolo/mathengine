package calculus;

interface Expression {

    double eval(Identity[] vars);
    String getPrintable();
    Expression differentiate(String x);

}
