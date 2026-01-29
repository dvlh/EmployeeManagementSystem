import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;


public class EmployeeMangementSystem {
    
    private static ArrayList<Employee> employees = new ArrayList<>();
    static PrintWriter pw;

    public static void main(String[] args) throws FileNotFoundException {
        
        // Define input and output files
        File input = new File("input.txt");
        File output = new File("output.txt");
        
        // Create PrintWriter for writing to the output file
        pw = new PrintWriter(output);
        
        // Read input from the file
        Scanner in = new Scanner(input);
        EmployeeMangementSystem EMS = new EmployeeMangementSystem();
        
        while(in.hasNext()) {
            
            String line = in.nextLine();
            String[] parts = line.split(",");
            
            String command = parts[0].trim();
            
             // Process different commands based on the first part of the input line
            if(command.equalsIgnoreCase("Add_Manager")) {
                // Extract name, ID, and salary from the input
                String name = parts[1].trim();     
                int id = Integer.parseInt(parts[2].trim());
                double salary = Double.parseDouble(parts[3].trim());
                
                // Create a new Manager object and add it to the employee list
                Employee emp = new Manager(name, id, salary);
                EMS.addEmployee(emp);
                pw.println("Manager " + name + " added.");
            }
            if(command.equalsIgnoreCase("Add_Developer")) {
                // Extract name, ID, and salary from the input
                String name = parts[1].trim();
                int id = Integer.parseInt(parts[2].trim());
                double salary = Double.parseDouble(parts[3].trim());
                
                // Create a new Developer object and add it to the employee list
                Employee emp = new Developer(name, id, salary);
                EMS.addEmployee(emp);
                pw.println("Developer " + name + " added.");
            }
            if(command.equalsIgnoreCase("Add_Designer")) {
                // Extract name, ID, and salary from the input
                String name = parts[1].trim();
                int id = Integer.parseInt(parts[2].trim());
                double salary = Double.parseDouble(parts[3].trim());
                
                // Create a new Designer object and add it to the employee list
                Employee emp = new Designer(name, id, salary);
                EMS.addEmployee(emp);
                pw.println("Designer " + name + " added.");
            }
            if (command.equalsIgnoreCase("Give_Leave")) {
                // Extract manager ID, employee ID, start date, and number of days from the input
                int managerId = Integer.parseInt(parts[1].trim());
                int employeeId = Integer.parseInt(parts[2].trim());
                LocalDate startDate = LocalDate.parse(parts[3].trim());
                int days = Integer.parseInt(parts[4].trim());
                
                // Call the approveLeave method to approve the leave for the employee
                EMS.approveLeave(managerId, employeeId, startDate, days);
            }
            if(command.equalsIgnoreCase("Change_State")) {
                // Extract manager ID, employee ID, and state from the input
                int managerId = Integer.parseInt(parts[1].trim());
                int employeeId = Integer.parseInt(parts[2].trim());
                String state = parts[3].trim();
                
                // Call the markEmployeeAsWorking method to mark the employee as working
                EMS.markEmployeeAsWorking(managerId, employeeId);
            }
            if(command.equalsIgnoreCase("del_Employee")) {
                // Extract employee ID from the input
                int employeeId = Integer.parseInt(parts[1].trim());
                EMS.removeEmployee(employeeId);
            }
            if(command.equalsIgnoreCase("printAllEmployees")) {
                // Call the printAllEmployees method to get a formatted string of employee information
                pw.print("Name       ID   Salary       Bonus");
                pw.print("\n"+EMS.printAllEmployees());
            }
            if(command.equalsIgnoreCase("print_leave_Records")) {
                // Extract employee ID from the input
                int employeeId = Integer.parseInt(parts[1].trim());
                
                // Call the printEmployeeLeaveRecords method to get a string of leave records
                pw.println(EMS.printEmployeeLeaveRecords(employeeId));
            }
        }
        
        // Close the input and output files
        in.close();
        pw.close();
        pw.flush();
    }
    
    //------------------------------------------------------------------------------------------------
    
