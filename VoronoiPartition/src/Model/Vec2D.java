package Model;

public class Vec2D {
	
	private double x, y;
	
	public Vec2D(double x, double y){
		this.x = x;
		this.y = y;
	}

	public double distSquare(Vec2D pos) {
		return x * pos.x + y * pos.y;
	}

	@Override
	public boolean equals(Object vec){
		Vec2D v = (Vec2D) vec;
		return v.x == x && v.y == y;
	}
	
	public Vec2D halfP(Vec2D p) {
		double newx = (x + p.x) / 2.0;
		double newy = (y + p.y) / 2.0;
		return new Vec2D(newx, newy);
	}
	
	// nem mindegy, hogy p-n, vagy pos-on hívjuk meg, mert a vektor irányát fogjuk használni a tartalmazások meghatározásánál
	// a lényeg az, hogy a középponthoz képest ez az óra járásával megegyező irányba mutató vektort hoz létre (ahol ez a Vec2D az óra középpontja)
	public Vec2D normalVec(Vec2D vec){
		double newx = vec.y - y;
		double newy = x - vec.x;
		return new Vec2D(newx, newy);
	}
	
	public Vec2D fromTo(Vec2D to){
		return new Vec2D(to.x - x, to.y - y);
	}

	public double cross(Vec2D vec) {
		return x * vec.y - y * vec.x;
	}
	
	public double x() { return x; }
	
	public double y() { return y; } 
}
