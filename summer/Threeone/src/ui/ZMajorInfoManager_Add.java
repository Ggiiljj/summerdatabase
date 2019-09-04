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

import control.MajorInfoManagerZ;
import model.MajorInfo;
import util.BaseException;

public class ZMajorInfoManager_Add extends JDialog implements ActionListener{
	private MajorInfo majorinfo=null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");
	private JLabel labelMajorid = new JLabel("רҵid��");
	private JLabel labelMajorname= new JLabel("רҵ����");
	private JLabel labelMajorclass= new JLabel("רҵ���");
	private JLabel labelSchoolid= new JLabel("ѧУid��");
	private JLabel labelSchoolname= new JLabel("ѧУ����");
	private JTextField edtMajorid = new JTextField(20);
	private JTextField edtMajorname= new JTextField(20);
	private JTextField edtMajorclass= new JTextField(20);
	private JTextField edtSchoolid= new JTextField(20);
	private JTextField edtSchoolname= new JTextField(20);
	public ZMajorInfoManager_Add(JDialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelMajorid);
		workPane.add(edtMajorid);
		workPane.add(labelMajorname);
		workPane.add(edtMajorname);
		workPane.add(labelMajorclass);
		workPane.add(edtMajorclass);
		workPane.add(labelSchoolid);
		workPane.add(edtSchoolid);
		workPane.add(labelSchoolname);
		workPane.add(edtSchoolname);
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
			MajorInfo s=new MajorInfo();
			String Majorid=this.edtMajorid.getText();
			String Majorname=this.edtMajorname.getText();
			String Majorclass=this.edtMajorclass.getText();
			String Schoolid=this.edtSchoolid.getText();
			String Schoolname=this.edtSchoolname.getText();
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
			this.majorinfo=new MajorInfo();
			this.majorinfo.setSchoolname(Schoolname);
			this.majorinfo.setSchoolid(Schoolid);
			this.majorinfo.setMajorname(Majorname);
			this.majorinfo.setMajorid(Majorid);
			this.majorinfo.setMajorclass(Majorclass);
			try {
				(new MajorInfoManagerZ()).createMajorInfo(this.majorinfo);
				this.setVisible(false);
			} catch (BaseException e1) {
				this.majorinfo=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	public MajorInfo getPeopleInfo() {
		return majorinfo;
	}
	
}