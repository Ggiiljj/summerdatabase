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

import control.SpecInfoManagerZ;
import control.SystemUserManager;
import model.SpecInfo;
import util.BaseException;

public class Zte extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
//	private Button btnAdd = new Button("��д�س���Ϣ");
//	private Button btnModify = new Button("�޸��س���Ϣ");
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("��ѯ");
	private Object tblTitle[]={"����id","�س����","�س��ȼ�","�س�����","�س�֤��"};
	private Object tblData[][];
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable readerTypeTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			SpecInfoManagerZ s=new SpecInfoManagerZ();
			List<SpecInfo> types= s.loadASpec(this.edtKeyword.getText().toString());
			tblData =new Object[types.size()][5];
			for(int i=0;i<types.size();i++)
			{
				tblData[i][0]=types.get(i).getPeopleid()+"";
				tblData[i][1]=types.get(i).getSpecclass();
				tblData[i][2]=types.get(i).getSpecgrade()+"";
				tblData[i][3]=types.get(i).getSpecdec()+"";
				tblData[i][4]=types.get(i).getSpecprove()+"";
			
			}
			
			tablmod.setDataVector(tblData,tblTitle);
			this.readerTypeTable.validate();
			this.readerTypeTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	public Zte(Frame f, String s, boolean b) {
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
//			SpecInfoManager_Add dlg=new SpecInfoManager_Add(this,"�û���Ϣ����",true);
//	        dlg.setVisible(true);
//	        this.reloadTable();
//		}
//		else if(e.getSource()==this.btnModify){
//			int i=this.readerTypeTable.getSelectedRow();
//			if(i<0) {
//				JOptionPane.showMessageDialog(null,  "��ѡ��","��ʾ",JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			SpecInfo specinfo=new SpecInfo();
//			specinfo.setPeopleid(this.tblData[i][0].toString());
//			specinfo.setSpecclass(this.tblData[i][1].toString());
//			specinfo.setSpecgrade(this.tblData[i][2].toString());
//			specinfo.setSpecdec(this.tblData[i][3].toString());
//			specinfo.setSpecprove(this.tblData[i][4].toString());
//			SpecInfoManager_Modify dlg=new SpecInfoManager_Modify(this,"�޸Ķ������",true,specinfo);
//			dlg.setVisible(true);
//			if(dlg.getSpecInfo()!=null){//ˢ�±���
//				this.reloadTable();
//			}
//		}
//	}
	
}}