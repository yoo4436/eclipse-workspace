package tw.brad.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tw.brad.apis.Gift;

public class GiftDAO {
	private static final String url ="jdbc:mysql://localhost:3306/iii";
	private static final String user = "root";
	private static final String pw = "root";
	
	private static final String sql_find_all = """
			select id , name , feature , addr , tel, picurl
			from gift
			order by id
			LIMIT ?, ?
			""";

	private static final String sql_query_key = """
			select id , name , feature , addr , tel, picurl
			from gift
			where name like ? or feature like ? or addr like ? or tel like ?
			order by addr
			""";


	private int page, rpp;
	
	public GiftDAO() {}
	
	public GiftDAO(int page,int rpp){
		this.page = page;
		this.rpp = rpp;
	}
	
	public List<Gift> findAll() {
		System.out.println("INININI");
		List<Gift> gifts = new ArrayList<Gift>();
		try(Connection conn = DriverManager.getConnection(url, user, pw);
				PreparedStatement pstmt = conn.prepareStatement(sql_find_all);
				){
			System.out.println("OK333");
			pstmt.setInt(1, (page-1)*rpp);
			pstmt.setInt(2, rpp);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Gift gift = new Gift();
				gift.setId(rs.getLong("id"));
				gift.setName(rs.getString("name"));
				gift.setFeature(rs.getString("feature"));
				gift.setAddr(rs.getString("addr"));
				gift.setTel(rs.getString("tel"));
				gift.setPicurl(rs.getString("picurl"));
				gifts.add(gift);
			}
			rs.close();
		}catch (Exception e){
			System.out.println(e);
		}
		return gifts;
	}
	
	public List<Gift> search(String keyword) throws Exception {
		String k = (keyword == null) ? "": keyword.trim();
		String like = "%" + k + "%";
		List<Gift> gifts = new ArrayList<Gift>();
		try (Connection conn = DriverManager.getConnection(url, user, pw);
				PreparedStatement pstmt = conn.prepareStatement(sql_query_key)){
			pstmt.setString(1, like);
			pstmt.setString(2, like);
			pstmt.setString(3, like);
			pstmt.setString(4, like);
			
			try (ResultSet rs = pstmt.executeQuery()){
				while (rs.next()) {
					Gift gift = new Gift();
					gifts.add(gift);
					
					gift.setId(rs.getLong("id"));
					gift.setName(rs.getString("name"));
					gift.setFeature(rs.getString("feature"));
					gift.setAddr(rs.getString("addr"));
					gift.setTel(rs.getString("tel"));
					gift.setPicurl(rs.getString("picurl"));
				}
			}
			
		} 
		
		
		return gifts;
	}
}
