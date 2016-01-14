package controllers;

import models.User;
import play.mvc.*;

/**
 * This is the About Controller. This is where we implement all of the Action
 * methods related to operations on the About page.
 */
public class About extends Controller {

    public Result show() {
        Long userId = Long.parseLong(session().get("user_id"));
        User user = User.find.byId(userId);

        return ok(views.html.about.about.render(user));
    }
}
