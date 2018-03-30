package com.rodrigo;

import com.rodrigo.course.Course;
import com.rodrigo.course.CourseRepository;
import com.rodrigo.review.Review;
import com.rodrigo.review.ReviewRepository;
import com.rodrigo.user.User;
//import com.rodrigo.user.UserRepository;
import com.rodrigo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class DatabaseLoader implements ApplicationRunner {
    private final CourseRepository courseRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    @Autowired
    public DatabaseLoader(CourseRepository courseRepository,
                          ReviewRepository reviewRepository,
                          UserRepository userRepository
                          ) {
        this.courseRepository = courseRepository;
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
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

        List<User> students = Arrays.asList(
                new User("jacobproffer", "Jacob",  "Proffer", "password", new String[] {"ROLE_USER"}),
                new User("mlnorman", "Mike",  "Norman", "password", new String[] {"ROLE_USER"}),
                new User("k_freemansmith", "Karen",  "Freeman-Smith", "password", new String[] {"ROLE_USER"}),
                new User("seth_lk", "Seth",  "Kroger", "password", new String[] {"ROLE_USER"}),
                new User("mrstreetgrid", "Java",  "Vince", "password", new String[] {"ROLE_USER"}),
                new User("anthonymikhail", "Tony",  "Mikhail", "password", new String[] {"ROLE_USER"}),
                new User("boog690", "AJ",  "Teacher", "password", new String[] {"ROLE_USER"}),
                new User("faelor", "Erik",  "Faelor Shafer", "password", new String[] {"ROLE_USER"}),
                new User("christophernowack", "Christopher",  "Nowack", "password", new String[] {"ROLE_USER"}),
                new User("calebkleveter", "Caleb",  "Kleveter", "password", new String[] {"ROLE_USER"}),
                new User("richdonellan", "Rich",  "Donnellan", "password", new String[] {"ROLE_USER"}),
                new User("albertqerimi", "Albert",  "Qerimi", "password", new String[] {"ROLE_USER"})
        );

        userRepository.save(students);

        User admin = new User("password", "romacas",  "Rodrigo", "Castro", new String[] {"ROLE_USER", "ROLE_ADMIN"});

        userRepository.save(admin);

        List<Course> coursesList = new ArrayList<>();
        List<Review> reviewList = new ArrayList<>();
        IntStream.range(0, 100)
                .forEach(i -> {
                    String template = templates[i % templates.length];
                    String buzzword = buzzwords[i % buzzwords.length];
                    String title = String.format(template, buzzword);
                    Course c = new Course(title, "http://www" + title + ".com");
                    Review review = new Review((i % 5) + 1, String.format("I loved %s", buzzword));
                    review.setUser(students.get(i % students.size()));
                    c.addReview(review);
                    coursesList.add(c);
                    reviewList.add(review);
                });
        courseRepository.save(coursesList);
        reviewRepository.save(reviewList);

    }
}
