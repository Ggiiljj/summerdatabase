package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.PeopleInfoManagerZ;
import control.RecommendInfoZ;
import control.SystemUserManager;
import model.PeopleInfo;
import model.XuetestInfo;
import util.BaseException;
public class XuetestInfoManager_Modify extends JDialog implements ActionListener {
	private XuetestInfo xuetestinfo=null;
	String subbname;
	String testtid;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	 JTextField edtx = new JTextField(20);
	public XuetestInfoManager_Modify(JDialog f, String s, boolean b,XuetestInfo rt) {
		super(f, s, b);
		this.xuetestinfo=rt;
		String subname=xuetestinfo.getTestname();
		this.subbname=subname;
		this.testtid=xuetestinfo.getTestid();
		int grade=xuetestinfo.getGrade();
		JLabel labelxx= new JLabel(subname+"成绩：");
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelxx);
		this.xuetestinfo.setGrade(rt.getGrade());
		workPane.add(edtx);
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
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			this.xuetestinfo=null;
			return;
		}
		else if(e.getSource()==this.btnOk){
			SystemUserManager s=new SystemUserManager();
			int gradex= Integer.parseInt(this.edtx.getText());
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
			this.xuetestinfo=new XuetestInfo();
			this.xuetestinfo.setGrade(gradex);
			this.xuetestinfo.setPeopleid(SystemUserManager.currentUser.getPeopleid());
			this.xuetestinfo.setTestname(this.subbname);
			this.xuetestinfo.setTestid(testtid);
			try {
				(new PeopleInfoManagerZ()).modifyXuetestInfo(this.xuetestinfo);
				(new RecommendInfoZ()).removePRecommend(SystemUserManager.currentUser.getPeopleid());

				this.setVisible(false);
			} catch (BaseException e1) {
				this.xuetestinfo=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	public XuetestInfo getXuetestInfo() {
		return xuetestinfo;
	}
	
}