    public String addEmployee(Employee emp) {
        employees.add(emp);    // Add the employee to the employee list
        return "";
    }
    
    //------------------------------------------------------------------------------------------------
    
    public String removeEmployee(int employeeId) {    // Remove an employee from the system.
        
        Employee eObject = findEmployeeById(employeeId);  // Find the employee with the given ID in the employee list
        
        if(eObject != null) {   // If found, remove the employee from the employee list
            employees.remove(eObject);
            pw.println("Employee removed successfully.");
        }
        else {    // If not found, write an error message to the output file
            pw.println("Employee with ID " + employeeId + " not found.");
        }
        return "";
    }
    
    //------------------------------------------------------------------------------------------------
    
    public String approveLeave(int managerId, int employeeId, LocalDate startDate, int days) {
        
        // Find the manager and employee objects with the given IDs in the employee list
        Employee mObject = findEmployeeById(managerId);
        Employee eObject = findEmployeeById(employeeId);
        
        if(mObject == null || eObject == null || !(mObject instanceof Manager)) {    // If the manager or employee is not found, write an error message to the output file
            pw.println("Leave approval failed. Either manager ID is incorrect or employee does not exist.");
        }
        else if(eObject.getState().equals("Leave")) {    // If the employee is already on leave, write an error message to the output file
            pw.println("Cannot approve leave for " + eObject.getName() + " as they are already on leave.");
        }
        else {    // If all checks pass, call the approveLeave method of the Manager object to approve the leave for the employee
            ((Manager)mObject).approveLeave(eObject, startDate, days);
            pw.println("Leave approved for " + eObject.getName());
        }
        return "";
    }
    
    //------------------------------------------------------------------------------------------------
    
    private Employee findEmployeeById(int id) {
    for (int i = 0 ; i < employees.size() ; i++) {
        if (employees.get(i).getId() == id) {
            return employees.get(i);
        }
    }
    return null;
}
    //------------------------------------------------------------------------------------------------
    
    public String markEmployeeAsWorking(int managerId, int employeeId) {
        
        // Find the manager and employee objects with the given IDs in the employee list
        Employee mObject = findEmployeeById(managerId);
        Employee eObject = findEmployeeById(employeeId);
        
        if(mObject == null || eObject == null || !(mObject instanceof Manager)) {    // If the manager or employee is not found, write an error message to the output file
            pw.println("manager ID is incorrect or employee ID does not exist.");
        }
        else if(eObject.getState().equals("Working")) {    // If the employee is already marked as working or not on leave, write an error message to the output file
            pw.println("Cannot mark as working for " + eObject.getName() + " as they are already mark as working.");
        }
        else {    // If all checks pass, call the markEmployeeAsWorking method of the Manager object to mark the employee as working
            ((Manager)mObject).markEmployeeAsWorking(eObject);
            pw.println("Employee " + eObject.getName() + " is now marked as working.");
        }
        return "";
    }
    
    //------------------------------------------------------------------------------------------------
    
    // Create an empty string
    // Iterate over the employee list
    // For each employee, append their information to the string in a formatted manner
    // Return the formatted string
    public String printAllEmployees() {
        StringBuilder sb = new StringBuilder();

        for (Employee employee : employees) {
            sb.append(String.format("%-10s %-4d %-12.2f %-10.2f%n", employee.getName(), employee.getId(), employee.getSalary(), employee.calculateBouns()));
        }

        return sb.toString();
    }
    
    //------------------------------------------------------------------------------------------------
    
    public String printEmployeeLeaveRecords(int employeeId) {
        
        // Find the employee object with the given ID in the employee list
        Employee eObject = findEmployeeById(employeeId);
        
        String s = "Leave Records for " + eObject.getName() + ":\n" + "Start Date   Days of Leave\n";
        
        
        for (int i = 0 ; i < eObject.getLeaveRecords().size() ; i++) {
            Leave l = (Leave)eObject.getLeaveRecords().get(i);
            s += l.getStartDate() + "   " + l.getDaysOfLeave() + "\n";
        }
        
        // Return the string of leave records
        return s;
        }
    
}
