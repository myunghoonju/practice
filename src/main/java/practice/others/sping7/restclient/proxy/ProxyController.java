package practice.others.sping7.restclient.proxy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.others.sping7.restclient.dto.Post;

import java.util.List;

@RestController
@RequestMapping("/proxy")
class ProxyController {

    private final PostHttpClient postHttpClient;

    ProxyController(PostHttpClient postHttpClient) {
        this.postHttpClient = postHttpClient;
    }

    @GetMapping(path = "/posts", version = "1.0+")
    List<Post> getPosts() {
        return postHttpClient.getPosts();
    }

    @GetMapping(path = "/posts/{id}", version = "1.0+")
    Post getPost(@PathVariable Long id) {
        return postHttpClient.getPost(id);
    }
}
