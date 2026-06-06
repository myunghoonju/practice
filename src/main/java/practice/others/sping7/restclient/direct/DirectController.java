package practice.others.sping7.restclient.direct;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import practice.others.sping7.restclient.dto.Post;

import java.util.List;

@RestController
@RequestMapping("/direct")
class DirectController {

    private final DirectPostService postService;

    DirectController(DirectPostService postService) {
        this.postService = postService;
    }

    @GetMapping(path = "/posts", version = "1.0+")
    List<Post> getPosts() {
        try {
            return postService.getPosts();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Failed after retries", e);
        }
    }

    @GetMapping(path = "/posts/{id}", version = "1.0+")
    Post getPost(@PathVariable Long id) {
        try {
            return postService.getPost(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Failed after retries", e);
        }
    }
}
