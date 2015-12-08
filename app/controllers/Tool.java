package controllers;

import play.mvc.*;

/**
 * This is a Tool controller that implements RESTful routes. You can test
 * these routes using the CURL utility.
 */
public class Tool extends Controller {

    // Route: GET /tool
    public Result index() {
        return ok("This is the tool/index page.\n");
    }

    // Route: GET /tool/:id
    public Result show(Long id) {
        return ok("This is the tool/show page for tool of id: " + String.valueOf(id) + "\n");
    }

    // Route: DELETE /tool/:id
    public Result delete(Long id) {
        return ok();
    }

    // Route: GET /tool/:id/edit
    public Result edit(Long id) {
        return ok();
    }

    // Route: PUT /tool/:id
    public Result update(Long id) {
       return ok();
    }

    // Route: GET /tool/new
    public Result form() {
        return ok("should be displaying a form to create a tool.\n");
    }

    // Route: POST /tool
    public Result createForm() {
        return ok(views.html.tool.createform.render());
    }

    public Result create() {
        return ok();
    }

}
