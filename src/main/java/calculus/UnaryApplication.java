package calculus;

class UnaryApplication implements Expression {

    private UnaryOperation op;
    private Expression arg1;

    UnaryApplication(UnaryOperation op, Expression arg1) {

        this.op = op;
        this.arg1 = arg1;

    }

    @Override
    public double eval(Identity[] vars) {
        return op.eval(arg1, null, vars);
    }

    @Override
    public String getPrintable() {
        return "(".concat(op.getOperator()).concat(arg1.getPrintable()).concat(")");
    }

    @Override
    public Expression differentiate(String x) {
        return null;
    }
}
