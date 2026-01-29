
import java.time.LocalDate;


public class Manager extends Employee implements Approver{

    public Manager(String name, int id, double salary) {
        super(name, id, salary);
    }

    @Override
    public double calculateBouns() {
        return getSalary() * 0.15;
    }
    
    @Override
    public void approveLeave(Employee e, LocalDate startDate, int days) {
        Leave leave = new Leave(e, startDate, days);
        
        e.addLeaveRecord(leave);
        e.setStateOnLeave();
    }
    
    @Override
    public void markEmployeeAsWorking(Employee e) {
        
        e.setStateWorking();
    }

}
