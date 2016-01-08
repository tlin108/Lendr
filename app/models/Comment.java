package models;

import com.avaje.ebean.Model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Entity
public class Comment extends Model {
  @Id
  public Long id;
  
  public String body;
  
  public String poster;
  
  @ManyToOne
  public Tool tool;

  public String datetime_posted;
  
  // A finder object for easier querying
  public static Finder<Long, Comment> find = new Finder<Long, Comment>(Comment.class);

  public static Comment newComment(String body, Tool tool, User poster){
  	if(body == null || tool == null || poster == null || body.length() == 0){
  		return null;
  	}

  	Comment comment = new Comment();
  	comment.body = body;
  	comment.poster = poster.userName;
  	comment.tool = tool;

  	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	//get current date time with Date()
	Date date = new Date();
	comment.datetime_posted = dateFormat.format(date);

  	return comment;
  }
}