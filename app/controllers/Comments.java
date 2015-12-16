package controllers;

import models.ToolCategory;
import models.User;
import models.Tool;
import models.ToolCategory;
import models.Comment;
import play.*;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.*;
import java.util.List;

import static play.data.Form.form;

/**
 * This is the Comment Controller. This is where we implement all of the Action
 * methods related to operations on the comments.
 */
public class Comments extends Controller {

    // Route: GET /comment/new/:id
    //  Displays the form for creating a new comment.
    public Result createForm(Long id) {
        Tool tool = Tool.find.byId(id);
        Long ownerId = Long.parseLong(session().get("user_id"));
        User owner = User.find.byId(ownerId);

        return ok(views.html.comment.createform.render(tool, owner));
    }

    // Route: POST /comment
    public Result create(Long id) {
        DynamicForm commentForm = Form.form().bindFromRequest();
        String body = commentForm.data().get("body");
        Long posterId = Long.parseLong(session().get("user_id"));
        User poster = User.find.byId(posterId);
        Tool tool = Tool.find.byId(id);

        Comment comment = Comment.newComment (body,tool,poster);

        if(comment == null ){
            flash("error", "Invalid comment, please enter some comment in the box below");
            return redirect(routes.Comments.createForm(tool.id));
        }

        comment.save();

        flash("success", "Added new comment");
        return redirect(routes.Tools.show(tool.id));
    }

    // Route: GET /users/:id
    //  Shows the User profile 'id'
    //  There's an issue here... why is 'new' in user/new NOT taken as an 'id' but 'login' in user/login IS taken as 'id'
    public Result show() {
        List<Tool> tools = Tool.find.all();
        List<ToolCategory> categories = ToolCategory.find.all();

        return ok(views.html.user.index.render(tools, categories));
    }



}