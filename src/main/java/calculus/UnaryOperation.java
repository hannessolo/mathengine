package calculus;

enum UnaryOperation implements Operation{
    NEGATE {

        @Override
        public double eval(Expression a, Expression b, Identity[] vars) {
            return (-1) * a.eval(vars);
        }

        @Override
        public String getOperator() {
            return "-";
        }

    }, SIN {

        @Override
        public double eval(Expression a, Expression b, Identity[] vars) {
            return 0;
        }

        @Override
        public String getOperator() {
            return null;
        }

    }, COS {

        @Override
        public double eval(Expression a, Expression b, Identity[] vars) {
            return 0;
        }

        @Override
        public String getOperator() {
            return null;
        }

    }

}
