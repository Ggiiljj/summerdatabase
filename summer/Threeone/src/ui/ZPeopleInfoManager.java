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
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import control.PeopleInfoManagerZ;
import control.SchoolInfoManagerZ;
import control.SystemUserManager;
import model.PeopleInfo;
import model.SchoolInfo;
import util.BaseException;
import util.BusinessException;

public class ZPeopleInfoManager extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private Button btnDelete = new Button("ɾ����Ա��Ϣ");
//	private Map<String,SchoolInfo> SchoolInfo_name=new HashMap<String,SchoolInfo>();
//	private Map<String,SchoolInfo> SchoolInfo_id=new HashMap<String,SchoolInfo>();
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("��ѯ");
	private Object tblTitle[]={"���֤��","׼��֤��","����","�Ա�","�绰����","������ѧ","����ʡ��"};
	private Object tblData[][];
	List<PeopleInfo> peoples=null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable readerTypeTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			SystemUserManager s=new SystemUserManager();
			peoples= s.loadZPeople(this.edtKeyword.getText().toString());
			System.out.print(this.edtKeyword.getText().toString());
			tblData =new Object[peoples.size()][7];
			for(int i=0;i<peoples.size();i++)
			{
				tblData[i][0]=peoples.get(i).getPeopleid()+"";
				tblData[i][1]=peoples.get(i).getTestid();
				tblData[i][2]=peoples.get(i).getPeoplename()+"";
				tblData[i][3]=peoples.get(i).getSex()+"";
				tblData[i][4]=peoples.get(i).getPhonenum()+"";
				tblData[i][5]=peoples.get(i).getMschoolname()+"";
				tblData[i][6]=peoples.get(i).getProvince()+"";
			}
			
			tablmod.setDataVector(tblData,tblTitle);
			this.readerTypeTable.validate();
			this.readerTypeTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public ZPeopleInfoManager(Frame f, String s, boolean b) {
		//��ȡ���������Ϣ
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
//		toolBar.add(btnAdd);
//		toolBar.add(btnModify);
		toolBar.add(this.btnDelete);
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//��ȡ��������
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.readerTypeTable), BorderLayout.CENTER);
		
		// ��Ļ������ʾ
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

//		this.btnAdd.addActionListener(this);
//		this.btnModify.addActionListener(this);
		this.btnSearch.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.addWindowListener((WindowListener) new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				//System.exit(0);
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.btnSearch){
			this.reloadTable();
		}
	  
		
		
		// TODO Auto-generated method stub
//		if(e.getSource()==this.btnAdd){
//			
//			SystemUserManager s=new SystemUserManager();
//			try {
//				List<PeopleInfo> t= s.loadPeople(SystemUserManager.currentUser.getPeopleid());
//				if(t.size()>0)
//				{
//					throw new BusinessException("��ѧ����Ϣ�Ѿ�¼��");
//				}
//				else
//				{
//					PeopleInfoManager_Add dlg=new PeopleInfoManager_Add(this,"�û���Ϣ����",true);
//					dlg.setVisible(true);
//				}
//			} catch (BaseException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//				JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
//			}
////			this.reloadTable();
//		}
//		else if(e.getSource()==this.btnModify){
//			int i=this.readerTypeTable.getSelectedRow();
//			if(i<0) {
//				JOptionPane.showMessageDialog(null,  "��ѡ��","��ʾ",JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			PeopleInfo peopleinfo=new PeopleInfo();
//			peopleinfo.setPeopleid(this.tblData[i][0].toString());
//			peopleinfo.setTestid(this.tblData[i][1].toString());
//			peopleinfo.setPeoplename(this.tblData[i][2].toString());
//			peopleinfo.setSex(this.tblData[i][3].toString());
//			peopleinfo.setPhonenum(this.tblData[i][4].toString());
//			peopleinfo.setMschoolname(this.tblData[i][5].toString());
//			peopleinfo.setProvince(this.tblData[i][6].toString());
//			PeopleInfoManager_Modify dlg=new PeopleInfoManager_Modify(this,"�޸Ķ������",true,peopleinfo);
//			dlg.setVisible(true);
//			if(dlg.getReadertype()!=null){//ˢ�±��
//				this.reloadTable();
//			}
//		}
		else if(e.getSource()==this.btnDelete){
			int i=this.readerTypeTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ����Ա��Ϣ","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			PeopleInfo people=this.peoples.get(i);
			if(JOptionPane.showConfirmDialog(this,"ȷ��ɾ������Ա��","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					PeopleInfoManagerZ s=new PeopleInfoManagerZ();
					System.out.print(people.getPeopleid());
					s.removePeoples(people.getPeopleid());
					this.reloadTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
	}
	
}