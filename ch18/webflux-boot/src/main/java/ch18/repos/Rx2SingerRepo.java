package ch18.repos;

import ch18.entities.Singer;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface Rx2SingerRepo {
    Single<Singer> findById(Long id);

    Flowable<Singer> findAll();

    Single<Void> save(Single<Singer> singerSingle);
}
