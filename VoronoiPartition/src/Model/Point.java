package Model;

import java.util.ArrayList;

public class Point {
	
	private Vec2D pos;
	private OwnArea ownArea;
	
	public Point(Vec2D position){
		pos = position;
		ownArea = new OwnArea();
	}
	
	public void makeOwnArea(ArrayList<Point> list) {
		for(Point p : list){
			updateArea(p);
		}
	}
	
	public void updateArea(Point p){
		Line line = calcLine(p);
		ownArea.addLine(line);
	}
	
	private Line calcLine(Point p) {
		Vec2D halfP = pos.halfP(p.pos);
		Vec2D direction = pos.normalVec(p.pos);	// nem mindegy, hogy p-n, vagy pos-on hívjuk meg, mert a vektor irányát fogjuk használni a tartalmazások meghatározásánál
		return new Line(halfP, direction);
	}

	public ArrayList<Section> getSections() {
		return ownArea.getSections();
	}
	
	public Vec2D getPos(){
		return pos;
	}
}
