package a01027727;

import java.awt.EventQueue;

import a01027727.b1.MainFrame;

public class B1 {

	public static void main(String[] args) {
		  EventQueue.invokeLater(new Runnable() {
		      @Override
		      public void run() {
		        try {
		          final MainFrame frame = new MainFrame();
		          frame.setVisible(true);
		        } catch (final Exception e) {
		          e.printStackTrace();
		        }
		      }
		    });
	}

}
