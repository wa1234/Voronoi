package View;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;

import Controller.Controller;
import Model.OwnArea;
import Model.Point;
import Model.Screen;
import Model.Section;
import Model.Vec2D;

@SuppressWarnings("serial")
public class View extends JComponent {
	
	private Screen screen;
	private Color pointColor;
	private Color sectionColor;
	private Color background;
	private static int pixelSize = 1;
	private JFrame frame;
	private Controller controller;
	
	public void paintComponent(java.awt.Graphics g) {
		clear(g);
		for(Point p : screen.getPoints()) {
			paintPoint(g, p);
			for(Section section : p.getSections()){
				paintSection(g, section);
			}
		}
	}
	
	private void paintSection(Graphics g, Section section) {
		g.setColor(sectionColor);
		Vec2D p1 = section.getP1();
		Vec2D p2 = section.getP2();
		g.drawLine((int) p1.x(), (int) p1.y(), (int) p2.x(), (int) p2.y());
	}

	private void paintPoint(Graphics g, Point p) {
		g.setColor(pointColor);
		Vec2D point = p.getPos();
		g.drawRect((int) (point.x() - pixelSize), (int) (point.y() - pixelSize), pixelSize * 2, pixelSize * 2);
	}

	private void clear(Graphics g) {
		g.setColor(background);
		g.fillRect(0, 0, screen.getW(), screen.getH());
	}
	
	public View() {
		pointColor = Color.BLACK;
		sectionColor = Color.BLUE;
		background = Color.WHITE;
		frame = new JFrame();
		frame.setBackground(background);
		frame.setTitle("Partícionálás - Frissítés: F5");
		int w = 600, h = 500;
		frame.setSize(w, h);
		screen = new Screen(w, h);
		OwnArea.screenSize = screen;
		controller = new Controller(screen, this);
		this.addComponentListener(controller);
		this.addMouseListener(controller);
		frame.addKeyListener(controller);
		frame.add(this);
		frame.addWindowStateListener(controller);
		frame.setVisible(true);
	}
	
	public static void main(String args[]) {
		View view = new View();
		view.setVisible(true);
	}

	public void reset() {
		int w = screen.getW(), h = screen.getH();
		screen = new Screen(w, h);
		controller.setScreen(screen);
	}
}
