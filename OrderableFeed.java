import java.util.Collections;

public class OrderableFeed extends Feed {
	
	
	//reorders the feed by caption, using Meme’s natural ordering
	public void sortByCaption() {
		Collections.sort(getMemes());
	}
	//- reorders the feed by rating, using the CompareMemeByRating
	//comparator
	public void sortByRating() {
		Collections.sort(getMemes(), new CompareMemeByRating());
	}
	//reorders the feed by creator, using the CompareMemeByCreator
	//comparator
	public void sortByCreator() {
		Collections.sort(getMemes(), new CompareMemeByCreator());
	}
	
	
	
}