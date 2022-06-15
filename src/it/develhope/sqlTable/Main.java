package it.develhope.sqlTable;
/**
 * This class creates a connection with newdb
 * and then it creates a new table and populates it
 *
 * @author Tania Ielpo
 */

import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import static java.sql.DriverManager.getConnection;

public class Main {

    public static void main(String[] args) {

        try {

            Connection connection = getConnection(
                    "jdbc:mysql://localhost:3306/newdb",
                    "developer",
                    "Developer123");

            Statement statement= connection.createStatement();

            createTable(statement, "students");

            insertInTable(statement,"Ielpo","Tania");
            insertInTable(statement,"Bianchi","Mario");
            insertInTable(statement,"Rossi","Giulia");
            insertInTable(statement,"Verdi","Giuseppe");

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    /**
     * inserts a new record in a table
     * @param statement
     * @param lastName
     * @param firstName
     * @throws SQLException
     */

    private static void insertInTable(@NotNull Statement statement, String lastName, String firstName)
            throws SQLException{

        statement.executeUpdate("INSERT INTO students " +
                "(last_name, first_name) " +
                "VALUES ('"+lastName +"','"+firstName+"')");
    }

    /**
     * creates a new table if it doesn't exist
     * @param statement
     * @param tableName
     * @throws SQLException
     */

    private static void createTable(@NotNull Statement statement, String tableName)
            throws SQLException {

        //statement.executeUpdate ("DROP TABLE students" );
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS students " +
                "(student_id INTEGER(10) primary key NOT NULL auto_increment," +
                "last_name VARCHAR(30)," +
                "first_name VARCHAR(30))");
    }


}
