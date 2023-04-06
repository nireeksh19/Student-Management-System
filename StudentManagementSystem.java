import java.sql.*;
import java.util.Scanner;
public class StudentManagementSystem {
    public static void main(String[] args) {
        try{
            //connection to database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","");
            System.out.println("Connection successful !!!!!");

            Scanner scanner = new Scanner(System.in);

            while(true){
                //Menu Displaying
                System.out.println("Select the option: ");
                System.out.println("1. Add the Student");
                System.out.println("2. Update the Student");
                System.out.println("3. Delete the Student");
                System.out.println("4. View All Students");
                System.out.println("5. Exit");

                //Read users choice
//                System.out.println("Please select Option from 1 to 4 ");
                int choice = scanner.nextInt();
                scanner.nextLine();
                //we need to consume the new line character before proceeding further
//                System.out.println("your choice is"+ choice);

                switch (choice){
                    case 1:
                        //adding a student
                        System.out.println("Enter the student name: ");
                        String name =scanner.nextLine();

                        System.out.println("Enter the student email: ");
                        String email =scanner.nextLine();

                        System.out.println("Enter the student phone number ");
                        String phoneNumber = scanner.nextLine();

                        String sql = "INSERT INTO student(name,email,phone_number) VALUES (?,?,?)";
                        PreparedStatement statement = conn.prepareStatement(sql);
                        statement.setString(1,name);
                        statement.setString(2,email);
                        statement.setString(3,phoneNumber);

                        statement.executeUpdate();

                        System.out.println("Student added successfully ");
                        break;

                    case 2:
                        //Update the user
                        System.out.println("Enter the student's id");
                        int id = scanner.nextInt();
                        scanner.nextLine();//consume newline character

                        System.out.println("Enter the student's name:");
                        name = scanner.nextLine();

                        System.out.println("Enter the student's email:");
                        email = scanner.nextLine();

                        System.out.println("Enter the student's phone number:");
                        phoneNumber = scanner.nextLine();

                        sql = "UPDATE student SET name=?, email=?, phone_number=? WHERE id=?";
                        statement = conn.prepareStatement(sql);
                        statement.setString(1, name);
                        statement.setString(2, email);
                        statement.setString(3, phoneNumber);
                        statement.setInt(4, id);
                        statement.executeUpdate();

                        System.out.println("Student updated successfully.");
                        break;

                    case 3:
                        //delete the student
                        System.out.println("Enter the student's id");
                        id = scanner.nextInt();
                        scanner.nextLine();//consume newline character

                        sql = "DELETE FROM student WHERE id=?";
                        statement = conn.prepareStatement(sql);
                        statement.setInt(1, id);
                        statement.executeUpdate();

                        System.out.println("Student deleted successfully.");
                        break;

                    case 4:
                        sql = "SELECT * FROM student";
                        statement = conn.prepareStatement(sql);
                        ResultSet result = statement.executeQuery();

                        System.out.println("ID"+"    "+"NAME"+"        "+"EMAIL"+"          "+"PHONE");

                        while (result.next()) {
                            int userId = result.getInt("id");
                            String userName = result.getString("name");
                            String userEmail = result.getString("email");
                            String userPhoneNumber = result.getString("phone_number");
                            System.out.println(userId + "\t" + userName + "\t" + userEmail + "\t" + userPhoneNumber);
                        }
                        break;
                    case 5:
                        // Exit the program
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice!! Choose between 1-5 only ..");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
