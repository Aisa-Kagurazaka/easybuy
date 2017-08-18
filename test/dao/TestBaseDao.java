package dao;

import java.sql.Connection;

import org.junit.Test;

public class TestBaseDao {

	@Test
	public void testGetConnection() {
		Connection conn = new BaseDao().getConnection();
		System.out.println(conn);
		for (int i = 0; i < 115; i++) {
			if(i%11==0)
			System.out.println("UPDATE goods SET isOff=1 WHERE goodsId="+i +";");
		}
	}
	
}
