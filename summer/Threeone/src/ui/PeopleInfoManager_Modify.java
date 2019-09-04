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
import util.BaseException;
public class PeopleInfoManager_Modify extends JDialog implements ActionListener {
	private PeopleInfo peopleinfo=null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelTestid = new JLabel("准考证号：");
	private JLabel labelSex= new JLabel("性别：");
	private JLabel labelPhonenum= new JLabel("电话：");
	private JLabel labelMschoolname = new JLabel("中学：");
	private JLabel labelProvince = new JLabel("省份：");
	private JTextField edtTestid = new JTextField(20);
	private JTextField edtSex = new JTextField(20);
	private JTextField edtPhonenum = new JTextField(20);
	private JTextField edtMschoolname = new JTextField(20);
	private JTextField edtProvince = new JTextField(20);
	public PeopleInfoManager_Modify(JDialog f, String s, boolean b,PeopleInfo rt) {
		super(f, s, b);
		this.peopleinfo=rt;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelTestid);
		this.edtTestid.setText(rt.getTestid());
		workPane.add(edtTestid);
		workPane.add(labelSex);
		this.edtSex.setText(rt.getSex());
		workPane.add(edtSex);
		workPane.add(labelPhonenum);
		this.edtPhonenum.setText(rt.getPhonenum());
		workPane.add(edtPhonenum);
		workPane.add(labelMschoolname);
		this.edtMschoolname.setText(rt.getMschoolname());
		workPane.add(edtMschoolname);
		workPane.add(labelProvince);
		this.edtProvince.setText(rt.getProvince());
		workPane.add(edtProvince);
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
				PeopleInfoManager_Modify.this.peopleinfo=null;
			}
		});
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			this.peopleinfo=null;
			return;
		}
		else if(e.getSource()==this.btnOk){
			SystemUserManager s=new SystemUserManager();
			String Testid=this.edtTestid.getText();
			String Sex=this.edtSex.getText();
			String Phonenum=this.edtPhonenum.getText();
			String Mschoolname=this.edtMschoolname.getText();
			String Province=this.edtProvince.getText();
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
			this.peopleinfo=new PeopleInfo();
			this.peopleinfo.setMschoolname(Mschoolname);
			this.peopleinfo.setPhonenum(Phonenum);
			this.peopleinfo.setProvince(Province);
			this.peopleinfo.setPeopleid(SystemUserManager.currentUser.getPeopleid());
			this.peopleinfo.setPeoplename(SystemUserManager.currentUser.getPeoplename());
			this.peopleinfo.setSex(Sex);
			this.peopleinfo.setTestid(Testid);
			try {
				(new PeopleInfoManagerZ()).modifyPeopleInfo(this.peopleinfo);
				(new RecommendInfoZ()).removePRecommend(this.peopleinfo.getPeopleid());
				this.setVisible(false);
			} catch (BaseException e1) {
				this.peopleinfo=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	public PeopleInfo getReadertype() {
		return peopleinfo;
	}
	
}
