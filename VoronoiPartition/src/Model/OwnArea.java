package Model;

import java.util.ArrayList;

public class OwnArea {

	private ArrayList<Section> sections;
	static public ScreenSize screenSize;
	
	public OwnArea(){
		int w = screenSize.getScreenW();
		int h = screenSize.getScreenH();
		sections = new ArrayList<>();
		sections.add(new Section(new Line(new Vec2D(0,0), new Vec2D(w,0)), new Vec2D(0,0), new Vec2D(w,0)));
		sections.add(new Section(new Line(new Vec2D(w,0), new Vec2D(0,h)), new Vec2D(w,0), new Vec2D(w,h)));
		sections.add(new Section(new Line(new Vec2D(w,h), new Vec2D(-w,0)), new Vec2D(w,h), new Vec2D(0,h)));
		sections.add(new Section(new Line(new Vec2D(0,h), new Vec2D(0,-h)), new Vec2D(0,h), new Vec2D(0,0)));
	}
	
	private ArrayList<Section> getIntersecteds(Line line){
		ArrayList<Section> intersecteds = new ArrayList<>();
		ArrayList<Section> toRemove = new ArrayList<>();
		for(Section section : sections){
			switch(section.intersectionResult(line)){
			case keepIt:{
				break;
			}
			case deleteIt:{
				toRemove.add(section);
				break;
			}
			case intersects:{
				intersecteds.add(section);
				break;
			}
			}
		}
		sections.removeAll(toRemove);
		return intersecteds;
	}
	
	private void refreshSections(Line line, ArrayList<Section> intersecteds){
		ArrayList<Vec2D> intersectPoints = new ArrayList<>();
		for(Section intersected : intersecteds){
			intersectPoints.add(intersected.cutIntersect(line));
		}
		sections.add(new Section(line, intersectPoints.get(0), intersectPoints.get(1)));
	}
	
	public void addLine(Line line) {
		ArrayList<Section> intersecteds = getIntersecteds(line);
		
		if(intersecteds.size() == 2){
			refreshSections(line, intersecteds);
		} else if(intersecteds.size() == 0){
			return;
		} else {
			System.err.println("Az új vonal hozzáadásánál hiba lépett fel, nem 2 szakaszt metsz pontosan az új vonal");
		}
	}

	public ArrayList<Section> getSections() {
		return sections;
	}
}
