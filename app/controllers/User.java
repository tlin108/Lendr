package controllers;

import play.data.Form;
import play.mvc.*;

import java.util.List;


/**
 * This is the User Controller. This is where we implement all of the Action
 * methods related to operations on my User.
 */
public class User extends Controller {

    // Route: GET /user/new
    //  Displays the form for creating a new user account.
    public Result form() {
        return ok(views.html.user.form.render());
    }

    // Route: POST /user
    //  Creates a User account from user request input.
    public Result create() {
        return ok();
    }

    // Route: DELETE /user/:id
    //  Delete a User account from data base.
    public Result delete(String id) {
        return ok();
    }

    // Route: PUT   /user/:id
    //  Update a User account by using it's registered form.
    public Result update(String id) {
        return ok();
    }

    // Route: GET /users/:id
    //  Shows the User profile 'id'
    public Result show(String id) {
        return ok("This is the user/:id page for user profile of id: " + String.valueOf(id) + "\n");
    }
}
