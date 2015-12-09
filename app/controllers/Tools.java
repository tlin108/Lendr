package controllers;

import models.User;
import models.Tool;
import models.Comment;
import play.mvc.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;


import java.util.List;


import static play.data.Form.form;

/**
 * This is a Tool controller that implements RESTful routes. You can test
 * these routes using the CURL utility.
 */
public class Tools extends Controller {

    // Route: GET /tool
    public Result index() {
        // Need to find a way to filter the list of tool by ownerId
        Long ownerId = Long.parseLong(session().get("user_id"));
        User owner = User.find.byId(ownerId);
        List<Tool> tools = owner.toolList;

        return ok(views.html.tool.index.render(tools));
    }

    // Route: GET /tool/:id
    public Result show(Long id) {
        Long ownerId = Long.parseLong(session().get("user_id"));
        User owner = User.find.byId(ownerId);
        Tool tool = Tool.find.byId(id);
        List<Comment> comments = tool.commentList;

        return ok(views.html.tool.item.render(tool, owner, comments));
    }

    // Route: DELETE /tool/:id
    public Result delete(Long id) {
        return ok();
    }

    // Route: GET /tool/:id/edit
    public Result edit(Long id) {
        return ok();
    }

    // Route: GET /borrow/:id
    public Result lend(Long id) {
        Long ownerId = Long.parseLong(session().get("user_id"));
        User owner = User.find.byId(ownerId);
        Tool tool = Tool.find.byId(id);
        tool.available = true;
        tool.save();
        List<Comment> comments = tool.commentList;

       return ok(views.html.tool.item.render(tool,owner,comments));
    }

    // Route: GET /borrow/:id
    public Result borrow(Long id) {
        Long ownerId = Long.parseLong(session().get("user_id"));
        User owner = User.find.byId(ownerId);
        Tool tool = Tool.find.byId(id);
        tool.available = false;
        tool.save();
        List<Comment> comments = tool.commentList;

       return ok(views.html.tool.item.render(tool,owner,comments));
    }

    // Route: GET /tool/new
    public Result createForm() {
        return ok(views.html.tool.createform.render());
    }

    // Route: POST /tool
    public Result create() {
        DynamicForm toolForm = Form.form().bindFromRequest();
        String name = toolForm.data().get("name");
        String description = toolForm.data().get("description");
        Long ownerId = Long.parseLong(session().get("user_id"));
        User owner = User.find.byId(ownerId);

        Tool tool = Tool.createNewTool (name,description,owner);

        if(tool == null ){
            flash("error", "Invalid tool, please enter all required informations");
            return redirect(routes.Tools.createForm());
        }

        tool.save();

        flash("success", "Added new tool"+tool.name);
        return redirect(routes.UserActivity.show());
    }

}
