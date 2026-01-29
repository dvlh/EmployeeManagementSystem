
public class Designer extends Employee {

    public Designer(String name, int id, double salary) {
        super(name, id, salary);
    }


    @Override
    public double calculateBouns() {
        return getSalary() * 0.10;
    }
    
}
