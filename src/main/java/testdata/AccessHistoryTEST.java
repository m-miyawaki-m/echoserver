package testdata;

import java.sql.Timestamp;

import echoserver.AccessHistoryDAO;
import echoserver.AccessHistoryDTO;

public class AccessHistoryTEST {
	public void AccessHistoryTESTData() {
	    // テストデータの作成
	    int connectionId = 1;
	    int userId = 100;
	    Timestamp loginTime = new Timestamp(System.currentTimeMillis());
	    Timestamp logoutTime = new Timestamp(System.currentTimeMillis());
	    String sourceIp = "192.168.0.1";
	    String status = "SUCCESS";

	    // AccessHistoryDAOインスタンスを作成
	    AccessHistoryDAO accessHistoryDAO = new AccessHistoryDAO("/properties/database.properties");

	    // AccessHistoryDTOインスタンスの作成
	    AccessHistoryDTO accessHistory = new AccessHistoryDTO(connectionId, userId, loginTime, logoutTime, sourceIp, status);

	    // アクセス履歴を保存
	    accessHistoryDAO.saveAccessHistory(accessHistory);
	    
	}
}
