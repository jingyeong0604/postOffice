package postoffice.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shinhan.dbutil.OracleUtil;


import postoffice.vo.PostVO;



public class PostDAO {
	
	static Connection conn;
	static Statement st;
	PreparedStatement pst; //?지원
	java.sql.CallableStatement cst;//sp지원
	static ResultSet rs; //결과값을 넣어야함
	int resultCount; //insert, update, delete건수
	
	public PostVO selectById(String postid) {
		//검증하는 sql 문
		PostVO postvo = null;
		String sql="select * from post where postid= "+postid;
		//List<PostVO> postList = new ArrayList<>();
		conn=OracleUtil.getConnection();//db연결
		try {
			st=conn.createStatement();//통로를 만들어야함.sql문장을 보내면 됨.
			rs = st.executeQuery(sql);//실행은 디비에서 하고 결과는 자바쪽으로 온것
			while(rs.next()) {//데이터를 읽어서 arraylist에 담음.
				postvo = makeEmp(rs);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}
		return postvo;
	}

	private PostVO makeEmp(ResultSet rs) throws SQLException {
		PostVO postvo =new PostVO();//값이 너무 많아서 직접 넣기가 복잡함
		
		postvo.setTypename(rs.getString("typename"));
		postvo.setDeliveryMethod(rs.getString("deliveryMethod"));
		postvo.setMemberid(rs.getString("memberid"));
		postvo.setPostid(rs.getString("postid"));
		postvo.setRecepientaddress(rs.getString("recepientaddress"));
		postvo.setSenddate(rs.getDate("senddate"));
		postvo.setRecepientid(rs.getString("recepientid"));
		
		return postvo;
	}
//    memberid varchar2(20),
//    recepientid varchar2(20),
//    recepientaddress varchar2(100),
//    senddate Date,
//    typename varchar2(20),
//    deliveryMethod varchar2(20),
//    postid varchar2(20) primary key,
	//우편물 접수
//	---------------- -------- ------------- 
//	MEMBERID                  VARCHAR2(20)  
//	RECEPIENTID               VARCHAR2(20)  
//	RECEPIENTADDRESS          VARCHAR2(100) 
//	SENDDATE                  DATE          
//	TYPENAME                  VARCHAR2(20)  
//	DELIVERYMETHOD            VARCHAR2(20)  
//	POSTID  
	public int addPost(String memberid,String recepientid,  String recepientaddress,String typename, String deliveryMethod) {
		String sql="""
				insert into post (memberid, recepientid, recepientaddress ,  typename,  deliveryMethod,postid, senddate)
				values (?,?,?,?,?,post_seq.nextval,sysdate) 
				""";
		conn = OracleUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, memberid);
			pst.setString(2, recepientid);
			pst.setString(3, recepientaddress);
			pst.setString(4, typename);
			pst.setString(5, deliveryMethod);
			//pst.setString(6, postid);
			//pst.setString(resultCount, sql);
			
			resultCount = pst.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleUtil.dbDisconnect(null, pst, conn);
		}
		
		return resultCount;
	}

	public static List<PostVO> selectByMemberId(String memId) {
		//검증하는 sql 문
		PostVO postvo = null;
		String sql="select * from post where memberid='"+memId + "'";
		
		List<PostVO> postlist = new ArrayList<>();
		conn=OracleUtil.getConnection();
		
		try {
			st=conn.createStatement();
			
			rs = st.executeQuery(sql);
			while(rs.next()) {
				PostVO post = makeEmp2(rs);
				postlist.add(post);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}
		return postlist;
	}
//	MEMBERID            VARCHAR2(20)  
//	RECEPIENTID         VARCHAR2(20)  
//	RECEPIENTADDRESS    VARCHAR2(100) 
//	SENDDATE            DATE          
//	TYPENAME            VARCHAR2(20)  
//	DELIVERYMETHOD      VARCHAR2(20)  
//	POSTID

	private static PostVO makeEmp2(ResultSet rs) throws SQLException {
		PostVO post = new PostVO();
		post.setDeliveryMethod(rs.getString("deliverymethod"));
		post.setMemberid(rs.getString("memberid"));
		post.setPostid("postid");
		post.setRecepientaddress("recepientid");
		post.setTypename("typename");
		//post.setSenddate("senddate");
		return post;
	}
}
