package a01027727;
import java.awt.EventQueue;


import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import a01027727.a3.MainFrame;


public class A3 {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
		      @Override
		      public void run() {
		        try {
		          for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
		            if ("Nimbus".equals(info.getName())) {
		              UIManager.setLookAndFeel(info.getClassName());
		              break;
		            }
		          }

		          MainFrame frame = new MainFrame();
		          frame.setVisible(true);
		          frame.setSize(400, 150);
		        } catch (Exception e) {
		          e.printStackTrace();
		        }
		      }
		    });
	}

}
