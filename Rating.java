import java.util.*;
public class Rating {
	private int score;
	private User user;

	public Rating() {
		score = 0;
		user = new User();
	}
	
	public Rating(User user, int score) {
		this.user = user;
		this.score = score;
	}
	public int getScore() {
		return score;
	}

	public boolean setScore(int score) {
		if (score == -1||score == 1 ||score == 0) {
			this.score = score;
			return true;
		}
			return false;
		}

	public User getUser() {
		return user;
	}

	public void setUser (User user) {
		this.user = user;
	}

	@Override
	
	//simple if then statement to match a specific output of strings
	public String toString() {
		String rating_type = "";
		
		if (this.getScore() == 1) {
			rating_type = "an upvote";
		}
		
		if (this.getScore() == 0) {
			rating_type = "a pass";
		}
		
		if (this.getScore() == -1) {
			rating_type = "a downvote";
		}
		
		return getUser().getUserName() + " rated as " + rating_type;
	}
	@Override
	public boolean equals(Object item) {
		if(item instanceof Rating) {
			if((((Rating) item).getScore() == score) 
					&&
					((Rating) item).getUser().equals(user)){
				return true;
			}
		}
		if(item == null) {
			return false;
		
		}
		else {
			return false;
		
	}
	}
}