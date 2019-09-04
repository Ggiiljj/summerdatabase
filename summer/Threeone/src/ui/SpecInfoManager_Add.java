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
import control.SpecInfoManagerZ;
import control.SystemUserManager;
import model.PeopleInfo;
import model.SpecInfo;
import util.BaseException;

public class SpecInfoManager_Add extends JDialog implements ActionListener{
	private SpecInfo specinfo=null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");
	private JLabel labelSpecclass = new JLabel("�س����");
	private JLabel labelSpecgrade= new JLabel("�س�����");
	private JLabel labelSpecdesc= new JLabel("�س�������");
	private JLabel labelSepcprove = new JLabel("�س�֤����");
	private JTextField edtSpecclass = new JTextField(20);
	private JTextField edtSpecgrade = new JTextField(20);
	private JTextField edtSpecdesc = new JTextField(20);
	private JTextField edtSepcprove = new JTextField(20);
	public SpecInfoManager_Add(JDialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelSpecclass);
		workPane.add(edtSpecclass);
		workPane.add(labelSpecgrade);
		workPane.add(edtSpecgrade);
		workPane.add(labelSpecdesc);
		workPane.add(edtSpecdesc);
		workPane.add(labelSepcprove);
		workPane.add(edtSepcprove);
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
			String peopleid=SystemUserManager.currentUser.getPeopleid().toString();
			String specclass=this.edtSpecclass.getText().toString();
			String specgrade=this.edtSpecgrade.getText().toString();
			String specdesc=this.edtSpecdesc.getText().toString();
			String specprove=this.edtSepcprove.getText().toString();
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
			this.specinfo=new SpecInfo();
			this.specinfo.setPeopleid(peopleid);
			this.specinfo.setSpecclass(specclass);
			this.specinfo.setSpecdec(specdesc);
			this.specinfo.setSpecgrade(specgrade);
			this.specinfo.setSpecprove(specprove);
			try {
				(new SpecInfoManagerZ()).createSpecInfo(this.specinfo);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.specinfo=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	public SpecInfo getSpecInfo() {
		return specinfo;
	}
	
}