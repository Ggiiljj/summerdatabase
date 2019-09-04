package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.PeopleInfoManagerZ;
import control.SystemUserManager;
import model.PeopleInfo;
import util.BaseException;

public class PeopleInfoManager_Add extends JDialog implements ActionListener{
	private PeopleInfo peopleinfo=null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");
	private JLabel labelTestid = new JLabel("׼��֤�ţ�");
	private JLabel labelSex= new JLabel("�Ա�");
	private JLabel labelPhonenum= new JLabel("�绰��");
	private JLabel labelMschoolname = new JLabel("��ѧ��");
	private JLabel labelProvince = new JLabel("ʡ�ݣ�");
	private JTextField edtTestid = new JTextField(20);
	private JTextField edtSex = new JTextField(20);
	private JTextField edtPhonenum = new JTextField(20);
	private JTextField edtMschoolname = new JTextField(20);
	private JTextField edtProvince = new JTextField(20);
	public PeopleInfoManager_Add(JDialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelTestid);
		workPane.add(edtTestid);
		workPane.add(labelSex);
		workPane.add(edtSex);
		workPane.add(labelPhonenum);
		workPane.add(edtPhonenum);
		workPane.add(labelMschoolname);
		workPane.add(edtMschoolname);
		workPane.add(labelProvince);
		workPane.add(edtProvince);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 300);
		// ��Ļ������ʾ
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
//				JOptionPane.showMessageDialog(null, this.edtLimited.getText()+"����һ���Ϸ�������","����",JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			if(n<0 || n>100){
//				JOptionPane.showMessageDialog(null, "�������Ʊ�����0-100֮��","����",JOptionPane.ERROR_MESSAGE);
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
				(new PeopleInfoManagerZ()).createPeopleInfo(this.peopleinfo);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.peopleinfo=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	public PeopleInfo getPeopleInfo() {
		return peopleinfo;
	}
	
}