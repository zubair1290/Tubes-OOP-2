/**
 * Skill
 */
public class Skill {
	protected int basePower;
	protected int masteryLevel;
	protected String skill;
	List <Element> elements;
	protected int jumlahElements;
	
	public Skill(String s, int b, int m, int je, Element[] e) {
		this.basePower = b;
		this.masteryLevel = m;
		this.skill = s;
		this.elements = new ArrayList<Element>(je);
		for (i = 0; i < je; i++){
			elements.add(e[i]);
		}
		this.jumlahElements = je;
	}
	public int getPower(){
		return this.basePower;
	}
	public List<Element> getElements(){
		return this.elements;
	}   
}
