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
import control.SystemUserManager;
import control.XuantestInfoManagerZ;
import model.XuantestInfo;
import model.XuetestInfo;
import util.BaseException;
import util.BusinessException;

public class Zxuan extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
//	private Button btnAdd = new Button("��дѡ����Ϣ");
//	private Button btnModify = new Button("�޸�ѡ����Ϣ");
	private Button btnDelete = new Button("ɾ��ѡ����Ϣ");
	private Object tblTitle[]={"��Ŀ","�ɼ�","��Ŀ��","id"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("��ѯ");
	List<XuantestInfo> xinfos=null;
	private JTable readerTypeTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			XuantestInfoManagerZ s=new XuantestInfoManagerZ();
			List<XuantestInfo> types= s.loadallXuantestInfo(this.edtKeyword.getText().toString());
			tblData =new Object[types.size()][4];
			xinfos=types;
			for(int i=0;i<types.size();i++)
			{
				tblData[i][0]=types.get(i).getTestname()+"";
				tblData[i][1]=types.get(i).getGrade();
				tblData[i][2]=types.get(i).getTestid()+"";
				tblData[i][3]=types.get(i).getPeopleid()+"";
				System.out.print(tblData[i][0]);
			}
			
			tablmod.setDataVector(tblData,tblTitle);
			this.readerTypeTable.validate();
			this.readerTypeTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public Zxuan(Frame f, String s, boolean b) {
		//��ȡ���������Ϣ
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
//		toolBar.add(btnAdd);
//		toolBar.add(btnModify);
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
		// TODO Auto-generated method stub
		if(e.getSource()==this.btnSearch){
			this.reloadTable();
		}
	  
//		if(e.getSource()==this.btnAdd){
//			XuantestInfoManagerZ s=new XuantestInfoManagerZ();
//			try {
//				List<XuantestInfo> t= s.loadXuantestInfo(SystemUserManager.currentUser.getPeopleid());
//				if(t.size()>0)
//				{
//					throw new BusinessException("ѡ���ɼ��Ѿ�¼��");
//				}
//				else
//				{
//					XuantestInfoManager_Add dlg=new XuantestInfoManager_Add(this,"ѡ����Ϣ����",true);
//					dlg.setVisible(true);
//					this.reloadTable();
//				}
//			} catch (BaseException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//				JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
//			}
//			
//		}
		else if(e.getSource()==this.btnDelete){
			int i=this.readerTypeTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "��ѡ��","��ʾ",JOptionPane.ERROR_MESSAGE);
				return;
			}
			XuantestInfo xuan=this.xinfos.get(i);
			if(JOptionPane.showConfirmDialog(this,"ȷ��ɾ������Ա������ѡ����","ȷ��",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					PeopleInfoManagerZ s=new PeopleInfoManagerZ();
					System.out.print(xuan.getPeopleid());
					s.removeXuan(xuan.getPeopleid());
					this.reloadTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"����",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
	}
}