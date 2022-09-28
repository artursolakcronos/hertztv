package com.hertz.hertztv.controller;

import com.hertz.hertztv.model.Artist;
import com.hertz.hertztv.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ArtistController {

    @Autowired
    private ArtistService artistService;

    @GetMapping("/artists")
    public List<Artist> getAllArtists() {
        return artistService.getAllArtists();
    }

    /**
     * CREATE A NEW ARTIST
     */
    @PostMapping("/artists")
    public Artist createArtist(@RequestBody Artist artist) {
        return artistService.createArtist(artist);
    }

    /**
     * GET ARTIST DETAILS
     */
    @GetMapping("/artists/{id}")
    public ResponseEntity<Artist> getArtistById(@PathVariable Long id) {
        return artistService.findArtistById(id);
    }

    /**
     * UPDATE ARTIST DETAILS
     */
    @PutMapping("/artists/{id}")
    public ResponseEntity<Artist> updateArtist(@PathVariable Long id, @RequestBody Artist updatedArtist) {
        Artist artist = artistService.findArtistForUpdate(id);

        artist.setFirstName(updatedArtist.getFirstName());
        artist.setLastName(updatedArtist.getLastName());
        artist.setNickname(updatedArtist.getNickname());
        artist.setEmail(updatedArtist.getEmail());
        artist.setPhoneNumber(updatedArtist.getPhoneNumber());

        //Method calling repository.save, will update existing
        Artist newArtistDetails = artistService.createArtist(artist);

        return ResponseEntity.ok(newArtistDetails);
    }

    /**
     * DELETE ARTIST
     */
    @DeleteMapping("/artists/{id}")
    public ResponseEntity<Boolean> deleteArtist(@PathVariable Long id) {
        artistService.deleteArtist(id);

        return ResponseEntity.ok(true);
    }


}
