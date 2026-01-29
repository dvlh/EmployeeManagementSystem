
import java.time.LocalDate;


public class Leave {
    
    private final Employee employee;
    private final LocalDate startDate;
    private final int daysOfLeave;

    public Leave(Employee employee, LocalDate startDate, int daysOfLeave) {
        this.employee = employee;
        this.startDate = startDate;
        this.daysOfLeave = daysOfLeave;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getDaysOfLeave() {
        return daysOfLeave;
    }
    
    
    
    public void displayLeaveDetails() {
        
    }
}
