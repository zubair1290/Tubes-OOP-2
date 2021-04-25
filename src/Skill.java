import java.util.ArrayList;

/**
 * Skill
 */
public class Skill implements InventoryParent {
	protected int basePower;
	protected int masteryLevel;
	protected String skill;
	ArrayList <Element> elements;
	protected int jumlahElements;
	
	public Skill(String name, int basePower, int masteryLevel, int jumlahElements, Element[] e) {
		this.basePower = basePower;
		this.masteryLevel = masteryLevel;
		this.skill = name;
		this.elements = new ArrayList<Element>(jumlahElements);
		for (int i = 0; i < jumlahElements; i++){
			elements.add(e[i]);
		}
		this.jumlahElements = jumlahElements;
	}
	public int getPower(){
		return this.basePower;
	}
	public ArrayList<Element> getElements(){
		return this.elements;
		
	}

	boolean equals(Skill skill) {
		return this.skill == skill.skill;
	}

}
