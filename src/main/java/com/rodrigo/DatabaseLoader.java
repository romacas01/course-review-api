package com.rodrigo;

import com.rodrigo.course.Course;
import com.rodrigo.course.CourseRepository;
import com.rodrigo.review.Review;
import com.rodrigo.review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {
    private final CourseRepository courseRepository;
    private final ReviewRepository reviewRepository;

    @Autowired
    public DatabaseLoader(CourseRepository courseRepository,
                          ReviewRepository reviewRepository) {
        this.courseRepository = courseRepository;
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        String[] templates = {
                "Up and Running with %s",
                "%s Basics",
                "%s for Beginners",
                "%s for Neckbeards",
                "Under the hood: %s"
        };
        String[] buzzwords = {
                "Spring REST Data",
                "Java 9",
                "Scala",
                "Groovy",
                "Hibernate",
                "Spring HATEOAS"
        };

        List<Course> coursesList = new ArrayList<>();
        List<Review> reviewList = new ArrayList<>();
        IntStream.range(0, 100)
                .forEach(i -> {
                    String template = templates[i % templates.length];
                    String buzzword = buzzwords[i % buzzwords.length];
                    String title = String.format(template, buzzword);
                    Course c = new Course(title, "http://www" + title + ".com");
                    Review review = new Review(i % 5, String.format("I loved ", buzzword));
                    c.addReview(review);
                    coursesList.add(c);
                    reviewList.add(review);
                });
        courseRepository.save(coursesList);
        reviewRepository.save(reviewList);

    }
}
