package bot.bot.database;
import java.sql.*;

public class Database {
    private Connection conn;
    private Statement stmt;

    // Create a new database or use an existing one if present
    public Database() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:mydatabase.db");
            stmt = conn.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS members " +
                    "(uuid TEXT NULL, " +
                    "discord TEXT NOT NULL)";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // Add a new member to the database
    public void addMember(String uuid, String discord) {
        try {
            String sql = "INSERT INTO members (uuid, discord) " +
                    "VALUES ('" + uuid + "', '" + discord + "')";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // Delete a member from the database by user
    public void deleteMember(String uuid) {
        try {
            String sql = "DELETE FROM members WHERE uuid = '" + uuid + "'";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // Search for a member in the database by name
    public String searchMemberByName(String discord) {
        String sum = null;
        try {
            String sql = "SELECT uuid, discord FROM members WHERE discord = '" + discord + "'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Uuid: " + rs.getString("uuid") + "\tDiscord: " + rs.getString("discord"));
                sum = rs.getString("uuid");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return sum;
    }

    public String searchMemberById(String uuid) {
        String sum = null;
        try {
            String sql = "SELECT uuid, discord FROM members WHERE uuid = '" + uuid + "'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Uuid: " + rs.getString("uuid") + "\tDiscord: " + rs.getString("discord"));
                sum = rs.getString("discord");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return sum;
    }

    // Display all members in the database
    public void displayAllMembers() {
        try {
            String sql = "SELECT uuid, discord FROM members";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("Uuid: " + rs.getString("uuid") + "\tDiscord: " + rs.getString("discord"));
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    // Close the database connection
    public void close() {
        try {
            conn.close();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
