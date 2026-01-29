
import java.time.LocalDate;


public interface Approver {
    
    public void approveLeave(Employee e, LocalDate startDate, int days);
    
    public void markEmployeeAsWorking(Employee e);
}
