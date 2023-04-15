package lib;

public class TaxFunction {

    private static final int DEFAULT_TAX_DEDUCTIBLE = 54000000;
    private static final int MARRIED_TAX_DEDUCTIBLE = DEFAULT_TAX_DEDUCTIBLE + 4500000;
    private static final int CHILD_TAX_DEDUCTIBLE = 1500000;
    private static final int MAX_CHILDREN = 3;


    public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        if (numberOfMonthWorking > 12) {
            throw new IllegalArgumentException("Number of working months can not exceed 12");
        }
        if (numberOfChildren < 0) {
            throw new IllegalArgumentException("Number of children can not be negative");
        }
        
        int childDeductible = Math.min(numberOfChildren, MAX_CHILDREN) * CHILD_TAX_DEDUCTIBLE;
        int taxDeductible = DEFAULT_TAX_DEDUCTIBLE;
        if (isMarried) {
            taxDeductible = MARRIED_TAX_DEDUCTIBLE;
        }
        
        int taxableIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking - deductible - taxDeductible - childDeductible;
        int tax = (int) Math.round(0.05 * taxableIncome);
        return Math.max(tax, 0);
    }
    
}
