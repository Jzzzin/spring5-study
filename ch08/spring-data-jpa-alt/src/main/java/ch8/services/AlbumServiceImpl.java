package ch8.services;

import ch8.entities.Album;
import ch8.entities.Singer;
import ch8.repos.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("springJpaAlbumService")
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumRepository albumRepositoy;

    @Transactional(readOnly = true)
    @Override
    public List<Album> findBySinger(Singer singer) {
        return albumRepositoy.findBySinger(singer);
    }

    @Override
    public List<Album> findByTitle(String title) {
        return albumRepositoy.findByTitle(title);
    }

}
