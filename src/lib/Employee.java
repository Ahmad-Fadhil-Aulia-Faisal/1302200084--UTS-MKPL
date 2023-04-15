package lib;

import java.time.LocalDate;
import java.time.Month;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;
    private LocalDate joinedDate;
    private boolean isForeigner;
    private boolean isMale;
    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;
    private String spouseName;
    private String spouseIdNumber;
    private List<Child> children;
	
	public Employee(String employeeId, String firstName, String lastName, String idNumber, String address, LocalDate joinedDate, boolean isForeigner, boolean isMale) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.idNumber = idNumber;
        this.address = address;
        this.joinedDate = joinedDate;
        this.isForeigner = isForeigner;
        this.isMale = isMale;
        children = new ArrayList<>();
	}

	private static class Child {
        private String name;
        private String idNumber;
        public Child(String name, String idNumber) {
            this.name = name;
            this.idNumber = idNumber;
        }
    }
	
	public void setMonthlySalary(int grade) {	
		switch (grade) {
            case 1:
                monthlySalary = 3000000;
                break;
            case 2:
                monthlySalary = 5000000;
                break;
            case 3:
                monthlySalary = 7000000;
                break;
            default:
                throw new IllegalArgumentException("Invalid grade");
        }
        if (isForeigner) {
            monthlySalary = (int) (monthlySalary * 1.5);
        }
	}
	
	public void setAnnualDeductible(int deductible) {	
		this.annualDeductible = deductible;
	}
	
	public void setAdditionalIncome(int income) {	
		this.otherMonthlyIncome = income;
	}
	
	public void setSpouse(String spouseName, String spouseIdNumber) {
		this.spouseName = spouseName;
		this.spouseIdNumber = idNumber;
	}
	
	public void addChild(Child child) {
		children.add(child);
	}
	
	public int getAnnualIncomeTax() {
		int monthsWorkedThisYear = calculateMonthsWorkedThisYear();
        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthsWorkedThisYear, annualDeductible, spouseIdNumber.equals(""), children.size());
	}

	private int calculateMonthsWorkedThisYear() {
        LocalDate currentDate = LocalDate.now();
        if (currentDate.getYear() == joinedDate.getYear()) {
            return currentDate.getMonthValue() - joinedDate.getMonthValue();
        } else {
            return 12;
        }
    }
}
