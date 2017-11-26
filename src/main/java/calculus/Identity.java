package calculus;

class Identity implements Expression {

    private double value;
    private String name;

    Identity(double a) {
        value = a;
    }

    Identity(String a) {
        name = a;
    }

    Identity(String a, double b) {
        value = b;
        name = a;
    }

    String getName() {
        return name;
    }

    double getValue() {
        return value;
    }

    @Override
    public double eval(Identity[] vars) {
        for (Identity id : vars) {

            if (id.getName().equals(this.name)) {
                return id.getValue();
            }

        }
        return value;
    }

    @Override
    public String getPrintable() {
        if (name == null) {
            return Double.toString(value);
        } else {
            return name;
        }

    }

    @Override
    public Expression differentiate(String x) {
        if (this.name.equals(x)) {
            return new Identity(1);
        } else {
            return new Identity(0);
        }

    }
}
