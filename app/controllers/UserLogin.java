package controllers;

import models.User;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;

/**
 * Created by alex_alarcon on 11/16/2015.
 */
public class UserLogin extends Controller {

    //Route: GET /user/login/form
    //Display the form for user login form
    public Result loginForm() { return ok(views.html.user.loginform.render()); }

    //Route: POST /user/login
    //Login registered user
    public Result login() {
        DynamicForm userForm = Form.form().bindFromRequest();
        String username = userForm.data().get("username");
        String password = userForm.data().get("password");

        User user = User.find.where().eq("username", username).findUnique();

        if(user != null && user.authenticate(password)) {
            session("user_id", user.id.toString());
            flash("success", "Welcome back " + user.userName);
        } else {
            flash("error", "Invalid login. Check your username and password.");
        }
        //need to implement redirect page after login is confirmed
        return ok();
    }

}
