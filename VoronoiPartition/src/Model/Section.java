package Model;

public class Section {
	
	private Line line;
	private Vec2D p1, p2;
	
	public Section(Line line, Vec2D p1, Vec2D p2) {
		this.line = line;
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public boolean inGoodDirection(Vec2D point) {
		return line.inGoodDirection(point);
	}
	
	public IntersectionResult intersectionResult(Line newLine){
		int db = 0;
		if(newLine.inGoodDirection(p1)) db++;
		if(newLine.inGoodDirection(p2)) db++;
		if(db == 2) return IntersectionResult.keepIt;
		else if(db == 0) return IntersectionResult.deleteIt;
		else return IntersectionResult.intersects;
	}
	
	public Vec2D cutIntersect(Line intersectLine) {
		if(intersectLine.inGoodDirection(p1)){
			p2 = line.intersection(intersectLine);
			return p2;
		}
		else {
			p1 = line.intersection(intersectLine);
			return p1;
		}
	}
	
	public Vec2D getP1() {
		return p1;
	}
	
	public Vec2D getP2() {
		return p2;
	}
}
