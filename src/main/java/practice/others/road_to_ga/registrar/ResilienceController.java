package practice.others.road_to_ga.registrar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.others.road_to_ga.dto.Post;

import java.util.List;

@RestController
@RequestMapping("/registrar")
class ResilienceController {

    private final PostHttpClient postHttpClient;
    private final UserHttpClient userHttpClient;

    ResilienceController(PostHttpClient postHttpClient, UserHttpClient userHttpClient) {
        this.postHttpClient = postHttpClient;
        this.userHttpClient = userHttpClient;
    }

    @GetMapping("/posts")
    List<Post> getPosts() {
        return postHttpClient.getPosts();
    }

    @GetMapping("/posts/{id}")
    Post getPost(@PathVariable Long id) {
        return postHttpClient.getPost(id);
    }

    @GetMapping("/users")
    List<UserHttpClient.User> getUsers() {
        return userHttpClient.getUsers();
    }

    @GetMapping("/users/{id}")
    UserHttpClient.User getUser(@PathVariable Long id) {
        return userHttpClient.getUser(id);
    }
}
