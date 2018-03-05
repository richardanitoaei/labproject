package base_statistics;

/**
 * Provides a common interface for Statistics, used in StatisticPanel and StatisticBox to hold specific data
 * from the data set.
 * @author patrick
 * @see Statistic
 * @see StatisticBox
 */
public class Statistic {

	private String title, contents;
	private boolean isDisplayed;
	
	public Statistic() {
		
		title = contents = "";
		isDisplayed = false;
		
	}
	
	/**
	 * Assigns values to the title and contents field.
	 * @param title a very brief description of the statistic
	 * @param contents an Integer
	 */
	public Statistic(String title, int contents) {
		
		this.title = title;
		this.contents = String.valueOf(contents);
		isDisplayed = false;
		
	}
	
	/**
	 * Assigns values to the title and contents field.
	 * @param title a very brief description of the statistic
	 * @param contents a String
	 */
	public Statistic(String title, String contents) {
		
		this.title = title;
		this.contents = contents;
		isDisplayed = false;
		
	}
	
	/**
	 * Returns the statistics title
	 * @return the title of the statistic
	 */
	public String getTitle() {
		
		return title;
		
	}
	
	/**
	 * Returns the contents of the statistic
	 * @return
	 */
	public String getContents() {
		
		return contents;
		
	}
	
	/**
	 * Returns whether the statistic is currently displayed
	 * @return
	 */
	public boolean getIsDisplayed() {
		
		return isDisplayed;
		
	}
	
	/**
	 * Sets whether the statistic is currently displayed
	 * @param isDisplayed
	 */
	public void setIsDisplayed(boolean isDisplayed) {
		
		this.isDisplayed = isDisplayed;
		
	}
	
	/**
	 * Returns a default string representation of the statistic
	 */
	public String toString() {
		
		return title + ", " + contents + ", " + isDisplayed;
		
	}
	
}
