package echoserver;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class AccessHistoryDAO {
    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public AccessHistoryDAO(String propertiesFilePath) {
        loadDatabaseProperties(propertiesFilePath);
    }

    private void loadDatabaseProperties(String propertiesFilePath) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(propertiesFilePath)) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        dbUrl = properties.getProperty("dbUrl");
        dbUser = properties.getProperty("dbUser");
        dbPassword = properties.getProperty("dbPassword");
    }
    
    
    // アクセス履歴を保存するメソッド（DTOを利用）
    public void saveAccessHistory(AccessHistoryDTO dto) {
        String sql = "INSERT INTO TESTUSER01.ACCESSHISTORY (CONNECTION_ID, USER_ID, LOGIN_TIME, LOGOUT_TIME, SOURCE_IP, STATUS) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, dto.getConnectionId());
            pstmt.setInt(2, dto.getUserId());
            pstmt.setTimestamp(3, dto.getLoginTime());
            pstmt.setTimestamp(4, dto.getLogoutTime());
            pstmt.setString(5, dto.getSourceIp());
            pstmt.setString(6, dto.getStatus());

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
