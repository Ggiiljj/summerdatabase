package ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import control.SchoolInfoManagerZ;
import control.SystemUserManager;
import model.SchoolInfo;
import model.UsersInfo;
import util.BaseException;

public class UserM extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Button btnAddz = new Button("���ӹ���Ա�û�");
	private Button btnAddu = new Button("������ͨ�û�");
	//private Button btnModify = new Button("�޸��û���Ϣ");
	private Button btnDelete = new Button("ɾ���û�");
//	private Map<String,SchoolInfo> SchoolInfo_name=new HashMap<String,SchoolInfo>();
//	private Map<String,SchoolInfo> SchoolInfo_id=new HashMap<String,SchoolInfo>();
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("��ѯ");
	private Object tblTitle[]={"�û�Id","�û���","�û�����","�û�����"};
	private Object tblData[][];
	List<UsersInfo> users=null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable readerTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			users=(new SystemUserManager()).loadaUser();
			tblData =new Object[users.size()][4];
			for(int i=0;i<users.size();i++){
				tblData[i][0]=users.get(i).getPeopleid();
				tblData[i][1]=users.get(i).getPeoplename();
				tblData[i][2]=users.get(i).getPeoplegrade();
				tblData[i][3]=users.get(i).getPeoplepwd();

			}
			tablmod.setDataVector(tblData,tblTitle);
			this.readerTable.validate();
			this.readerTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public UserM(Frame f, String s, boolean b) {
		super(f, s, b);
		//��ȡ���������Ϣ
		List<SchoolInfo> types=null;
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAddz);
		toolBar.add(btnAddu);
		//toolBar.add(btnModify);
		toolBar.add(this.btnDelete);
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		
		
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//��ȡ��������
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.readerTable), BorderLayout.CENTER);
		
		// ��Ļ������ʾ
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnAddz.addActionListener(this);
		this.btnAddu.addActionListener(this);
		//this.btnModify.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.btnSearch.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnAddu){
			UserMu_Add dlg=new UserMu_Add(null,"�����û�",true);
			dlg.setVisible(true);
			this.reloadTable();
		
//			if(dlg.getReader()!=null){//ˢ�±���
//				this.reloadTable();
//			}
		}
		else if (e.getSource()==this.btnAddz)
		{
			UserMz_Add dlg=new UserMz_Add(null,"�����û�",true);
			dlg.setVisible(true);
			this.reloadTable();
			
		}
//		else if(e.getSource()==this.btnModify){
//			int i=this.readerTable.getSelectedRow();
//			if(i<0) {
//				JOptionPane.showMessageDialog(null,  "��ѡ��ѧУ","��ʾ",JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			UsersInfo users=this.users.get(i);
//			BeanReader reader=this.readers.get(i);
//			
//			ZSchoolInfoManager_Modify dlg=new ZSchoolInfoManager_Modify(this,"�޸Ķ���",true,school);
//			dlg.setVisible(true);
//			if(dlg.getSchoolInfo()!=null){//ˢ�±���
//				this.reloadTable();
//			}
//		}
		else if(e.getSource()==this.btnDelete){
			int i=this.readerTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ������ʺ�","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			UsersInfo user=this.users.get(i);
			if(JOptionPane.showConfirmDialog(this,"ȷ��ɾ�����ʺ���","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					(new SystemUserManager()).removeUser(user.getPeopleid());
					this.reloadTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		else if(e.getSource()==this.btnSearch){
			this.reloadTable();
		}
	}
}