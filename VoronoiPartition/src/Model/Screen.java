package Model;

import java.util.ArrayList;

public class Screen implements ScreenSize {
	
	private int w, h;
	private ArrayList<Point> points;
	public Screen(int w, int h) {
		points = new ArrayList<Point>();
		this.w = w;
		this.h = h;
	}
	
	public void resize(int w, int h) {
		this.w = w;
		this.h = h;
		ArrayList<Point> copied = points;
		points = new ArrayList<>();
		for(Point p : copied){
			this.addPoint(p.getPos());
		}
	}
	
	public void addPoint(Vec2D point) {
		Point p = new Point(point);
		if(points.size() == 0) {
			points.add(p);
			return;
		}
		if(containsPoint(point)){
			return;
		}
		updateAreas(p);
		points.add(p);
	}
	
	private boolean containsPoint(Vec2D p) {
		for(Point point : points) {
			if(point.getPos().equals(p)) {
				return true;
			}
		}
		return false;
	}
	
	private void updateAreas(Point p) {
		p.makeOwnArea(points);
		for(Point point : points){
			point.updateArea(p);
		}
	}
	
	public int getW() {
		return w;
	}
	
	public int getH() {
		return h;
	}

	public ArrayList<Point> getPoints() {
		return points;
	}

	@Override
	public int getScreenW() {
		return w;
	}
	
	@Override
	public int getScreenH() {
		return h;
	}
}
