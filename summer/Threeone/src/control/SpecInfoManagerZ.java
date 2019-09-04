package control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.PeopleInfo;
import model.SchoolInfo;
import model.SpecInfo;
import model.XuantestInfo;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class SpecInfoManagerZ {

	public List<SpecInfo> loadSpec(String userid)throws BaseException{
		Connection conn=null;
		try {
			List<SpecInfo> result=new ArrayList<SpecInfo>();
			conn=(Connection) DBUtil.getConnection();
			String sql="select * from spec_info where peopleid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,userid);
			java.sql.ResultSet rs=pst.executeQuery();
			//if(!rs.next()) throw new BusinessException("个人信息不 存在");
			while(rs.next())
			{
				SpecInfo u=new SpecInfo();
				u.setPeopleid(rs.getString(1));
				u.setSpecclass(rs.getString(2));
				u.setSpecgrade(rs.getString(3));
				u.setSpecdec(rs.getString(4));
				u.setSpecprove(rs.getString(5));
				result.add(u);
			}
			rs.close();
			pst.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
	public List<SpecInfo> loadASpec(String keyword)throws BaseException{
		Connection conn=null;
		try {
			List<SpecInfo> result=new ArrayList<SpecInfo>();
			conn=(Connection) DBUtil.getConnection();
			String sql="select * from spec_info ";
			if(keyword!=null && !"".equals(keyword))
				sql+=" where (peopleid like ? or specclass like ? or specdec like ?)";
			sql+=" order by peopleid";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			if(keyword!=null && !"".equals(keyword)){
				pst.setString(1, "%"+keyword+"%");
				pst.setString(2, "%"+keyword+"%");
				pst.setString(3, "%"+keyword+"%");
			}
			java.sql.ResultSet rs=pst.executeQuery();
			//if(!rs.next()) throw new BusinessException("个人信息不 存在");
			while(rs.next())
			{
				SpecInfo u=new SpecInfo();
				u.setPeopleid(rs.getString(1));
				u.setSpecclass(rs.getString(2));
				u.setSpecgrade(rs.getString(3));
				u.setSpecdec(rs.getString(4));
				u.setSpecprove(rs.getString(5));
				result.add(u);
			}
			rs.close();
			pst.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
public  void createSpecInfo(SpecInfo b) throws BaseException{
		
		
//		if(b.getBarcode()==null || "".equals(b.getBarcode()) || b.getBarcode().length()>20){
//			throw new BusinessException("条码必须是1-20个字");
//		}
//		if(b.getBookname()==null || "".equals(b.getBookname()) || b.getBookname().length()>50){
//			throw new BusinessException("图书名称必须是1-50个字");
//		}
		Connection conn=null;
		try {
			conn=(Connection) DBUtil.getConnection();
			String sql="select * from people_info where peopleid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, b.getPeopleid());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("该学生信息不存在");
			rs.close();
			pst.close();
			 sql="insert into spec_info(peopleid,specclass,specgrade,specdec,specprove) values(?,?,?,?,?)";
			 pst=conn.prepareStatement(sql);
			pst.setString(1, b.getPeopleid());
			pst.setString(2, b.getSpecclass());
			pst.setString(3, b.getSpecgrade());
			pst.setString(4, b.getSpecdec());
			pst.setString(5, b.getSpecprove());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}
public void modifySpecInfo(SpecInfo rt,SpecInfo gg)throws BaseException{
//	if(rt.getReaderTypeId()<=0){
//		throw new BusinessException("读者类别ID必须是大于0的整数");
//	}
//	if(rt.getReaderTypeName()==null || "".equals(rt.getReaderTypeName()) || rt.getReaderTypeName().length()>20){
//			throw new BusinessException("读者类别名称必须是1-20个字");
//		}
//		if(rt.getLendBookLimitted()<0 || rt.getLendBookLimitted()>100){
//			throw new BusinessException("借阅图书数量必须在0-100之间");
//		}
		Connection conn=null;
		try {
			conn=(Connection) DBUtil.getConnection();
			String sql="select * from people_info where peopleid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, rt.getPeopleid());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("请先建立个人信息");
			rs.close();
            pst.close();
			sql="update spec_info set specclass=?,specgrade=?,specdec=? where peopleid=? and specprove=?";
			conn.setAutoCommit(false); 
			pst=conn.prepareStatement(sql);
			pst.setString(1, rt.getSpecclass());
			pst.setString(2, rt.getSpecgrade());
			pst.setString(3, rt.getSpecdec());
			pst.setString(4, gg.getPeopleid());
			pst.setString(5, gg.getSpecprove());
			pst.execute();
			conn.setAutoCommit(true); 
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}
			catch(SQLException e1)
			{
				e1.printStackTrace();
			}
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
}

public void DeleteSpecInfo(String peopleid,String specprove)throws BaseException{
//	if(rt.getReaderTypeId()<=0){
//		throw new BusinessException("读者类别ID必须是大于0的整数");
//	}
//	if(rt.getReaderTypeName()==null || "".equals(rt.getReaderTypeName()) || rt.getReaderTypeName().length()>20){
//			throw new BusinessException("读者类别名称必须是1-20个字");
//		}
//		if(rt.getLendBookLimitted()<0 || rt.getLendBookLimitted()>100){
//			throw new BusinessException("借阅图书数量必须在0-100之间");
//		}
		Connection conn=null;
		try {
			conn=(Connection) DBUtil.getConnection();
			String sql="select * from people_info where peopleid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, peopleid);
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("请先建立个人信息");
			rs.close();
            pst.close();
			sql="delete from spec_info where peopleid=? and specprove=?";
			conn.setAutoCommit(false); 
			pst=conn.prepareStatement(sql);
			pst.setString(1, peopleid);
			pst.setString(2, specprove);
			pst.execute();
			conn.setAutoCommit(true); 
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			}
			catch(SQLException e1)
			{
				e1.printStackTrace();
			}
			throw new DbException(e);
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
}
}
