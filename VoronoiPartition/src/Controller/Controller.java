package Controller;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;

import javax.swing.JFrame;

import Model.Screen;
import Model.Vec2D;
import View.View;

public class Controller implements MouseListener, ComponentListener, KeyListener, WindowStateListener {

	private View view;
	private Screen screen;
	
	public Controller(Screen screen, View view){
		this.screen = screen;
		this.view = view;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		screen.addPoint(new Vec2D(x, y));
		view.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void componentHidden(ComponentEvent e) {}

	@Override
	public void componentMoved(ComponentEvent e) {}

	@Override
	public void componentResized(ComponentEvent e) {
		int h = e.getComponent().getHeight();
		int w = e.getComponent().getWidth();
		screen.resize(w, h);
	}

	@Override
	public void componentShown(ComponentEvent e) {}

	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent ke) {
		if(ke.getKeyCode() == KeyEvent.VK_F5){
			int w = screen.getW(), h = screen.getH();
			screen = new Screen(w, h);
			view.reset();
			view.repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {}
	
	private static boolean isMaximized(int state) {
	    return (state & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH;
	}
	
	@Override
	public void windowStateChanged(WindowEvent event) {
		boolean isMaximized = isMaximized(event.getNewState());
        boolean wasMaximized = isMaximized(event.getOldState());

        if (isMaximized && !wasMaximized) {
        	int h = event.getComponent().getHeight();
    		int w = event.getComponent().getWidth();
    		screen.resize(w, h);
        }
	}
}
