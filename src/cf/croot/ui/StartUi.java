
//StartUI.java

package cf.croot.ui;

import java.awt.EventQueue;

public class StartUi {
	private static LoginFrame myloginFrame;
	private static ExamMain myexammain;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myloginFrame = new LoginFrame();
					myloginFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void StartTest(String ExamCategory){
		EventQueue.invokeLater(new Runnable() {
		public void run() {
			try {
				myexammain = new ExamMain(ExamCategory);
				myexammain.setVisible(true);
				myloginFrame.dispose();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	});
	}
}
