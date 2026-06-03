package practice.others.road_to_ga.restclient.registrar;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.NonNull;
import org.springframework.web.service.registry.AbstractHttpServiceRegistrar;

class PostHttpServiceRegistrar extends AbstractHttpServiceRegistrar implements EnvironmentAware {

    private Environment environment;

    @Override
    public void setEnvironment(@NonNull Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void registerHttpServices(GroupRegistry registry, AnnotationMetadata metadata) {

        // 1. 특정 클래스 직접 등록
        registry.forGroup("posts")
                .register(PostHttpClient.class);

        // 2. 패키지 스캔 — @HttpExchange 붙은 인터페이스 자동 탐색
        // registry.forGroup("posts")
        //         .detectInBasePackages("practice.others.road_to_ga.registrar");

        registry.forGroup("users")
                .register(UserHttpClient.class);

        // 3. importing class의 어노테이션 기반 조건부 등록 예시
        // RegistrarConfig에 @Profile("premium") 같은 커스텀 어노테이션이 있을 때만 등록
        // if (metadata.hasAnnotation("org.springframework.context.annotation.Profile")) {
        //     registry.forGroup("users").register(UserHttpClient.class);
        // }

        // 4. 환경 프로파일 기반 조건부 등록 예시
        // if (environment.matchesProfiles("prod")) {
        //     registry.forGroup("users").register(UserHttpClient.class);
        // }
    }
}
