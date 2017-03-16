import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Review {
    private String authorName;
    private String reviewText;
    private int rating;
    private String handle;
    private Date date;

    public Review(String authorName, String reviewText, int rating, String handle, Date date) {
        this.authorName = authorName;
        this.reviewText = reviewText;
        this.rating = rating;
        this.handle = handle;
        this.date = date;
    }

    public String getAuthorName() {
        return this.authorName.replace("[", "").replace("]", "");
    }

    public String getReviewText() {
        return this.reviewText.replace("[", "").replace("]", "");
    }

    public int getRating() {
        return this.rating;
    }

    public String getHandle() {
        return this.handle.replace("[", "").replace("]", "");
    }

    public Date getDate() {
        return this.date;
    }
}
