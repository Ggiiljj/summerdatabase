package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.PeopleInfoManagerZ;
import control.SchoolInfoManagerZ;
import control.SystemUserManager;
import model.PeopleInfo;
import model.SchoolInfo;
import util.BaseException;

public class ZSchoolInfoManager_Modify extends JDialog implements ActionListener {
	private SchoolInfo schoolinfo=null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	//private JLabel labelSchoolid = new JLabel("学校id：");
	private JLabel labelSchoolname= new JLabel("学校名：");
	private JLabel labelProvince= new JLabel("所属省份：");
	private JLabel labelCity= new JLabel("所属城市：");
	private JLabel labelSchooldesc= new JLabel("备注描述：");
	//private JTextField edtSchoolid = new JTextField(20);
	private JTextField edtSchoolname= new JTextField(20);
	private JTextField edtProvince= new JTextField(20);
	private JTextField edtCity= new JTextField(20);
	private JTextField edtSchooldesc= new JTextField(20);
	public ZSchoolInfoManager_Modify(JDialog f, String s, boolean b,SchoolInfo rt) {
		super(f, s, b);
		this.schoolinfo=rt;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		//workPane.add(labelSchoolid);
		//this.edtSchoolid.setText(rt.getSchoolid());
		//workPane.add(edtSchoolid);
		workPane.add(labelSchoolname);
		this.edtSchoolname.setText(rt.getSchoolname());
		workPane.add(edtSchoolname);
		workPane.add(labelProvince);
		this.edtProvince.setText(rt.getProvince());
		workPane.add(edtProvince);
		workPane.add(labelCity);
		this.edtCity.setText(rt.getCity());
		workPane.add(edtCity);
		workPane.add(labelSchooldesc);
		this.edtSchooldesc.setText(rt.getSchooldesc());
		workPane.add(edtSchooldesc);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 300);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				ZSchoolInfoManager_Modify.this.schoolinfo=null;
			}
		});
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			this.schoolinfo=null;
			return;
		}
		else if(e.getSource()==this.btnOk){
			SchoolInfo s=new SchoolInfo();
			//String Schoolid=this.edtSchoolid.getText();
			String Schoolname=this.edtSchoolname.getText();
			String Province=this.edtProvince.getText();
			String City=this.edtCity.getText();
			String Schooldesc=this.edtSchooldesc.getText();
//			int n=0;
//			try{
//				n=Integer.parseInt(this.edtLimited.getText());
//			}catch(Exception ex){
//				JOptionPane.showMessageDialog(null, this.edtLimited.getText()+"不是一个合法的整数","错误",JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			if(n<0 || n>100){
//				JOptionPane.showMessageDialog(null, "借阅限制必须在0-100之间","错误",JOptionPane.ERROR_MESSAGE);
//				return;
//			}
			
			
			s.setSchoolname(Schoolname);
			s.setSchoolid(this.schoolinfo.getSchoolid());
			s.setProvince(Province);
			s.setCity(City);
			s.setSchooldesc(Schooldesc);
			try {
				(new SchoolInfoManagerZ()).modifySchoolInfo(s,this.schoolinfo);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.schoolinfo=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}
	public SchoolInfo getSchoolInfo() {
		return schoolinfo;
	}
	
}
