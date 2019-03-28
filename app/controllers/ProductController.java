package controllers;

import com.google.common.io.Files;
import models.Product;
import models.Review;
import models.UserAccount;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProductController extends Controller
{
    private JPAApi db;
    private FormFactory formFactory;

    @Inject
    public ProductController(JPAApi db, FormFactory formFactory)
    {
        this.db = db;
        this.formFactory = formFactory;
    }

    @Transactional(readOnly = true)
    public Result getProduct(int productId)
    {
        Product product = getProductByProductId(productId);
        List<Review> reviews = getReviewsByProductId(productId);
        UserAccount userAccount = getUserAccountById(product.getUserId());
        return ok(views.html.product.render(product, reviews, userAccount));
    }

    @Transactional(readOnly = true)
    public Result getProducts()
    {
        List<Product> products = getAllProducts();
        return ok(views.html.products.render(products));
    }

    @Transactional(readOnly = true)
    public Result getProductPicture(int productId)
    {
        Product product = getProductByProductId(productId);
        return ok(product.getPicture());
    }

    public Result getProductAdd()
    {
        return ok(views.html.product_add.render());
    }

    @Transactional
    public Result postProductAdd()
    {
        List<Product> products = getAllProducts();

        Product product = new Product();
        DynamicForm form = formFactory.form().bindFromRequest();

        final String NAME = form.get("name");
        final BigDecimal ITEM_COST = new BigDecimal(form.get("itemCost"));
        final int UNITS_IN_STOCK = Integer.parseInt(form.get("unitsInStock"));
        final int USER_ID = Integer.parseInt(form.get("userId"));
        final String DESCRIPTION = form.get("description");
        final int CATEGORY_ID = Integer.parseInt(form.get("categoryId"));

        Http.MultipartFormData<File> formData = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> filePart = formData.getFile("picture");
        File file = filePart.getFile();
        byte[] picture;
        try
        {
            picture = Files.toByteArray(file);
            if (picture != null && picture.length > 0)
            {
                product.setPicture(picture);
            }
        } catch (Exception e)
        {
            picture = null;
        }

        product.setProductName(NAME);
        product.setItemCost(ITEM_COST);
        product.setUnitsInStock(UNITS_IN_STOCK);
        product.setUserId(USER_ID);
        product.setDescription(DESCRIPTION);
        product.setCategoryId(CATEGORY_ID);

        db.em().persist(product);

        return ok(views.html.products.render(products));
    }

    @Transactional(readOnly = true)
    public Result getProductEdit(int productId)
    {
        Product product = getProductByProductId(productId);
        return ok(views.html.product_edit.render(product));
    }

    @Transactional
    public Result postProductEdit(int productId)
    {
        List<Review> reviews = getReviewsByProductId(productId);
        Product product = getProductByProductId(productId);
        UserAccount userAccount = getUserAccountById(product.getUserId());

        DynamicForm form = formFactory.form().bindFromRequest();

        final String NAME = form.get("name");
        final BigDecimal ITEM_COST = new BigDecimal(form.get("itemCost"));
        final int UNITS_IN_STOCK = Integer.parseInt(form.get("unitsInStock"));
        final int USER_ID = Integer.parseInt(form.get("userId"));
        final String DESCRIPTION = form.get("description");
        final int CATEGORY_ID = Integer.parseInt(form.get("categoryId"));

        Http.MultipartFormData<File> formData = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> filePart = formData.getFile("picture");
        File file = filePart.getFile();
        byte[] picture;
        try
        {
            picture = Files.toByteArray(file);
            if (picture != null && picture.length > 0)
            {
                product.setPicture(picture);
            }
        } catch (Exception e)
        {
            picture = null;
        }

        product.setProductName(NAME);
        product.setItemCost(ITEM_COST);
        product.setUnitsInStock(UNITS_IN_STOCK);
        product.setUserId(USER_ID);
        product.setDescription(DESCRIPTION);
        product.setCategoryId(CATEGORY_ID);

        return ok(views.html.product.render(product, reviews, userAccount));
    }


    @Transactional
    public Result postAddProductReview(int productId)
    {
        Product product = getProductByProductId(productId);
        UserAccount userAccount = getUserAccountById(product.getUserId());
        Review review = new Review();

        DynamicForm form = formFactory.form().bindFromRequest();

        final String TITLE = form.get("title");
        final int USER_ID = Integer.parseInt(form.get("userId"));
        final int STARS = Integer.parseInt(form.get("stars"));
        final String CONTENT = form.get("content");
        final int PRODUCT_ID = Integer.parseInt(form.get("productId"));
        final LocalDate DATE = LocalDate.now();

        review.setTitle(TITLE);
        review.setUserId(USER_ID);
        review.setStars(STARS);
        review.setContent(CONTENT);
        review.setProductId(PRODUCT_ID);
        review.setReviewDate(DATE);

        db.em().persist(review);

        List<Review> reviews = getReviewsByProductId(productId);

        return ok(views.html.product.render(product, reviews, userAccount));

    }








    @Transactional(readOnly = true)
    private List<Product> getAllProducts()
    {
        String query = "SELECT p FROM Product p ORDER BY productId";
        TypedQuery<Product> allProductsQuery = db.em().createQuery(query, Product.class);
        return allProductsQuery.getResultList();
    }

    @Transactional(readOnly = true)
    private List<Product> getAllProductsByUserAccountId(int userId)
    {
        String query = "SELECT p FROM Product p WHERE userId = :userId ORDER BY productId";
        TypedQuery<Product> allProductsQuery = db.em().createQuery(query, Product.class);
        allProductsQuery.setParameter("userId", userId);
        return allProductsQuery.getResultList();
    }

    @Transactional(readOnly = true)
    private UserAccount getUserAccountById(int userId)
    {
        String query = "SELECT ua FROM UserAccount ua WHERE userId = :userId";
        TypedQuery<UserAccount> userAccountQuery = db.em().createQuery(query, UserAccount.class);
        userAccountQuery.setParameter("userId", userId);
        return userAccountQuery.getSingleResult();
    }

    @Transactional(readOnly = true)
    private Product getProductByProductId(int productId)
    {
        String query = "SELECT p FROM Product p WHERE productId = :productId";
        TypedQuery<Product> productQuery = db.em().createQuery(query, Product.class);
        productQuery.setParameter("productId", productId);
        return productQuery.getSingleResult();
    }

    @Transactional(readOnly = true)
    private List<Review> getReviewsByProductId(int productId)
    {
        String query = "SELECT r FROM Review r WHERE productId = :productId";
        TypedQuery<Review> reviewsQuery = db.em().createQuery(query, Review.class);
        reviewsQuery.setParameter("productId", productId);
        return reviewsQuery.getResultList();
    }



}
