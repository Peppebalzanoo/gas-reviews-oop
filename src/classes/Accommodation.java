package classes;

import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Accommodation extends Structure{
	
	private Integer id;
	private String rooms;
	private Integer structure_reference;
	ArrayList<String> typeList;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRooms() {
		return rooms;
	}
	public void setRooms(String rooms) {
		this.rooms = rooms;
	}
	public Integer getStructure_reference() {
		return structure_reference;
	}
	public void setStructure_reference(Integer structure_reference) {
		this.structure_reference = structure_reference;
	}
	public ArrayList<String> getTypeList() {
		return typeList;
	}
	public void setTypeList(ArrayList<String> typeList) {
		this.typeList = typeList;
	} 
	

	public Accommodation(Integer accommodation_id, String title, String country, String address, String price, Integer rating_average, ImageIcon picture, Integer structure_reference,  String rooms, ArrayList<String> typeList) {
		super(structure_reference, title, country, address, price, rating_average, picture);
		
		this.setRooms(rooms);
		this.setStructure_reference(structure_reference);
		this.setTypeList(typeList);
		
	}

}
