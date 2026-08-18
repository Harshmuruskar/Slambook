package com.example.slambook.service.impl;

import com.example.slambook.dto.CreateSlamBookRequest;
import com.example.slambook.dto.SlamBookResponse;
import com.example.slambook.dto.UpdateSlamBookRequest;
import com.example.slambook.entity.SlamBook;
import com.example.slambook.exception.ResourceNotFoundException;
import com.example.slambook.repository.SlamBookRepository;
import com.example.slambook.service.SlamBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SlamBookServiceImpl implements SlamBookService {

    private final SlamBookRepository slamBookRepository;

    @Override
    @Transactional
    public SlamBookResponse create(CreateSlamBookRequest request) {
        SlamBook slamBook = SlamBook.builder()
                .fullName(request.getFullName())
                .nickname(request.getNickname())
                .profilePhotoUrl(request.getProfilePhotoUrl())
                .dateOfBirth(request.getDateOfBirth())
                .gender(request.getGender())
                .favoriteColor(request.getFavoriteColor())
                .hobbies(request.getHobbies())
                .aboutMe(request.getAboutMe())
                .friendshipRating(request.getFriendshipRating())
                .isBestFriend(request.getIsBestFriend() != null ? request.getIsBestFriend() : false)
                .friendshipStartDate(request.getFriendshipStartDate())
                .songName(request.getSongName())
                .songArtist(request.getSongArtist())
                .songUrl(request.getSongUrl())
                .songDedication(request.getSongDedication())
                .memoryPhotoUrl(request.getMemoryPhotoUrl())
                .memoryText(request.getMemoryText())
                .build();

        SlamBook savedSlamBook = slamBookRepository.save(slamBook);
        return mapToResponse(savedSlamBook);
    }

    @Override
    @Transactional(readOnly = true)
    public SlamBookResponse getById(Long id) {
        SlamBook slamBook = slamBookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SlamBook not found with id: " + id));
        return mapToResponse(slamBook);
    }

    @Override
    @Transactional
    public SlamBookResponse update(Long id, UpdateSlamBookRequest request) {
        SlamBook slamBook = slamBookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("SlamBook not found with id: " + id));

        if (request.getFullName() != null) slamBook.setFullName(request.getFullName());
        if (request.getNickname() != null) slamBook.setNickname(request.getNickname());
        if (request.getProfilePhotoUrl() != null) slamBook.setProfilePhotoUrl(request.getProfilePhotoUrl());
        if (request.getDateOfBirth() != null) slamBook.setDateOfBirth(request.getDateOfBirth());
        if (request.getGender() != null) slamBook.setGender(request.getGender());
        if (request.getFavoriteColor() != null) slamBook.setFavoriteColor(request.getFavoriteColor());
        if (request.getHobbies() != null) slamBook.setHobbies(request.getHobbies());
        if (request.getAboutMe() != null) slamBook.setAboutMe(request.getAboutMe());
        if (request.getFriendshipRating() != null) slamBook.setFriendshipRating(request.getFriendshipRating());
        if (request.getIsBestFriend() != null) slamBook.setIsBestFriend(request.getIsBestFriend());
        if (request.getFriendshipStartDate() != null) slamBook.setFriendshipStartDate(request.getFriendshipStartDate());
        if (request.getSongName() != null) slamBook.setSongName(request.getSongName());
        if (request.getSongArtist() != null) slamBook.setSongArtist(request.getSongArtist());
        if (request.getSongUrl() != null) slamBook.setSongUrl(request.getSongUrl());
        if (request.getSongDedication() != null) slamBook.setSongDedication(request.getSongDedication());
        if (request.getMemoryPhotoUrl() != null) slamBook.setMemoryPhotoUrl(request.getMemoryPhotoUrl());
        if (request.getMemoryText() != null) slamBook.setMemoryText(request.getMemoryText());

        SlamBook updatedSlamBook = slamBookRepository.save(slamBook);
        return mapToResponse(updatedSlamBook);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!slamBookRepository.existsById(id)) {
            throw new ResourceNotFoundException("SlamBook not found with id: " + id);
        }
        slamBookRepository.deleteById(id);
    }

    private SlamBookResponse mapToResponse(SlamBook slamBook) {
        return SlamBookResponse.builder()
                .id(slamBook.getId())
                .fullName(slamBook.getFullName())
                .nickname(slamBook.getNickname())
                .profilePhotoUrl(slamBook.getProfilePhotoUrl())
                .dateOfBirth(slamBook.getDateOfBirth())
                .gender(slamBook.getGender())
                .favoriteColor(slamBook.getFavoriteColor())
                .hobbies(slamBook.getHobbies())
                .aboutMe(slamBook.getAboutMe())
                .friendshipRating(slamBook.getFriendshipRating())
                .isBestFriend(slamBook.getIsBestFriend())
                .friendshipStartDate(slamBook.getFriendshipStartDate())
                .songName(slamBook.getSongName())
                .songArtist(slamBook.getSongArtist())
                .songUrl(slamBook.getSongUrl())
                .songDedication(slamBook.getSongDedication())
                .memoryPhotoUrl(slamBook.getMemoryPhotoUrl())
                .memoryText(slamBook.getMemoryText())
                .createdAt(slamBook.getCreatedAt())
                .updatedAt(slamBook.getUpdatedAt())
                .build();
    }
}
