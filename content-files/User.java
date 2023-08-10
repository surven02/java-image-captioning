import java.util.ArrayList;
//takes in several objects//
public class User {
	private String userName;
	private ArrayList<Meme> memesCreated;
	private ArrayList<Meme> memesViewed;
	
	public User() {
		this.userName = "";
		this.memesViewed = new ArrayList<Meme>();
		this.memesCreated = new ArrayList<Meme>();
		
	}

	// A constructor that accepts a username as a string //
	public User(String userName) {
		this.userName = userName;
		this.memesCreated = new ArrayList<Meme>();
		this.memesViewed = new ArrayList<Meme>();
	}
	//implement the Comparable interface and provide the compareTo
	//method that orders Users and returns positive and negative values after comparing the parameter 
	//and the userName
	public int compareTo(User b1) {
		int uservalue = 0;
		uservalue = this.userName.compareTo(b1.getUserName());
		if(uservalue > 0) {
			return 1;
		}
		if(uservalue < 0 ) {
			return -1;
		}
		uservalue = (this.memesCreated).size()-b1.memesCreated.size();
		if (uservalue < 0) {
			return 1;
		}
		if (uservalue > 0) {
			return -1;
		}
		
		return 0;
	}



	public String getUserName() {
		return userName;
	}
	

	public void setUserName(String username) {
		this.userName = username;
	}

	public ArrayList<Meme> getMemesCreated() {
		return memesCreated;
	}

	public void setMemesCreated(ArrayList<Meme> memelist) {
		this.memesCreated = memelist;
	}

	public ArrayList<Meme> getMemesViewed() {
		return memesViewed;
	}

	public void setMemesViewed(ArrayList<Meme> memelist) {
		this.memesViewed = memelist;
	}
	//Accepts a meme argument and an int and records whether the Meme has been seen by the user and provide a rating//
	public void rateMeme(Meme meme, int rating) {
		ArrayList<Meme> memelist = getMemesViewed();
		memelist.add(meme);
		setMemesViewed(memelist);
		
		Rating ratingone =  new Rating();
		ratingone.setUser(this);
		ratingone.setScore(rating);
		meme.addRating(ratingone);
	}

	//takes in the argument and creates a meme object//
	public Meme createMeme(BackgroundImage bgimage, String caption) {
		Meme meme = new Meme(bgimage,caption,this);
		this.memesCreated.add(meme);
		return meme;
	}
	//deletes the contents within the meme object if the taken in arguments return a boolean value of false//
	public boolean deleteMeme(Meme meme) {
		ArrayList<Meme> memelist = getMemesCreated();
		if(memelist.contains(meme) && (meme.getShared() == false)) {
			memelist.remove(meme);
			return true;
		}
		return false;
	}
	// classes the meme as shared and overlays a copy within the <ArrayList>
	public void shareMeme(Meme meme, Feed feed) {
		meme.setShared(true);
		ArrayList<Meme> memelist = feed.getMemes();
		memelist.add(meme);
	}
	// accepts a feed argument and an int and returns a True or False value //
	public boolean rateNextMemeFromFeed(Feed feed, int ratingScore) {
		Meme memetwo = feed.getNewMeme(this);
		while (memetwo != null) {
			rateMeme(memetwo, ratingScore);
			return true;
		}
		return false;
	}
	
	//returns an average value of all the ratings//
	public double calculateReputation() {
		double sum = 0.0;
		double numberOfRatings = 0.0;
		if ((getMemesCreated().size() != 0) ||(getMemesViewed().size() != 0)){
			for(Meme meme: getMemesCreated()) {
				sum += meme.calculateOverallRating();
				numberOfRatings+= 1.0;
			}
		
		return sum/numberOfRatings;
	}else {
		return sum;
	}
	}
	
	
	// a method to determine how many memes have been viewed or rated
	@Override
	public String toString() {
		String userName = getUserName();
		int numberofElements = getMemesViewed().size();
		String i = String.valueOf(numberofElements);
		double reputationValue = calculateReputation();
		double roundedReputationValue = Math.round(reputationValue * 10.0)/10.0;
		String d = String.valueOf(roundedReputationValue);
		
		return userName + " has rated (" + i + ") memes, " + "(" + d + ")" ; 
		
	}
	// Determines whether the given username is similar to the paramater username //
	@Override
	public boolean equals(Object object) {
		if(object == null) {
			return false;
		}
		if(object instanceof User) {
			User username = (User) object;
			return(this.userName.equals(username.userName));
		}
		return false;
	}
}