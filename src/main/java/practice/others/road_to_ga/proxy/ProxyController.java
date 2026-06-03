package practice.others.road_to_ga.proxy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.others.road_to_ga.dto.Post;

import java.util.List;

@RestController
@RequestMapping("/proxy")
class ProxyController {

    private final PostHttpClient postHttpClient;

    ProxyController(PostHttpClient postHttpClient) {
        this.postHttpClient = postHttpClient;
    }

    @GetMapping("/posts")
    List<Post> getPosts() {
        return postHttpClient.getPosts();
    }

    @GetMapping("/posts/{id}")
    Post getPost(@PathVariable Long id) {
        return postHttpClient.getPost(id);
    }
}
