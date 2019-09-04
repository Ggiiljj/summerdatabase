package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import control.PeopleInfoManagerZ;
import control.SystemUserManager;
import model.PeopleInfo;
import model.XuetestInfo;
import util.BaseException;

public class XuetestInfoManager_Add extends JDialog implements ActionListener{
	private PeopleInfo XuetestInfo=null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");
	private JLabel label1= new JLabel("���ĳɼ���");
	private JLabel label2= new JLabel("��ѧ�ɼ���");
	private JLabel label3= new JLabel("Ӣ��ɼ���");
	private JLabel label4 = new JLabel("����ɼ���");
	private JLabel label5= new JLabel("��ѧ�ɼ���");
	private JLabel label6= new JLabel("����ɼ���");
	private JLabel label7 = new JLabel("���γɼ���");
	private JLabel label8= new JLabel("��ʷ�ɼ���");
	private JLabel label9= new JLabel("����ɼ���");
	private JTextField edt1 = new JTextField(20);
	private JTextField edt2 = new JTextField(20);
	private JTextField edt3 = new JTextField(20);
	private JTextField edt4 = new JTextField(20);
	private JTextField edt5 = new JTextField(20);
	private JTextField edt6 = new JTextField(20);
	private JTextField edt7 = new JTextField(20);
	private JTextField edt8 = new JTextField(20);
	private JTextField edt9 = new JTextField(20);
	public XuetestInfoManager_Add(JDialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(label1);
		workPane.add(edt1);
		workPane.add(label2);
		workPane.add(edt2);
		workPane.add(label3);
		workPane.add(edt3);
		workPane.add(label4);
		workPane.add(edt4);
		workPane.add(label5);
		workPane.add(edt5);
		workPane.add(label6);
		workPane.add(edt6);
		workPane.add(label7);
		workPane.add(edt7);
		workPane.add(label8);
		workPane.add(edt8);
		workPane.add(label9);
		workPane.add(edt9);
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
			List<String> resultx=new ArrayList<String>();
			List<String> resultn=new ArrayList<String>();
			resultn.add("����".toString());
			resultn.add("��ѧ".toString());
			resultn.add("Ӣ��".toString());
			resultn.add("����".toString());
			resultn.add("��ѧ".toString());
			resultn.add("����".toString());
			resultn.add("����".toString());
			resultn.add("��ʷ".toString());
			resultn.add("����".toString());
			String g1=this.edt1.getText();
			String g2=this.edt2.getText();
			String g3=this.edt3.getText();
			String g4=this.edt4.getText();
			String g5=this.edt5.getText();
			String g6=this.edt6.getText();
			String g7=this.edt7.getText();
			String g8=this.edt8.getText();
			String g9=this.edt9.getText();
			resultx.add(g1);
			resultx.add(g2);
			resultx.add(g3);
			resultx.add(g4);
			resultx.add(g5);
			resultx.add(g6);
			resultx.add(g7);
			resultx.add(g8);
			resultx.add(g9);
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
			for(int i=0;i<9;i++)
			{
				XuetestInfo x=new XuetestInfo();
				PeopleInfoManagerZ y=new PeopleInfoManagerZ();
				x.setPeopleid(SystemUserManager.currentUser.getPeopleid());
				try {
					x.setTestid(y.LoadTestid(resultn.get(i)));
					System.out.print(resultn.get(i));
				} catch (BaseException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				x.setTestname(resultn.get(i));
				x.setGrade(Integer.parseInt(resultx.get(i)));
				try {
					(new PeopleInfoManagerZ()).createXuetestInfo(x);
					this.setVisible(false);
				} catch (BaseException e1) {
					this.XuetestInfo=null;
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
			}
			}
			
		
	}
	public PeopleInfo getXuetestInfo() {
		return XuetestInfo;
	}
	
}


