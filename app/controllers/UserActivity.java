package controllers;

import models.Admin;
import models.User;
import models.Tool;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;


import java.util.List;

import static play.data.Form.form;

/**
 * This is the User Controller. This is where we implement all of the Action
 * methods related to operations on my User.
 */
public class UserActivity extends Controller {

    // Route: GET /user/new
    //  Displays the form for creating a new user account.
    public Result createForm() {
        return ok(views.html.user.createform.render());
    }

    // Route: GET /user/login/form
    //  Display the form for user login form
    public Result loginForm() { 
    	return ok(views.html.user.loginform.render());
    }

    // Route: POST /user/login
    //  Login registered user
    public Result login() {
        DynamicForm userForm = Form.form().bindFromRequest();
        String username = userForm.data().get("userName");
        String password = userForm.data().get("password");

        Admin admin = Admin.find.where().eq("userName", username).findUnique();
        User user = User.find.where().eq("userName", username).findUnique();

        if(user != null && user.authenticate(password)) {
            session("user_id", user.id.toString());
            flash("success", "Welcome back " + user.userName);

        } else if(admin != null && admin.authenticate(password)) {
            session("admin_id", admin.id.toString());
            flash ("success", "Welcome admin " + admin.userName);
   
        } else {
            flash("error", "Invalid login. Check your username and password.");
        }
        return redirect(routes.UserActivity.show());
    }

    // Route: POST /user
    //  Creates a User account from user request input.
    public Result create() {
        DynamicForm userForm = Form.form().bindFromRequest();
        String username = userForm.data().get("userName");
        String password = userForm.data().get("password");
        String firstname = userForm.data().get("firstName");
        String lastname = userForm.data().get("lastName");
        String phonenum = userForm.data().get("phoneNum");

        User user = User.createNewUser(username, password, firstname, lastname, phonenum);

        if(user == null) {
            flash("error", "Invalid user, please enter all required informations");
            return redirect(routes.UserActivity.createForm());
        }

        user.save();

        flash("success", "Welcome new user " + user.userName);
        session("user_id", user.id.toString());
        return redirect(routes.UserActivity.show());
    }

    // Route: DELETE /user/:id
    //  Delete a User account from data base.
    public Result delete() {
        return ok();
    }

    // Route: PUT   /user/:id
    //  Update a User account by using it's registered form.
    public Result update() {
        return ok();
    }

    // Route: GET /users/:id
    //  Shows the User profile 'id'
    public Result show() {
        List<Tool> tools = Tool.find.all();

        return ok(views.html.user.index.render(tools));
    }

    public Result showAdmin() {
        return ok();
    }


    public Result profile() {
        return ok(views.html.user.profile.render());
    }

    public Result logout() {
        session().remove("user_id");
        return redirect(routes.Application.index());
    }

}