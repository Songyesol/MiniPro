package co.micol.minipro.member.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.micol.minipro.common.DAO;
import co.micol.minipro.common.DbInterface;
import co.micol.minipro.common.EmployeeVO;
import co.micol.minipro.member.service.MemberVo;
import oracle.jdbc.OracleTypes;

public class MemberDao extends DAO implements DbInterface<MemberVo> {
	private PreparedStatement psmt;
	private ResultSet rs;

	private final String SELECTLIST = "SELECT  * FROM MEMBER";
	private final String SELECTMEMBER = "SELECT  * FROM MEMBER WHERE MID=?";
	private final String LOGINMEMBER = "SELECT  * FROM MEMBER WHERE MID=? AND MPASSWORD=?";
	private final String INSERTMEMBER = "INSERT INTO MEMBER(MID,MNAME,MPASSWORD) VALUES(?,?,?)";
	private final String IDCHECK = "SELECT MID FROM MEMBER WHERE MID=?";
	
	@Override
	public ArrayList<MemberVo> selectList() {
		// TODO 전체 회원 리스트
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		MemberVo vo;
		
		try {
			psmt=conn.prepareStatement(SELECTLIST);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo=new MemberVo();
				vo.setmId(rs.getString("mid"));
				vo.setmName(rs.getString("mname"));
				vo.setmAuth(rs.getString("mauth"));
				list.add(vo);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public MemberVo select(MemberVo vo) {
		// TODO 한명의 레코드를 찾아오는 메서드 
		
		try {
			psmt=conn.prepareStatement(SELECTMEMBER);
			psmt.setString(1, vo.getmId());
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo.setmName(rs.getString("mname"));
				vo.setmAuth(rs.getString("mauth"));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return vo;
	}
	

	@Override
	public int insert(MemberVo vo) {
		// TODO 회원가입시 회원정보 DB에 저장하는 쿼리실행 
		int n=0;
		try {
			psmt=conn.prepareStatement(INSERTMEMBER);
			psmt.setString(1, vo.getmId());
			psmt.setString(2, vo.getmName());
			psmt.setString(3, vo.getmPassword());
			
			n=psmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		return n;
	}

	@Override
	public int update(MemberVo vo) {
		// TODO 권한, 패스워드만 변경
		String sql = null;
		int r=0;
		
		if(vo.getmPassword() != null) {
			sql = "UPDATE MEMBER SET  MPASSWORD=? WHERE MID=?"; //패스워드 변경구문
		} else if(vo.getmAuth() != null) {
			sql = "UPDATE MEMBER SET MAUTH = ? WHERE MID=?"; //권한변경			
		}

		try {
			psmt=conn.prepareStatement(sql);
			if(vo.getmPassword() != null) { //패스워드가 변경될때
				psmt.setString(1, vo.getmPassword());
			} else {
				psmt.setString(1, vo.getmAuth()); //권한변경
			}
			psmt.setString(2, vo.getmId());
			r = psmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return r;
	}

	@Override
	public int delete(MemberVo vo) {
		// TODO 회원한명 삭제 
		String sql = "DELETE FROM MEMBER WHERE MID=?";
		int r=0;
		try {
			psmt=conn.prepareStatement(sql);
			psmt.setString(1, vo.getmId());
			
			r = psmt.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public boolean isIdCheck(String id) { 
		//아이디 체크 메서드(true/false값 가져오는건 관례상 ID 붙임)
		//boolean을 쓰면 default가 참.
		boolean bool = true;
		try {
			psmt=conn.prepareStatement(IDCHECK);
			psmt.setString(1, id);
			
			rs = psmt.executeQuery();
			if(rs.next()) {
				bool=false;
			}
		}catch(SQLException e){
			e.printStackTrace(); //오류가 나면 콘솔창에 뿌려줘
		}finally {
			close();
		}
		
		return bool;
		
	}
	
	public MemberVo loginCheck(MemberVo vo) { 
		// TODO 로그인 체크 메서드
		
		try {
			psmt=conn.prepareStatement(LOGINMEMBER);
			psmt.setString(1, vo.getmId());
			psmt.setString(2, vo.getmPassword());
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				vo.setmName(rs.getString("mname"));
				vo.setmAuth(rs.getString("mauth"));
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return vo;
	}
	
	//프로시저 호출
	public EmployeeVO getSalaryInfo(int empId, int salary) {
		EmployeeVO vo =  new EmployeeVO();;
		try {
			CallableStatement csmt =conn.prepareCall("{ call SAL_HISTORY_PROC(?,?,?) }");
			csmt.setInt(1, empId);
			csmt.setInt(2, salary);
			csmt.registerOutParameter(3, OracleTypes.CURSOR); 
			csmt.execute();
			
			rs = (ResultSet) csmt.getObject(3);
			if(rs.next()) {
				vo= new EmployeeVO();
				vo.setEmail(rs.getString("email"));
				vo.setEmployeeId(rs.getInt("employee_id"));
				vo.setFirstName(rs.getString("first_name"));
				vo.setLastName(rs.getString("last_name"));
				vo.setHireDate(rs.getString("hire_date"));
				vo.setSalary(rs.getInt("salary"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return vo;
	}
	
	//paging
	public List<EmployeeVO> getPagingList(int page){
		List<EmployeeVO> list = new ArrayList<EmployeeVO>();
		String sql ="select b.rn, b.* "
				+ "from(select rownum rn, a.* "
				+ "        from (select * "
				+ "                  from employees "
				+ "                   order by employee_id) a) b "
				+ "where b.rn between ? and ? ";
		try {
			psmt=conn.prepareStatement(sql);
			int startCnt = 1 + ( page - 1 ) * 10;
			int endCnt = page * 10;
			psmt.setInt(1, startCnt);
			psmt.setInt(2, endCnt);
			rs=psmt.executeQuery();
			
			while(rs.next()) {
				EmployeeVO vo = new EmployeeVO();
				vo.setEmployeeId(rs.getInt("employee_id"));
				vo.setFirstName(rs.getString("first_name"));
				vo.setLastName(rs.getString("last_name"));
				vo.setEmail(rs.getString("email"));
				vo.setHireDate(rs.getString("hire_date"));
				vo.setSalary(rs.getInt("salary"));
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	
	public int getTotalCnt() {
		String sql = "SELECT COUNT(*) FROM EMPLOYEES";
		int totalCount = 0;
		try {
			psmt=conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			if(rs.next()) {
				totalCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return totalCount;
	}
	
	private void close() {
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();

		}catch(SQLException e){
			e.printStackTrace();
		}
	}

}
