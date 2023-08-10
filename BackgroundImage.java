import java.util.*;

public class BackgroundImage {
	private String imageFileName;
	private String title;
	private String description;

	public BackgroundImage() {
		imageFileName = "";
		title = "";
		description = "";
	}
	
	public BackgroundImage(String imageFileName, String title, String description) {
		this.imageFileName = imageFileName;
		this.title = title;
		this.description = description;
	}
	
	
	//use if then statements to check whether a positive or negative values returns after comparing the parameter and the imageFileName
	public int compareTo(BackgroundImage b) {
		int bgvalue = 0;
		bgvalue = this.imageFileName.compareTo(b.getImageFileName());
		if (bgvalue > 0) {
			return 1;
		}
		if (bgvalue < 0) {
			return -1;
		}
		bgvalue = this.title.compareTo(b.getTitle());
		if (bgvalue > 0) {
			return 1;
		}
		if (bgvalue < 0) {
			return -1;
		}
		bgvalue = this.description.compareTo(b.getDescription());
		if (bgvalue > 0) {
			return 1;
		}
		if (bgvalue <0) {
			return -1;
		}
		return 0;
	}
	
	//getters and setters
	public String getImageFileName() {
		return imageFileName;
	}
	
	public void setImageFileName(String name) {
		imageFileName = name;
	}
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description; 
	}
	
	@Override
	public String toString() {
		return title + " <" + description + ">";
	}
	//returns a boolean value of true if the paramater is a backgroundimage object and match the other given parameters//
	@Override
	public boolean equals(Object object) {
		if (object == null || !(object instanceof BackgroundImage)) return false;
		else if (((BackgroundImage)object).toString().equals(this.toString()) && ((BackgroundImage)object).getImageFileName().equals(this.getImageFileName())) return true;
		else return false;

	}
	public static void main(String[] args) {
		BackgroundImage bgimage = new BackgroundImage("Horse", "He is galloping", "A galloping Horse");
		System.out.println(bgimage.toString());
	}
}