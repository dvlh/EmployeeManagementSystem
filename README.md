# 🏢 Employee Management System (EMS)
A Robust Java-based Organizational Solution
🌟 Overview
This project is a high-performance management tool designed to bridge the gap between human resource needs and automated data processing. It manages diverse employee roles—Managers, Developers, and Designers—using advanced Object-Oriented principles.

The system doesn't just store names; it handles automated payroll calculations, leave history tracking, and real-time status monitoring via a command-driven interface.

🛠️ Technical Architecture
Core Logic & OOP Implementation

Abstraction: The Employee class serves as a blueprint, ensuring every staff member has a name, ID, and salary while keeping the specific bonus logic flexible.

Polymorphism: The system automatically calculates bonuses based on the specific job type:


Developer: 12% bonus.


Designer: 10% bonus.


Manager: 15% bonus.


Interfaces: The Approver interface is used by the Manager class to handle leave requests and status updates, ensuring clean, modular code.

Data Handling

Input Processing: Reads from input.txt using a Scanner to parse comma-separated commands.


Reporting: Generates a professional output.txt report summarizing all transactions and current staff records
