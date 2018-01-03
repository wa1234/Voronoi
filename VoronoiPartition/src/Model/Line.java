package Model;

public class Line {
	
	private Vec2D p;
	private Vec2D dir;

	public Line(Vec2D point, Vec2D direction) {
		p = point;
		dir = direction;
	}

	public Vec2D getPoint(){
		return p;
	}

	public boolean inGoodDirection(Vec2D point) {
		return dir.cross(p.fromTo(point)) < 0;	// fontos az irány!
	}

	public Vec2D intersection(Line intersectLine) {
		double lambda = (dir.y()*p.x() - dir.y()*intersectLine.p.x() + intersectLine.p.y()*dir.x() - p.y()*dir.x()) / (intersectLine.dir.x()*dir.y() - intersectLine.dir.y()*dir.x());
		double retX = intersectLine.p.x() + lambda * intersectLine.dir.x();
		double retY = intersectLine.p.y() + lambda * intersectLine.dir.y();
		return new Vec2D(retX, retY);
	}
}
