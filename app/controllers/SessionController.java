package controllers;

import models.UserAccount;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import scala.concurrent.java8.FuturesConvertersImpl;

import javax.inject.Inject;
import javax.persistence.TypedQuery;
import java.util.List;

public class SessionController extends Controller
{
    private JPAApi db;
    private FormFactory formFactory;

    @Inject
    public SessionController(JPAApi db, FormFactory formFactory)
    {
        this.db =  db;
        this.formFactory = formFactory;
    }


    public Result getLogin()
    {
        return ok(views.html.login.render(""));
    }

    @Transactional(readOnly = true)
    public Result postLogin()
    {
        DynamicForm form = formFactory.form().bindFromRequest();

        String email = form.get("email");


        String sql = "SELECT ua FROM UserAccount ua WHERE email = :email";
        TypedQuery<UserAccount> query = db.em().createQuery(sql, UserAccount.class);
        query.setParameter("email", email);

        List<UserAccount> users = query.getResultList();

        Result result;

        String message;
        if (users.size() == 1)
        {
//            UserAccount user = users.get(0);
//            answer = "Pass: " + user.getFullName();
            result = redirect("/products");
        }
        else
        {
            message = "Incorrect username or password.";
            result = ok(views.html.login.render(message));
        }
        return result;
    }

}
