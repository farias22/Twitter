package models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tweet")
public class Tweet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "app_user")
    private String message;
    @Column(name = "published_at")
    @CreationTimestamp()
    private Date publishedAt;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "author_fk")
    private AppUser appUser;

    public Tweet() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tweet tweet = (Tweet) o;
        return Objects.equals(id, tweet.id) &&
                Objects.equals(appUser, tweet.appUser) &&
                Objects.equals(message, tweet.message) &&
                Objects.equals(publishedAt, tweet.publishedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appUser, message, publishedAt);
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", appUser=" + appUser +
                ", message='" + message + '\'' +
                ", publishedAt=" + publishedAt +
                '}';
    }
}
