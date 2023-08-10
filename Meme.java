import java.util.*;
public class Meme implements Comparable<Meme>{
	private User creator;
	private BackgroundImage backgroundImage;
	private Rating[] ratings; 
	private String caption, captionVerticalAlign;
	private boolean shared;
	
	public Meme() {
		this.creator = new User();
		this.backgroundImage  = new BackgroundImage();
		this.ratings = new Rating[10];
		this.caption = "";
		this.captionVerticalAlign = "bottom";
		this.shared = false;
	}
	
	public Meme(BackgroundImage image, String caption, User creator) {
		this.ratings = new Rating[10] ;
		this.captionVerticalAlign = "bottom";
		this.backgroundImage = image;
		this.caption = caption;
		this.creator = creator;			
	}
	
	
	
	
	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public BackgroundImage getBackgroundImage() {
		return backgroundImage;
	}

	public void setBackgroundImage(BackgroundImage image) {
		this.backgroundImage = image;
	}

	public Rating[] getRatings() {
		return ratings;
	}

	public void setRatings(Rating[] rating) {
		this.ratings = rating;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getCaptionVerticalAlign() {
		return captionVerticalAlign;
	}

	public boolean setCaptionVerticalAlign(String captionVerticalAlign) {
		if (captionVerticalAlign.equals("top") ||captionVerticalAlign.equals("middle") ||captionVerticalAlign.equals("bottom")) {
			this.captionVerticalAlign = captionVerticalAlign;
			return true;
		}
			return false;
		}

	public boolean getShared() {
		return shared;
	}

	public void setShared(boolean value) {
		this.shared = value;
	}
	

	public boolean addRating(Rating rating) {
		if(ratings[9] == null) {
			for(int i = 0; i<10;i++) {
				if(ratings[i] == null) {
					ratings[i] = rating;
					return true;
				}
			}
			} else {
				for (int a=0 ; a<ratings.length-1 ; a++) {
					ratings[a] = ratings[a+1];
				ratings[ratings.length-1] = rating;
				}
				return true;

			}
		return false;
	}
	private int getPositiveRatings() {
		int total = 0;
		for (int a=0 ; a<ratings.length ; a++) {
			if (ratings[a] != null) {
				if (ratings[a].getScore() > 0) total++;
			}
		}
		return total;
	}
	private int getNegativeRatings() {
		int total = 0;
		for (int a=0 ; a<ratings.length ; a++) {
			if (ratings[a] != null) {
				if (ratings[a].getScore() < 0) total++;
			}
		}
		return total;
	}
		

	public double calculateOverallRating() {
		double sum = 0.0;
		for (int a=0 ; a<ratings.length ; a++) {
			if (ratings[a] != null) {
				sum += ratings[a].getScore();
			}
			if (ratings[a] == null) {
				sum += 0.0;
			}
		}
		return sum;
	}

	//Returns the captions overall rating//
	@Override
	public String toString() {
		return backgroundImage.toString() + " '" + caption + "' " + 
				calculateOverallRating() + " [+1: " + getPositiveRatings() + ", -1: " + 
				getNegativeRatings() + "]" + " - created by " + getCreator().getUserName();
	}
	

	@Override
	public boolean equals(Object object){
		if( object instanceof Meme) {
			if (((Meme)object).getCreator().equals(creator) && ((Meme)object).getCaption().equals(caption) && ((Meme)object).getBackgroundImage().equals(backgroundImage)) { 
				return true;
		}
		return false;
	}
		return false;
}
	
	public static void main(String[] args) {
		
		//testing BackgroundImage Constructor
		BackgroundImage backgroundimageone = new BackgroundImage("Joker", "How bots laugh", "Image of Joquain Phoenix in his role as Joker, laughing maniacally" );
		BackgroundImage backgroundimagetwo = new BackgroundImage("fish", "How fish swim", "Image of Fish" );
		BackgroundImage backgroundimagethree = new BackgroundImage("fish", "How fish swim", "Image of Fish" );
		BackgroundImage image1 = new BackgroundImage("Leo DiCaprio", "A smirk and a nod", "Image of Leonardo DiCaprio in his role as Jay Gatsby, smirking and enjoying a drink" );
		BackgroundImage image2 = new BackgroundImage("Monkey", "How monkeys climb", "Picture of Monkey" );
		BackgroundImage image3 = new BackgroundImage("monkey", "How monkeys climb", "Picture of Monkey" );
		
		
		
		//toString
		System.out.println(image1.toString());
		System.out.println(image2.toString());
		//equals
		System.out.println(image1.equals(image2));
		System.out.println(image2.equals(image3));
		
		
	
		//testing Rating Constructor
		User sv = new User();
		User Venkat = new User();
		User Suri = new User();
		sv.setUserName("sv");
		Venkat.setUserName("Venkat");
		Suri.setUserName("sv");
		//testing Ratings Constructor, toString
		Rating[] rating = new Rating[10];
		for(int x = 0; x < rating.length; x++) {
			rating[x] = new Rating(sv, (int)(Math.random()*4-2));
			System.out.println(rating[x].toString());
		}
		Rating[] firstrating = new Rating[10];
		for(int x = 0; x < firstrating.length; x++) {
			firstrating[x] = new Rating(sv, (int)(Math.random()*4-2));
			System.out.println(firstrating[x].toString());
		}
		
		//equals test
		System.out.println(rating.equals(firstrating));
			
		//Meme Constructor
		Meme memeone = new Meme(image1, "fish", sv);
		Meme memetwo = new Meme(image2, "animals", Venkat);
		Meme meme3 = new Meme(image3, "fish", sv);
		//Meme toString()
		System.out.println(memeone.toString());
		System.out.println(memetwo.toString());
		//Meme equals()
		System.out.println(memeone.equals(memetwo));
		System.out.println(memeone.equals(meme3));
		
		//calculateOverallRating
		System.out.println(memeone.calculateOverallRating());
		System.out.println(memetwo.calculateOverallRating());
		
		//addRating
		System.out.println(memeone.addRating(new Rating(sv, 1)));
		System.out.println(memetwo.addRating(new Rating(Venkat, -1)));
		
		//captionVerticalAlign
		System.out.println(memeone.setCaptionVerticalAlign("top"));
		System.out.println(memeone.setCaptionVerticalAlign("corner"));
		
		//setScore
//		System.out.println(ratings1.setScore(1));
//		System.out.println(ratings.setScore(-1));
		// User Constructor
		
		
		User Surya = new User("surven02");
		System.out.println(Surya.getUserName());
		User Will = new User("pumpkinspicelatte");
		System.out.println(Will.getUserName());
		
		//User, Feed, Meme, Rating toString() methods
		Meme meme = new Meme(backgroundimageone, "fish", Surya);
		Meme meme2 = new Meme(backgroundimagetwo, "animals", Will);
		
		Feed feedone = new Feed();
		Feed feedtwo = new Feed();
		
		ArrayList<Meme> firstmemelist = new ArrayList<Meme>();
		firstmemelist.add(meme);
		firstmemelist.add(memetwo);
		Surya.setMemesCreated(firstmemelist);
		feedone.setMemes(firstmemelist);
		
		System.out.println(feedone.toString());
		System.out.println(feedtwo.toString());
		
		System.out.println(memeone.toString());
		System.out.println(memetwo.toString());
		
		System.out.println(Surya.toString());
		System.out.println(Will.toString());
		
		
		//Ratings
		Rating[] ratings = new Rating[10];
		for(int x = 0; x < ratings.length; x++) {
			ratings[x] = new Rating(Surya, (int)(Math.random()*4-2));
			System.out.println(ratings[x].toString());
		}
			
		Rating[] ratings1 = new Rating[10];
			for(int x = 0; x < ratings1.length; x++) {
				ratings1[x] = new Rating(Will, (int)(Math.random()*4-2));
				System.out.println(ratings1[x].toString());
			}	
		// User equals() method
		User Jake = new User("doglover");
		System.out.println(Surya.equals(Will));	
		System.out.println(Will.equals(Jake));
			
		// Feed getNewMeme()
		System.out.println(feedone.getNewMeme(Surya));
		System.out.println(feedone.getNewMeme(Will));
		
		// User rateMeme()
		Will.rateMeme(memetwo, 1);
		System.out.println(memetwo.toString());
		System.out.println(Will.getMemesViewed());
		Surya.rateMeme(memeone,0);
		System.out.println(memeone.toString());
		System.out.println(Will.getMemesViewed());
	
		
		
		// User rateNextMemeFromFeed()
		System.out.println(Jake.rateNextMemeFromFeed(feedone, -1)); // output should be true (confirmed)
		System.out.println(Surya.rateNextMemeFromFeed(feedone, 0)); // output should be false (confirmed)
		
		//	 User createMeme()
		Will.createMeme(backgroundimagethree, "the best output to the input");
		System.out.println(Will.getMemesCreated());
		BackgroundImage backgroundimagefour = new BackgroundImage("Test Trial", "Surya Uses Testers", "Awesome!");
		Surya.createMeme(backgroundimagefour, "test cycle");
		System.out.println(Surya.getMemesCreated());
		
		 //User deleteMeme()
		Meme meme4 = new Meme();
		System.out.println(Surya.deleteMeme(memeone)); // should output true (confirmed)
		System.out.println(Surya.deleteMeme(meme4)); // output should be false since its not created by Surya (confirmed)
		
		// User shareMeme()
		Will.shareMeme(meme4,feedone);
		System.out.println(meme4.shared); // should output true 
		System.out.println(feedone.toString()); // should print the feed with the added meme (meme4 is empty but still prints)
		Meme memethree = new Meme();
		System.out.println(meme3.shared); //should output false (default value is unshared = false for this test) 
		
		// User calculateReputation();
		System.out.println(Surya.calculateReputation()); //output a nonzero number
		System.out.println(Will.calculateReputation());	 //output 0.0
		
		CompareMemeByRating surya = new CompareMemeByRating();
		Meme meme1 = new Meme();
		System.out.println(surya.compare(meme1, meme2));
		System.out.println(surya.compare(meme3, meme4));
		
		CompareMemeByCreator suryav = new CompareMemeByCreator();
		System.out.println(suryav.compare(meme1, meme2));
		System.out.println(suryav.compare(meme3, meme4));
		
		Meme bgimage = new Meme();
		Meme bgimage2 = new Meme();
		System.out.println(bgimage.compareTo(bgimage2));
		Meme bgimage3 = new Meme();
		Meme bgimage4 = new Meme();
		System.out.println(bgimage3.compareTo(bgimage4));
		
		System.out.println(meme1.compareTo(meme2));
		System.out.println(meme2.compareTo(meme3));
		System.out.println(meme1.compareTo(meme3));
		
		Meme user1 = new Meme();
		Meme user2 = new Meme();
		System.out.println(user1.compareTo(user2));
		System.out.println(user1.compareTo(user1));
		
		OrderableFeed orderablefeed = new OrderableFeed();

		User user3 = new User();
		System.out.println(orderablefeed.getNewMeme(user3));
		User user4 = new User();
		System.out.println(orderablefeed.getNewMeme(user4));
		
		

		ArrayList<Meme> memeslist = new ArrayList<Meme>();
		memeslist.add(meme1);
		memeslist.add(meme2);
		orderablefeed.setMemes(memeslist);
		System.out.println("Before" + memeslist);//Before it is ordered
		orderablefeed.sortByCaption();
		System.out.println("After" + memeslist);//After it is ordered
		System.out.println("Before" + memeslist);//Before it is ordered
		orderablefeed.sortByRating();
		System.out.println("After" + memeslist);//After it is ordered
		System.out.println("Before" + memeslist);//Before it is ordered
		orderablefeed.sortByCreator();
		System.out.println("After" + memeslist);//After it is ordered
		OrderableFeed orderablefeed2 = new OrderableFeed();
		ArrayList<Meme> secondmemelist = new ArrayList<Meme>();
		secondmemelist.add(meme3);
		secondmemelist.add(meme4);
		orderablefeed2.setMemes(secondmemelist);
		System.out.println("Before" + secondmemelist);//Before it is ordered
		orderablefeed2.sortByCaption();
		System.out.println("After" + secondmemelist);//After it is ordered
		System.out.println("Before" + secondmemelist);//Before it is ordered
		orderablefeed2.sortByRating();
		System.out.println("After" + secondmemelist);//After it is ordered
		System.out.println("Before" + secondmemelist);//Before it is ordered
		orderablefeed2.sortByCreator();
		System.out.println("After" + secondmemelist);;//After it is ordered
}

	@Override
	//implement the Comparable interface and provide the compareTo
	//method that orders Memes based on a positive or negative value
	public int compareTo(Meme m1) {
		int exmval = 0;
		exmval = this.caption.compareTo(m1.getCaption());
		if (exmval > 0) {
			return 1;
		}
		if (exmval < 0) {
			return -1;
		}
		exmval = this.backgroundImage.compareTo(m1.getBackgroundImage());
		if (exmval > 0) {
			return 1;
		}
		if (exmval < 0) {
			return -1;
		}
		double valdouble = 0;
		valdouble = this.calculateOverallRating() - m1.calculateOverallRating();
		if(valdouble > 0) {
			return -1;}
		//return appropriate values
		if(valdouble < 0) {
			return 1;
			}
		if (this.shared == true && m1.shared == false)
			return -1;
		else if (m1.shared == true && this.shared == false)
			return 1;
		else
			return 0;
		}
	}

