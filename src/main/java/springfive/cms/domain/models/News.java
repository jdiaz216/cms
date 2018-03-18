package springfive.cms.domain.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String id;

    String title;

    String content;

    @ManyToOne
    User author;

    @OneToMany
    Set<User> mandatoryReviewers;

    @ElementCollection
    Set<Review> reviewers;

    @OneToMany
    Set<Category> categories;

    
    Set<Tag> tags;

    public Review review(String userId, String status) {

        final Review review = new Review(userId, status);

        this.reviewers.add(review);

        return review;
    }


    public Boolean revised() {

        return this.mandatoryReviewers.stream().allMatch(reviewer ->
                this.reviewers.stream().anyMatch(review ->
                        reviewer.getId().equals(review.getUserId())
                                && "approved".equals(review.getStatus())));
    }
}
