package controllers;

import models.User;
import play.mvc.*;

/**
 * Created by alex_alarcon on 12/18/2015.
 */
public class About extends Controller {

    public Result show() {
        Long userId = Long.parseLong(session().get("user_id"));
        User user = User.find.byId(userId);

        return ok(views.html.about.about.render(user));
    }
}
