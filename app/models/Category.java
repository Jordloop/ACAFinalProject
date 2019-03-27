package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Category
{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    private String name;

    public int getCategoryId()
    {
        return categoryId;
    }

    public String getName()
    {
        return name;
    }
}
