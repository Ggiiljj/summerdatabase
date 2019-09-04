package control;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import model.EnrollPlan;
import model.MajorInfo;
import util.BaseException;
import util.BusinessException;
import util.DBUtil;
import util.DbException;

public class EnrollPlanManagerZ {
	public List<EnrollPlan> searchEnrolls(String keyword)throws BaseException{
		List<EnrollPlan> result=new ArrayList<EnrollPlan>();
		Connection conn=null;
		try {
			conn=(Connection) DBUtil.getConnection();
			String sql="select enroll_plan.*,school_info.schoolname,major_info.majorname from enroll_plan,school_info,major_info where enroll_plan.enrollid=major_info.majorid and enroll_plan.schoolid=school_info.schoolid";
			if(keyword!=null && !"".equals(keyword))
				sql+=" and (major_info.majorname like ? or enroll_plan.enrollid  like ? or school_info.schoolname=? or enroll_plan.xuansubjects=?)";
			sql+=" order by enroll_plan.enrollid";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			if(keyword!=null && !"".equals(keyword)){
				pst.setString(1, "%"+keyword+"%");
				pst.setString(2, "%"+keyword+"%");
				pst.setString(3, "%"+keyword+"%");
				pst.setString(4, "%"+keyword+"%");
			}
				
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()){
				EnrollPlan r=new EnrollPlan();
				r.setEnrollid(rs.getString(1));
				r.setEnrollyear(rs.getInt(2));
				r.setXuansubjects(rs.getString(3));
				r.setPlannum(rs.getInt(4));
				r.setTuition(rs.getDouble(5));
				r.setOtherrequire(rs.getString(6));
				r.setSchoolid(rs.getString(7));
				r.setSchoolname(rs.getString(8));
				r.setEnrollname(rs.getString(9));
				result.add(r);
			}
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
		return result;
		
	}
	
public  void createEnrollPlan(EnrollPlan b) throws BaseException{
		
		
//		if(b.getBarcode()==null || "".equals(b.getBarcode()) || b.getBarcode().length()>20){
//			throw new BusinessException("条码必须是1-20个字");
//		}
//		if(b.getBookname()==null || "".equals(b.getBookname()) || b.getBookname().length()>50){
//			throw new BusinessException("图书名称必须是1-50个字");
//		}
		Connection conn=null;
		try {
			conn=(Connection) DBUtil.getConnection();
			String sql="select * from school_info where schoolid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, b.getSchoolid());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("请先创建学校信息");
			rs.close();
			pst.close();
		    sql="select * from major_info where majorid=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, b.getEnrollid());
			rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("请先创建专业信息");
			rs.close();
			pst.close();
			sql="select * from enroll_plan where enrollid=? and schoolid=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, b.getEnrollid());
			pst.setString(2, b.getSchoolid());
			rs=pst.executeQuery();
			if(rs.next()) throw new BusinessException("该专业的招生计划表已经存在");
			rs.close();
			pst.close();
     		sql="insert into enroll_plan(enrollid,enrollyear,xuansubjects,plannum,tuition,otherrequire,schoolid) values(?,?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1, b.getEnrollid());
			pst.setInt(2, b.getEnrollyear());
			pst.setString(3, b.getXuansubjects());
			pst.setInt(4, b.getPlannum());
			pst.setDouble(5, b.getTuition());
			pst.setString(6, b.getOtherrequire());
			pst.setString(7, b.getSchoolid());
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
public void modifyEnrollPlan(EnrollPlan rt,EnrollPlan tem)throws BaseException{
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
			String sql="select * from school_info where schoolid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, rt.getSchoolid());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("请先创建学校信息");
			rs.close();
			pst.close();
		    sql="select * from major_info where majorid=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, rt.getEnrollid());
			rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("请先创建专业信息");
			rs.close();
			pst.close();
			if((!rt.getEnrollid().equals(tem.getEnrollid()))&&(!rt.getSchoolid().equals(tem.getSchoolid())))
			{
				sql="select * from enroll_plan where enrollid=? and schoolid=? ";
				pst=conn.prepareStatement(sql);
				pst.setString(1, rt.getEnrollid());
				pst.setString(2, rt.getSchoolid());
				rs=pst.executeQuery();
				if(rs.next()) throw new BusinessException("该专业的招生计划表已经存在");
				rs.close();
				pst.close();
			}
			sql="update enroll_plan set enrollid=?,enrollyear=?,xuansubjects=?,plannum=?,tuition=?,otherrequire=?,schoolid=? where enrollid=?and schoolid=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, rt.getEnrollid());
			pst.setInt(2, rt.getEnrollyear());
			pst.setString(3, rt.getXuansubjects());
			pst.setInt(4, rt.getPlannum());
			pst.setDouble(5, rt.getTuition());
			pst.setString(6, rt.getOtherrequire());
			pst.setString(7, rt.getSchoolid());
			pst.setString(8, tem.getEnrollid());
			pst.setString(9, tem.getSchoolid());
			pst.execute();
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
public void removeenroll(String enrollid)throws BaseException{
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
			conn.setAutoCommit(false); 
			System.out.print(enrollid);
			String sql="delete from enroll_plan where enrollid=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, enrollid);
			pst.execute();
            pst.close();
            sql="delete from recommend_info where majorid=?";
            pst=conn.prepareStatement(sql);
            pst.setString(1, enrollid);
            pst.execute();
            pst.close();
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
