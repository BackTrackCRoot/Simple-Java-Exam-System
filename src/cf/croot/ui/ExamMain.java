package cf.croot.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;

import cf.croot.database.LoadSubJect;
import cf.croot.until.BackgroundPanel;

@SuppressWarnings("serial")
public class ExamMain extends JFrame {

	private JPanel contentPane;
	private static LoadSubJect ls;
	private static JTextArea textArea;
	private static int SubjectNumber =0;
	private JTable testtable;
	private ButtonGroup SelectABCD;
	private ArrayList<Integer> SubjectSelect =null;
	private static JRadioButton ARadioButton,BRadioButton,CRadioButton,DRadioButton;
	private JLabel myclock;

	/**
	 * Create the frame.
	 */
	public ExamMain(String ExamCategory) {
		setTitle("�����ѧ������ϵͳ-2015");
		System.out.println("��ǰѡ��רҵ��"+ExamCategory);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		setLocationRelativeTo(null);// ʹ�������
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("����",Font.PLAIN,30));
		JScrollPane textLinWrap = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		contentPane.add(textLinWrap, BorderLayout.CENTER);
		
		Image image=new ImageIcon(this.getClass().getResource("/images/maininfo.png")).getImage();
		image.flush();
		BackgroundPanel InfoPanel = new BackgroundPanel(image);
		InfoPanel.setPreferredSize(new Dimension(500,80));
		contentPane.add(InfoPanel,BorderLayout.NORTH);
		
		JPanel ControlPanel = new JPanel();
		JButton LastButton = new JButton("��һ��");
		LastButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if((getSubjectNumber()-1)!=0){
					ChangeSubject(getSubjectNumber()-1);
					DefaultTableModel dt = (DefaultTableModel) testtable.getModel();
					String SubjectState = (String) dt.getValueAt(getSubjectNumber()-1, 1);
					if(SubjectState.equals("��")){
						SelectABCD.clearSelection();
					}
					else{
						int i = SubjectSelect.get(getSubjectNumber()-1);
						setSelectABCD(i);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "���Ѿ������һ�⣡", "��Ŵ���", JOptionPane.ERROR_MESSAGE);
			}
		});
		ARadioButton = new JRadioButton("A");
		ARadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DefaultTableModel dt = (DefaultTableModel) testtable.getModel();
				dt.setValueAt("��", getSubjectNumber()-1, 1);
				SubjectSelect.set(getSubjectNumber()-1, 1);
				System.out.println("ArrayList��С��"+SubjectSelect.size());
