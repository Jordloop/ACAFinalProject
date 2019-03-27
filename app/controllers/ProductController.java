package controllers;

import com.google.common.io.Files;
import models.Product;
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

        TypedQuery<Product> query = db.em()
                .createQuery("SELECT p FROM Product p WHERE productId = :productId", Product.class);
        query.setParameter("productId", productId);
        Product product = query.getSingleResult();


        return ok(views.html.product.render(product));
    }

    @Transactional(readOnly = true)
    public Result getProducts()
    {
        TypedQuery<Product> query = db.em()
                .createQuery("SELECT p FROM Product p ORDER BY productName, productId", Product.class);
        List<Product> products = query.getResultList();

        return ok(views.html.products.render(products));
    }

    @Transactional(readOnly = true)
    public Result getProductPicture(int productId)
    {
        TypedQuery<Product> query = db.em()
                .createQuery("SELECT p FROM Product p WHERE productId = :productId", Product.class);
        query.setParameter("productId", productId);
        Product product = query.getSingleResult();

        return ok(product.getPicture());
    }

    public Result getProductAdd()
    {

        return ok(views.html.productadd.render());
    }

    @Transactional
    public Result postProductAdd()
    {
        Product product = new Product();

        DynamicForm form = formFactory.form().bindFromRequest();

        final String NAME = form.get("name");
        final BigDecimal ITEM_COST = new BigDecimal(form.get("itemCost"));

        final int UNITS_IN_STOCK = Integer.parseInt(form.get("unitsInStock"));
        final int USER_ID = Integer.parseInt(form.get("userId"));
        final String DESCRIPTION = form.get("description");
        final int CATEGORY_ID = Integer.parseInt(form.get("categoryId"));

//        String hobby = form.get("hobbyId");
//        Integer hobbyId;
//        if(hobby != null && hobby.length() > 0)
//        {
//            hobbyId = Integer.parseInt(hobby);
//        }
//        else
//        {
//            hobbyId = null;
//        }


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

        return ok(views.html.product.render(product));
    }


}
