package com.hertz.hertztv.service;

import com.hertz.hertztv.exception.ResourceNotFoundException;
import com.hertz.hertztv.model.Artist;
import com.hertz.hertztv.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    @Autowired
    private ArtistRepository artistRepository;

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public Artist createArtist(Artist artist) {
        return artistRepository.save(artist);
    }

    public ResponseEntity<Artist> findArtistById(Long id) {
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Artist with id " + id + " not found!"));
        return ResponseEntity.ok(artist);
    }

    public Artist findArtistForUpdate(Long id) {
        return artistRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Artist with id " + id + " not found!"));
    }

    public void deleteArtist(Long id) {
        try {
            artistRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Artist with id " + id + " not existing!");
        }
    }


}
