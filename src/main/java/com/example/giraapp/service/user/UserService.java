package com.example.giraapp.service.user;


import com.example.giraapp.helper.LoggedUser;
import com.example.giraapp.model.entity.Song;
import com.example.giraapp.model.entity.User;
import com.example.giraapp.repository.SongRepository;
import com.example.giraapp.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final SongRepository songRepository;
    private final LoggedUser loggedUser;

    @Autowired
    public UserService(UserRepository userRepository, SongRepository songRepository, LoggedUser loggedUser) {
        this.userRepository = userRepository;
        this.songRepository = songRepository;
        this.loggedUser = loggedUser;
    }


    public Optional<User> findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }


    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public void addSongToPlaylist(Long id) {
        Song song = this.songRepository.findById(id).orElse(null);
        User user = this.userRepository.findById(this.loggedUser.getId()).get();
        if(song != null){
            user.getPlaylist().add(song);
            this.userRepository.saveAndFlush(user);
        }
    }


    public Set<Song> getPlaylist(Long id) {
        return this.userRepository.findById(id).get().getPlaylist();
    }
    @Transactional
    public void removeAllSongs() {
        User user = this.userRepository.findById(this.loggedUser.getId()).get();
        user.getPlaylist().clear();
    }
}
