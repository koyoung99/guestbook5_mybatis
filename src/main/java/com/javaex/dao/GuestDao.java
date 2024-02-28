package com.javaex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestVo;

@Repository
public class GuestDao {
	// 필드
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	@Autowired
	private DataSource dataSource;

	@Autowired
	private SqlSession sqlSession;

	// 연결
	public void getConnection() {
		try {

			// 2. Connection 얻어오기
			conn = dataSource.getConnection();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 종료
	public void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 리스트
	public List<GuestVo> guestSelect() {

		List<GuestVo> guestList = sqlSession.selectList("guestbook.select");

		System.out.println(guestList);

		return guestList;
	}

	// 추가
	public int guestInsert(GuestVo guestVo) {

		int count = sqlSession.insert("guestbook.insert", guestVo);

		System.out.println(count);

		return count;
	}
	// 삭제

	public int guestDelete(GuestVo guestVo) {

		int count = sqlSession.delete("guestbook.delete", guestVo);
		System.out.println(guestVo);

		return count;
	}

}