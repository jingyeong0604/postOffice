package postoffice.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.shinhan.dbutil.OracleUtil;

import postoffice.vo.MemberVO;

public class MemberDAO {
	Connection conn;
	Statement st;
	PreparedStatement pst;
	CallableStatement cst;
	ResultSet rs;
	int resultCount;
	//conn : 데이터베이스에 접근하게 해주는 하나의 객체

	//rs : 어떠한 정보를 담을 수 있는 하나의 객체
	
	public int memLogin(String memId, String pass) {
		String sql = "select password from member where memberid=?";
		conn = OracleUtil.getConnection(); //db연결
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, memId);
			rs=pst.executeQuery();
			//st=conn.createStatement();//통로를 만들어야함.sql문장을 보내면 됨.
			
			//resultCount = pst.executeQuery(sql);//실행은 디비에서 하고 결과는 자바쪽으로 온것			
			while(rs.next()) {//데이터를 읽어서 arraylist에 담음.
				if(rs.getString(1).equals(pass)) return 1;
				else return 0;
				
			}
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}
		return -2;
	}
	
	
	public int memberInsert(MemberVO mem) {
		String sql="""
				insert into member values(?,?,?,?,?,?)
				""";
		conn= OracleUtil.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, mem.getName());
			pst.setString(2, mem.getContact());
			pst.setString(3, mem.getAddress());
			pst.setString(4, mem.getEmail());
			pst.setString(6, mem.getPassword());
			pst.setString(5, mem.getMemberid());
			
			resultCount =pst.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			OracleUtil.dbDisconnect(null, pst, conn);
		}
		return resultCount;
	}
	
	public MemberVO selectById(MemberVO id) {
		MemberVO mem=null;
		String sql="select * from member where memberid="+id;
		conn = OracleUtil.getConnection(); //db연결
		
		try {
			st=conn.createStatement();
			rs = st.executeQuery(sql);			
			while(rs.next()) {
				id = makeEmp(rs);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleUtil.dbDisconnect(rs, st, conn);
		}
		return mem;
	}

	private MemberVO makeEmp(ResultSet rs) throws SQLException {
		MemberVO mem = new MemberVO();
		
		mem.setMemberid(rs.getString("memberid"));
		mem.setPassword(rs.getString("password"));
		
		return mem;
	}

	public int memUpdate(MemberVO mem) {
		String sql="""
				update member
				set name=?, contact=?, address=?,
				email=?, password=? 
				where memberid=?
				
				""";
		conn= OracleUtil.getConnection();
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, mem.getName());
			pst.setString(2, mem.getContact());
			pst.setString(3, mem.getAddress());
			pst.setString(4, mem.getEmail());
			pst.setString(5, mem.getPassword());
			pst.setString(6, mem.getMemberid());
			
			resultCount =pst.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			OracleUtil.dbDisconnect(null, pst, conn);
		}
		return resultCount;
	}


	public int memberDelete(String memId) {
		String sql ="""
				delete from member
				where memberid=?
				""";
		
		conn = OracleUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, memId);
			resultCount =pst.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			OracleUtil.dbDisconnect(null, pst, conn);
		}
		return resultCount; 
	}
	}
