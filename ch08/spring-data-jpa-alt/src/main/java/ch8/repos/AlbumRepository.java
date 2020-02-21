package ch8.repos;

import ch8.entities.Album;
import ch8.entities.Singer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    List<Album> findBySinger(Singer singer);

    @Query("SELECT a FROM Album a WHERE a.title LIKE %:title%")
    List<Album> findByTitle(@Param("title") String title);
}