//				System.out.println("ArrayList��һ����"+SubjectSelect.get(0));
			}
		});
		BRadioButton = new JRadioButton("B");
		BRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DefaultTableModel dt = (DefaultTableModel) testtable.getModel();
				dt.setValueAt("��", getSubjectNumber()-1, 1);
				SubjectSelect.set(getSubjectNumber()-1, 2);
			}
		});
		CRadioButton = new JRadioButton("C");
		CRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DefaultTableModel dt = (DefaultTableModel) testtable.getModel();
				dt.setValueAt("��", getSubjectNumber()-1, 1);
				SubjectSelect.set(getSubjectNumber()-1, 3);
			}
		});
		DRadioButton = new JRadioButton("D");
		DRadioButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DefaultTableModel dt = (DefaultTableModel) testtable.getModel();
				dt.setValueAt("��", getSubjectNumber()-1, 1);
				SubjectSelect.set(getSubjectNumber()-1, 4);
			}
		});
		JButton NextButton = new JButton("��һ��");
		NextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(ls.getSubjectCount()!=getSubjectNumber()){
					ChangeSubject(getSubjectNumber()+1);
					DefaultTableModel dt = (DefaultTableModel) testtable.getModel();
					String SubjectState = (String) dt.getValueAt(getSubjectNumber()-1, 1);
					if(SubjectState.equals("��")){
						SelectABCD.clearSelection();
					}
					else{
						int i = SubjectSelect.get(getSubjectNumber()-1);
						setSelectABCD(i);
					}
				}
				else
					JOptionPane.showMessageDialog(null, "���Ѿ��������һ�⣡", "��Ŵ���", JOptionPane.ERROR_MESSAGE);
			}
		});
		JButton SubmitButton = new JButton("����");
		SubmitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(JOptionPane.showConfirmDialog(null,"��ȷ��Ҫ����", "����", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
					//�������
					int score = ls.CompareAnswer(SubjectSelect, ExamCategory);
					JOptionPane.showMessageDialog(null, "���ķ���Ϊ��"+score+"��", "�ɼ�", JOptionPane.INFORMATION_MESSAGE);
					System.exit(0);
				}
			}
		});
		SelectABCD = new ButtonGroup();
		SelectABCD.add(ARadioButton);
		SelectABCD.add(BRadioButton);
		SelectABCD.add(CRadioButton);
		SelectABCD.add(DRadioButton);
		ControlPanel.add(LastButton);
		ControlPanel.add(ARadioButton);
		ControlPanel.add(BRadioButton);
		ControlPanel.add(CRadioButton);
		ControlPanel.add(DRadioButton);
		ControlPanel.add(NextButton);
		ControlPanel.add(SubmitButton);
		ControlPanel.setLayout(new FlowLayout(FlowLayout.CENTER,40,0));
		contentPane.add(ControlPanel,BorderLayout.SOUTH);
		
		JPanel TestView = new JPanel();
		String[] columns  ={"���","����"}; 
		Object[][] rowData={};
		//��ֹ�༭
		DefaultTableModel model = new DefaultTableModel(rowData,columns){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row,int column){
				return false;
			}
		};
		testtable = new JTable(model);
		testtable.getTableHeader().setReorderingAllowed(false);
		testtable.getTableHeader().setResizingAllowed(false);
		testtable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getButton()== MouseEvent.BUTTON1){
					System.out.println("��ǰ���������"+testtable.getSelectedRow());
					ChangeSubject(testtable.getSelectedRow()+1);
					DefaultTableModel dt = (DefaultTableModel) testtable.getModel();
					String SubjectState = (String) dt.getValueAt(getSubjectNumber()-1, 1);
					if(SubjectState.equals("��")){
						SelectABCD.clearSelection();
					}
					else{
						int i = SubjectSelect.get(getSubjectNumber()-1);
						setSelectABCD(i);
					}
				}
			}
		});
		
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
		// tcr.setHorizontalAlignment(JLabel.CENTER);
		tcr.setHorizontalAlignment(SwingConstants.CENTER);// �����Ͼ�����һ��
		testtable.setDefaultRenderer(Object.class, tcr);
		
		
		JScrollPane scrollPane = new JScrollPane(testtable);
		scrollPane.setPreferredSize(new Dimension(200,400));
		myclock = new JLabel("�ѿ��ԣ�0����");
		myclock.setFont(new Font("����",Font.PLAIN,30));
		TestView.add(scrollPane);
		TestView.add(myclock);
		TestView.setPreferredSize(new Dimension(210,500));
		contentPane.add(TestView,BorderLayout.EAST);
		
		ls = new LoadSubJect(ExamCategory);
		int sc = ls.getSubjectCount();
		SubjectSelect = new ArrayList<Integer>();
		//
		if(ls.getSubjectCount()!=0){
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					DefaultTableModel tableModel = (DefaultTableModel) testtable.getModel();
					//String[][] tr = new String()
					for(int i =0;i<sc;i++){
						tableModel.addRow(new Object[]{Integer.toString(i+1), "��"});
						SubjectSelect.add(i,0);
					}
					ChangeSubject(1);
				}}).start();
		}
		else{
			//���Ϊ��
			JOptionPane.showMessageDialog(null, "��ǰ���Ϊ�գ�", "������", JOptionPane.ERROR_MESSAGE);
		}
//		DefaultTableModel tableModel = (DefaultTableModel) testtable.getModel();
//		for(int i =1;i<ls.getSubjectCount();i++){
//			
//		}
//		tableModel.addRow(new Object[]{"sitinspring", "35"});
		
		
		//��ʱ��
		Timer mytimer = new Timer();
		Clock ck =new Clock(ExamCategory);
		mytimer.schedule(ck,0,1000*60);
	}
	private static void ChangeSubject(int Id){
		HashMap<String,String> map = ls.getSubjet(Id);
		textArea.setText(map.get("Subject"));
		setSubjectNumber(Id);
	}
	/**
	 * @return the subjectNumber
	 */
	private static int getSubjectNumber() {
		return SubjectNumber;
	}
	/**
	 * @param subjectNumber the subjectNumber to set
	 */
	private static void setSubjectNumber(int subjectNumber) {
		SubjectNumber = subjectNumber;
	}
	private static void setSelectABCD(int ABCD){
		switch(ABCD){
		case 1:ARadioButton.setSelected(true);break;
		case 2:BRadioButton.setSelected(true);break;
		case 3:CRadioButton.setSelected(true);break;
		case 4:DRadioButton.setSelected(true);break;
		default:break;
		}
	}
	class Clock extends TimerTask{
		private String ExamCategory;
		public Clock(String ExamCategory){
			this.ExamCategory=ExamCategory;
		}
		private int s = -1;
		@Override
		public void run() {
			// TODO Auto-generated method stub
			s+=1;
			myclock.setText("�ѿ��ԣ�" + s + "����");
			System.err.println("�ѿ��ԣ�" + s + "����");
			if(s==5){
				int score = ls.CompareAnswer(SubjectSelect, ExamCategory);
				JOptionPane.showMessageDialog(null, "���ķ���Ϊ��"+score+"��", "�ɼ�", JOptionPane.INFORMATION_MESSAGE);
				System.exit(0);
			}
		}	
	}
}