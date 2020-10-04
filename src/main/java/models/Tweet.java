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


    private String author;

    public Tweet() {
    }


    public Tweet(String author, String message) {
        this.author=author;
        this.message=message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
                Objects.equals(author, tweet.author) &&
                Objects.equals(message, tweet.message) &&
                Objects.equals(publishedAt, tweet.publishedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, message, publishedAt);
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "id=" + id +
                ", appUser=" + author +
                ", message='" + message + '\'' +
                ", publishedAt=" + publishedAt +
                '}';
    }




}
