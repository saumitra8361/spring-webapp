package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class CommentController {
    @Autowired
    private ImageService imageService;
    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/image/{id}/{title}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable("title") String title, @PathVariable("id") Integer id, @RequestParam("comment") String text, HttpSession session){
        Image image = imageService.getImage(id);
        User user = (User) session.getAttribute("loggeduser");
        Comment comment = new Comment();
        comment.setImage(image);
        comment.setUser(user);
        comment.setText(text);
        commentService.createComment(comment);
        return "redirect:/images/"+image.getId()+"/" + image.getTitle();
    }
}
