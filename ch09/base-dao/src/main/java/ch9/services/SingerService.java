package ch9.services;

import ch9.entities.Singer;

import java.util.List;

public interface SingerService {
    List<Singer> findAll();

    Singer findById(Long id);

    Singer save(Singer singer);

    long countAll();
}
