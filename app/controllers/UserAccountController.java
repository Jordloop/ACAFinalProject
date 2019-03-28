package controllers;

import models.Product;
import models.UserAccount;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import javax.security.auth.login.AccountException;
import java.util.List;

public class UserAccountController extends Controller
{

    private JPAApi db;
    private FormFactory formFactory;

    @Inject
    public UserAccountController(JPAApi db, FormFactory formFactory)
    {
        this.db = db;
        this.formFactory = formFactory;
    }


    @Transactional(readOnly = true)
    public Result getUserAccount(int userId)
    {
        String query = "SELECT au FROM UserAccount au WHERE userId = :userId";
        TypedQuery<UserAccount> userAccountQuery = db.em().createQuery(query, UserAccount.class);
        userAccountQuery.setParameter("userId", userId);
        UserAccount user = userAccountQuery.getSingleResult();

        List<Product> userProducts = getAllProductsByUserAccountId(userId);

        return ok(views.html.user.render(user, userProducts));
    }



    @Transactional(readOnly = true)
    private List<Product> getAllProductsByUserAccountId(int userId)
    {
        String query = "SELECT p FROM Product p WHERE userId = :userId ORDER BY productId";
        TypedQuery<Product> allProductsQuery = db.em().createQuery(query, Product.class);
        allProductsQuery.setParameter("userId", userId);
        return allProductsQuery.getResultList();
    }


}
