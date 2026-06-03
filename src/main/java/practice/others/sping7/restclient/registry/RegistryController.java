package practice.others.sping7.restclient.registry;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import practice.others.sping7.restclient.dto.Post;

import java.util.List;

@RestController
@RequestMapping("/registry")
class RegistryController {

    private final PostHttpClient postHttpClient;

    RegistryController(PostHttpClient postHttpClient) {
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
