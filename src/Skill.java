import java.util.ArrayList;

/**
 * Skill
 */
public class Skill implements InventoryParent {
	protected String skill;
	protected int basePower;
	protected int masteryLevel;
	ArrayList <Element> elements;
	protected int jumlahElements;
	
	public Skill(String skill, int basePower, int masteryLevel, int jumlahElements, Element[] e) {
		this.skill = skill;
		this.basePower = basePower;
		this.masteryLevel = masteryLevel;
		this.elements = new ArrayList<Element>(jumlahElements);
		for (int i = 0; i < jumlahElements; i++){
			elements.add(e[i]);
		}
		this.jumlahElements = jumlahElements;
	}
	public Skill(Skill skill) {
		this.skill = skill.skill;
		this.basePower = skill.basePower;
		this.masteryLevel = skill.masteryLevel;
		this.elements = new ArrayList<Element>(skill.jumlahElements);
		for (int i = 0; i < skill.jumlahElements; i++){
			elements.add(skill.elements.get(i));
		}
		this.jumlahElements = skill.jumlahElements;
	}

	public String getSkill() { return this.skill; }
	public int getPower() { return this.basePower; }
	public int getMasteryLevel() { return this.masteryLevel; }
	public ArrayList<Element> getElements(){ return this.elements; }

	@Override
	public boolean equals(Object skill) {
		if (skill == this) return true;
		Skill skill2 = (Skill) skill;
		return this.skill == skill2.skill;
	}

}
