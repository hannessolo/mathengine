package calculus;

enum BinaryOperation implements Operation{
    ADD {
       public double eval (Expression a, Expression b, Identity[] vars) {
           return a.eval(vars) + b.eval(vars);
       }
       public String getOperator() {
           return "+";
        }
    }, SUBTRACT {
        public double eval (Expression a, Expression b, Identity[] vars) {
            return a.eval(vars) - b.eval(vars);
        }
        public String getOperator() {
            return "-";
        }
    }, MULTIPLY {
        public double eval (Expression a, Expression b, Identity[] vars) {
            return a.eval(vars) * b.eval(vars);
        }
        public String getOperator() {
            return "*";
        }
    }, DIVIDE {
        public double eval (Expression a, Expression b, Identity[] vars) {
            return a.eval(vars) / b.eval(vars);
        }
        public String getOperator() {
            return "/";
        }
    }, POWER {
        public double eval (Expression a, Expression b, Identity[] vars) {
            return Math.pow(a.eval(vars), b.eval(vars));
        }
        public String getOperator() { return "^"; }
    };
}
