package Model.Entities;

public class Employee {
    private String employeeId;
    private String userId;
    private String position;
    private double salary;

    public Employee(String employeeId, String userId, String position, double salary) {
        this.employeeId = employeeId;
        this.userId = userId;
        this.position = position;
        this.salary = salary;
    }

    // Getters y setters
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }
}
