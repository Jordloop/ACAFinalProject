package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Review
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;
    private String content;
    private LocalDate reviewDate;
    private int productId;
    private int userId;
    private int stars;
    private String title;

    public int getReviewId()
    {
        return reviewId;
    }

    public String getContent()
    {
        return content;
    }

    public LocalDate getReviewDate()
    {
        return reviewDate;
    }

    public int getProductId()
    {
        return productId;
    }

    public int getUserId()
    {
        return userId;
    }

    public int getStars()
    {
        return stars;
    }

    public String getTitle()
    {
        return title;
    }
}
