package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.EnrollPlanManagerZ;
import control.RecommendInfoZ;
import control.SignupPlanManagerZ;
import model.EnrollPlan;
import model.SignupPlan;
import util.BaseException;

public class SignupPlanManager_Modify extends JDialog implements ActionListener {
	private SignupPlan signupplan=null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");//�����ƻ�����
	private JLabel labelSigncondclass= new JLabel("�������ͣ�");
	private JLabel labelSigncond1= new JLabel("����1 :");
	private JLabel labelSigncond2= new JLabel("����2��");
	private JLabel labelBegintime= new JLabel("��ʼ����ʱ�䣺");
	private JLabel labelEndtime= new JLabel("��������ʱ�䣺");
	private JLabel labelSignupway= new JLabel("������ʽ��");
	private JComboBox cmbUsertype1= new JComboBox(new String[] { "1��A��", "2���ּܷ���"});
	private JTextField edtSchoolid = new JTextField(20);
	private JTextField edtSigncond1= new JTextField(20);
	private JTextField edtSigncond2= new JTextField(20);
	private JTextField edtBegintime= new JTextField(20);
	private JTextField edtEndtime= new JTextField(20);
	private JTextField edtSignupway= new JTextField(20);
	public SignupPlanManager_Modify(JDialog f, String s, boolean b,SignupPlan rt) {
		super(f, s, b);
		this.signupplan=rt;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelSigncondclass);
		workPane.add(cmbUsertype1);
		workPane.add(labelSigncond1);
		this.edtSigncond1.setText(String.valueOf(rt.getSigncond1()));
		workPane.add(edtSigncond1);
		workPane.add(labelSigncond2);
		this.edtSigncond2.setText(String.valueOf(rt.getSigncond2()));
		workPane.add(edtSigncond2);
		workPane.add(labelBegintime);
		this.edtBegintime.setText(String.valueOf(rt.getBegintime()));
		workPane.add(edtBegintime);
		workPane.add(labelEndtime);
		this.edtEndtime.setText(String.valueOf(rt.getEndtime()));
		workPane.add(edtEndtime);
		workPane.add(labelSignupway);
		this.edtSignupway.setText(rt.getSignupway());
		workPane.add(edtSignupway);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(220, 400);
		// ��Ļ������ʾ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				SignupPlanManager_Modify.this.signupplan=null;
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			this.signupplan=null;
			return;
		}
		else if(e.getSource()==this.btnOk){
			SignupPlan s=new SignupPlan();
			int signcondclass=this.cmbUsertype1.getSelectedIndex()+1;
			int signcond1=Integer.parseInt(this.edtSigncond1.getText().toString());
			int signcond2=Integer.parseInt(this.edtSigncond2.getText().toString());
			Date begintime=null;
			Date endtime=null;
			begintime = java.sql.Date.valueOf(this.edtBegintime.getText().toString());
			endtime = java.sql.Date.valueOf(this.edtEndtime.getText().toString());
			String signupway=this.edtSignupway.getText();
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
			
			s.setSchoolid(this.signupplan.getSchoolid());
			s.setBegintime(begintime);
			s.setEndtime(endtime);
			s.setSigncond1(signcond1);
			s.setSigncond2(signcond2);
			s.setSigncondclass(signcondclass);
			s.setSignupway(signupway);
			try {
				(new SignupPlanManagerZ()).modifySignupPlan(s,this.signupplan);
				(new RecommendInfoZ()).removeSRecommend(this.signupplan.getSchoolid());

				this.setVisible(false);
			} catch (BaseException e1) {
				this.signupplan=null;
				JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	public SignupPlan getSignupPlan() {
		return signupplan;
	}
	
}