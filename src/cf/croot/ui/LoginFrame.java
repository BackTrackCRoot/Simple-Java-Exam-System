
//LoginFrame.java

package cf.croot.ui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import cf.croot.database.Users;
import cf.croot.until.BackgroundPanel;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private JComboBox<String> comboBox;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LoginFrame frame = new LoginFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("\u767B\u5F55");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 434, 255);
		setLocationRelativeTo(null);// 使窗体居中
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u8003\u751F\u53F7\uFF1A");
		lblNewLabel.setBounds(55, 66, 54, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u5BC6\u7801\uFF1A");
		lblNewLabel_1.setBounds(55, 101, 54, 15);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(149, 66, 222, 21);
		textField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER||e.getKeyCode()==KeyEvent.VK_TAB){
					passwordField.requestFocus();
				}
			}
		});
		contentPane.add(textField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(149, 98, 222, 21);
		contentPane.add(passwordField);
		passwordField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					String passwd = new String(passwordField.getPassword());
					if(textField.getText().equals("")||passwd.equals("")){
						JOptionPane.showMessageDialog(null, "账号/密码不能为空！");
					}
					else{
						if(Users.CheckPassWordAndUser(textField.getText(), passwd)){
							setVisible(false);
							StartUi.StartTest(comboBox.getSelectedItem().toString());
						}
						else
							JOptionPane.showMessageDialog(null, "账号/密码错误！");
					}
				}
			}
		});
		
		JButton btnNewButton = new JButton("登录");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String passwd = new String(passwordField.getPassword());
				if(textField.getText().equals("")||passwd.equals("")){
					JOptionPane.showMessageDialog(null, "账号/密码不能为空！");
				}
				else{
					if(Users.CheckPassWordAndUser(textField.getText(), passwd)){
						setVisible(false);
						StartUi.StartTest(comboBox.getSelectedItem().toString());
					}
					else
						JOptionPane.showMessageDialog(null, "账号/密码错误！");
				}
			}
		});
		btnNewButton.setBounds(55, 171, 93, 23);
		contentPane.add(btnNewButton);
		
		JLabel label = new JLabel("\u8003\u8BD5\u9879\u76EE\uFF1A");
		label.setBounds(55, 131, 73, 15);
		contentPane.add(label);
		
		String comoboBoxList[] = {"软件专业","网络专业","信息安全专业"};
		comboBox = new JComboBox<String>(comoboBoxList);
		comboBox.setBounds(149, 129, 222, 21);
		contentPane.add(comboBox);
		
		Image image=new ImageIcon(this.getClass().getResource("/images/login.png")).getImage();
		image.flush();
		BackgroundPanel panel = new BackgroundPanel(image);
		panel.setBounds(0, 0, 428, 60);
		contentPane.add(panel);
	}
}