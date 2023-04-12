package com.example.giraapp.service.song;

import com.example.giraapp.model.SongAddModel;
import com.example.giraapp.model.SongViewModel;
import com.example.giraapp.model.entity.Song;
import com.example.giraapp.model.entity.StyleEntity;
import com.example.giraapp.repository.SongRepository;
import com.example.giraapp.repository.StyleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;
    private final StyleRepository styleRepository;
    private final ModelMapper mapper;

    @Autowired
    public SongService(SongRepository songRepository, StyleRepository styleRepository, ModelMapper mapper) {
        this.songRepository = songRepository;
        this.styleRepository = styleRepository;
        this.mapper = mapper;
    }


    public void addSong(SongAddModel songAddModel) {
        Song song = this.mapper.map(songAddModel, Song.class);
        StyleEntity style = this.styleRepository.findByName(songAddModel.getStyle()).orElse(null);
        song.setStyle(style);

        this.songRepository.saveAndFlush(song);



    }

    public List<SongViewModel> getAllSongs() {
        return this.songRepository.findAll().stream().map(song -> {
            SongViewModel songModel = mapper.map(song, SongViewModel.class);
            String styleName = song.getStyle().getName().name();
            songModel.setStyle(styleName);

            return songModel;
        }).toList();
    }
}
