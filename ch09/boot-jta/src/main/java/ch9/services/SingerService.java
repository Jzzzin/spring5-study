package ch9.services;

import ch9.entities.Singer;

public interface SingerService {
    Singer save(Singer singer);

    long count();
}
