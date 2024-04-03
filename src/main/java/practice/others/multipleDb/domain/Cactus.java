package practice.others.multipleDb.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cactus {

    @Id  @Column(length = 128)
    private String name;

    @Column
    private int age;

    @Column
    private LocalDateTime created_at;
}
