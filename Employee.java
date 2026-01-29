
import java.util.ArrayList;


abstract class Employee {
    private String name;
    private int id;
    private double salary;
    private String state = "";
    private ArrayList<Leave> leaveRecords = new ArrayList<>();

    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Employee{" + "name=" + name + ", id=" + id + ", salary=" + salary + ", state=" + state + '}';
    }
    
    public void setStateOnLeave() {
        this.state = "Leave";
    }
    
    public void addLeaveRecord(Leave leave) {
        leaveRecords.add(leave);
    }
    
    public void setStateWorking() {
        this.state = "Working";
    }
    
    public ArrayList getLeaveRecords() {
        return leaveRecords;
    }
    
    
    
    public abstract double calculateBouns();
    
}
