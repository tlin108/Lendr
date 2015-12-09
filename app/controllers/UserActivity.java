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
        //Create admin credentials
        Admin admin = new Admin("admin", "adminpass");
        boolean userLogin = false;
        boolean adminLogin = false;

        //Get username and password entered by user
        DynamicForm userForm = Form.form().bindFromRequest();
        String username = userForm.data().get("userName");
        String password = userForm.data().get("password");

        //Find unique username from db
        User user = User.find.where().eq("userName", username).findUnique();

        //Authenticate user's password
        if(user != null && user.authenticate(password)) {
            session("user_id", user.id.toString());
            flash("success", "Welcome back " + user.userName);
            userLogin = true;

        //Authenticate admin's password
        } else if(username.equals(admin.userName) && admin.authenticate(password)) {
            adminLogin = true;

        } else {
            flash("error", "Invalid login. Check your username and password.");
            return redirect(routes.UserActivity.loginForm());
        }

        if(userLogin)
            return redirect(routes.UserActivity.show());
        else
            return redirect(routes.UserActivity.showAdmin());
    }

    // Route: POST /user
    //  Creates a User account from user request input.
    public Result create() {
        DynamicForm userForm = Form.form().bindFromRequest();
        String username = userForm.data().get("userName");
        String password = userForm.data().get("password");
        String firstname = userForm.data().get("firstName");
        String lastname = userForm.data().get("lastName");
        String email = userForm.data().get("email");
        String address = userForm.data().get("address");
        String phonenum = userForm.data().get("phoneNum");

        User user = User.createNewUser(username, password, firstname, lastname, email, address, phonenum);

        if(user == null) {
            flash("error", "Invalid user, please enter all valid informations");
            return redirect(routes.UserActivity.createForm());
        }

        user.save();

        flash("success", "Welcome new user " + user.userName);
        session("user_id", user.id.toString());
        return redirect(routes.UserActivity.show());
    }

    // Route: DELETE /user/:id
    //  Delete a User account from data base.
    //@Security.Authenticated(UserAuth.class)
    public Result delete() {
        return ok();
    }

    // Route: PUT   /user/:id
    //  Update a User account by using it's registered form.
    //@Security.Authenticated(UserAuth.class)
    public Result update() {
        return ok();
    }

    // Route: GET /users/:id
    //  Shows the User profile 'id'
    @Security.Authenticated(UserAuth.class)
    public Result show() {
        List<Tool> tools = Tool.find.all();
        return ok(views.html.user.index.render(tools));
    }
    //Route: GET /admin
    //Shows the admin homepage
    public Result showAdmin() {
        return ok(views.html.admin.index.render());
    }

    //Roure: GET/profile
    //Shows user profile
    @Security.Authenticated(UserAuth.class)
    public Result profile() {
        Long userId = Long.parseLong(session().get("user_id"));
        User user = User.find.byId(userId);

        return ok(views.html.user.profile.render(user));
    }

    public Result logout() {
        session().remove("user_id");
        return redirect(routes.Application.index());
    }

}
