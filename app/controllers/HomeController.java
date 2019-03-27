package controllers;

import play.mvc.Controller;
import play.mvc.Result;

public class HomeController extends Controller
{
    public Result getHomePage()
    {

        return ok(views.html.home_page.render());
    }
}
