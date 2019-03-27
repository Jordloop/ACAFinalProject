package models;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class ProductDetail
{

    @Id
    private int productId;

    private String productName;
    private BigDecimal itemCost;
    private int unitsInStock;
    private byte[] pricure;
    private String description;

    private String userFirstName;
    private String userLastName;
    private int userId;

    private String categoryName;

    private String reviewContent;
    private String title;
    private LocalDate reviewDate;
    private int stars;
    private String reviewUserFirstName;
    private String reviewUserLastName;





    public ProductDetail()
    {

    }

}
