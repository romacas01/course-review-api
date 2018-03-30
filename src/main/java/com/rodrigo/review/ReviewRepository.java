package com.rodrigo.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;

public interface ReviewRepository extends PagingAndSortingRepository<Review, Long> {

    @RestResource(rel = "rating-is-equal-to", path = "ratingEqualsTo")
    Page<Review> findByRating(@Param("rating") int rating, Pageable page);

    @RestResource(rel = "find-by-description-contains", path = "descriptionContains")
    Page<Review> findByDescriptionContains(@Param("description") String description, Pageable page);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or @reviewRepository.findOne(#id)?.user?.username == authentication.name")
    void delete(@Param("id") Long id);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or #review.user?.username == authentication.name")
    void delete(@Param("review") Review entity);
}
