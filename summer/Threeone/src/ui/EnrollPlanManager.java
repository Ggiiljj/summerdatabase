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

import control.EnrollPlanManagerZ;
import control.SchoolInfoManagerZ;
import model.EnrollPlan;
import model.SchoolInfo;
import util.BaseException;

public class EnrollPlanManager extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Button btnAdd = new Button("添加招生计划表");
	private Button btnModify = new Button("修改招生计划表");
	private Button btnDelete = new Button("删除招生计划表");
//	private Map<String,SchoolInfo> SchoolInfo_name=new HashMap<String,SchoolInfo>();
//	private Map<String,SchoolInfo> SchoolInfo_id=new HashMap<String,SchoolInfo>();
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("查询");
	private Object tblTitle[]={"学校","专业编号","专业名","计划数","选考科目","学费","录取年份"};
	private Object tblData[][];
	List<EnrollPlan> enrolls=null;
	DefaultTableModel tablmod=new DefaultTableModel();
	private JTable readerTable=new JTable(tablmod);
	private void reloadTable(){
		try {
			enrolls=(new EnrollPlanManagerZ()).searchEnrolls(this.edtKeyword.getText().toString());
			tblData =new Object[enrolls.size()][7];
			for(int i=0;i<enrolls.size();i++){
				tblData[i][0]=enrolls.get(i).getSchoolname();
				tblData[i][1]=enrolls.get(i).getEnrollid();
				tblData[i][2]=enrolls.get(i).getEnrollname();
				tblData[i][3]=enrolls.get(i).getPlannum();
				tblData[i][4]=enrolls.get(i).getXuansubjects();
				tblData[i][5]=enrolls.get(i).getTuition();
				tblData[i][6]=enrolls.get(i).getEnrollyear();

			}
			tablmod.setDataVector(tblData,tblTitle);
			this.readerTable.validate();
			this.readerTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public EnrollPlanManager(Frame f, String s, boolean b) {
		super(f, s, b);
		//提取读者类别信息
		List<EnrollPlan> types=null;
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(this.btnDelete);
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		
		
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		//提取现有数据
		this.reloadTable();
		this.getContentPane().add(new JScrollPane(this.readerTable), BorderLayout.CENTER);
		
		// 屏幕居中显示
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnAdd.addActionListener(this);
		this.btnModify.addActionListener(this);
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
		if(e.getSource()==this.btnAdd){
			EnrollPlanManager_Add dlg=new EnrollPlanManager_Add(this,"添加招生计划表",true);
			dlg.setVisible(true);
			this.reloadTable();
//			if(dlg.getReader()!=null){//刷新表格
//				this.reloadTable();
//			}
		}
		else if(e.getSource()==this.btnModify){
			int i=this.readerTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择招生计划表","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			EnrollPlan enrolls=this.enrolls.get(i);
//			BeanReader reader=this.readers.get(i);
//		
			EnrollPlanManager_Modify dlg=new EnrollPlanManager_Modify(this,"修改招生计划",true,enrolls);
			dlg.setVisible(true);
			if(dlg.getEnrollPlan()!=null){//刷新表格
				this.reloadTable();
			}
		}
		else if(e.getSource()==this.btnDelete){
			int i=this.readerTable.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null,  "请选择招生计划表","提示",JOptionPane.ERROR_MESSAGE);
				return;
			}
			EnrollPlan enroll=this.enrolls.get(i);
			if(JOptionPane.showConfirmDialog(this,"确定删除该招生计划表吗？","确认",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				try {
					(new EnrollPlanManagerZ()).removeenroll(enroll.getEnrollid());
					this.reloadTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				}
				
			}
		}
		else if(e.getSource()==this.btnSearch){
			this.reloadTable();
		}
	}
}