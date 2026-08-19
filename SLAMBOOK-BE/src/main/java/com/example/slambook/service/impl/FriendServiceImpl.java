package com.example.slambook.service.impl;

import com.example.slambook.dto.CreateFriendRequest;
import com.example.slambook.dto.FriendResponse;
import com.example.slambook.dto.UpdateFriendRequest;
import com.example.slambook.entity.Friend;
import com.example.slambook.entity.SlamBook;
import com.example.slambook.exception.ResourceNotFoundException;
import com.example.slambook.repository.FriendRepository;
import com.example.slambook.repository.SlamBookRepository;
import com.example.slambook.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final FriendRepository friendRepository;
    private final SlamBookRepository slamBookRepository;

    @Override
    @Transactional
    public FriendResponse addFriend(Long slamBookId, CreateFriendRequest request) {
        SlamBook slamBook = slamBookRepository.findById(slamBookId)
                .orElseThrow(() -> new ResourceNotFoundException("SlamBook not found with id: " + slamBookId));

        Friend friend = Friend.builder()
                .slamBook(slamBook)
                .friendName(request.getFriendName())
                .relationship(request.getRelationship())
                .friendshipRating(request.getFriendshipRating())
                .isBestFriend(request.getIsBestFriend())
                .friendshipSince(request.getFriendshipSince())
                .message(request.getMessage())
                .songName(request.getSongName())
                .songArtist(request.getSongArtist())
                .songUrl(request.getSongUrl())
                .songDedication(request.getSongDedication())
                .memoryPhotoUrl(request.getMemoryPhotoUrl())
                .memoryText(request.getMemoryText())
                .build();

        Friend savedFriend = friendRepository.save(friend);
        return mapToResponse(savedFriend);
    }

    @Override
    public List<FriendResponse> getFriendsBySlamBook(Long slamBookId) {
        if (!slamBookRepository.existsById(slamBookId)) {
            throw new ResourceNotFoundException("SlamBook not found with id: " + slamBookId);
        }
        return friendRepository.findBySlamBookId(slamBookId).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FriendResponse updateFriend(Long friendId, UpdateFriendRequest request) {
        Friend friend = friendRepository.findById(friendId)
                .orElseThrow(() -> new ResourceNotFoundException("Friend not found with id: " + friendId));

        if (request.getFriendName() != null) {
            friend.setFriendName(request.getFriendName());
        }
        if (request.getRelationship() != null) {
            friend.setRelationship(request.getRelationship());
        }
        if (request.getFriendshipRating() != null) {
            friend.setFriendshipRating(request.getFriendshipRating());
        }
        if (request.getIsBestFriend() != null) {
            friend.setIsBestFriend(request.getIsBestFriend());
        }
        if (request.getFriendshipSince() != null) {
            friend.setFriendshipSince(request.getFriendshipSince());
        }
        if (request.getMessage() != null) {
            friend.setMessage(request.getMessage());
        }
        if (request.getSongName() != null) {
            friend.setSongName(request.getSongName());
        }
        if (request.getSongArtist() != null) {
            friend.setSongArtist(request.getSongArtist());
        }
        if (request.getSongUrl() != null) {
            friend.setSongUrl(request.getSongUrl());
        }
        if (request.getSongDedication() != null) {
            friend.setSongDedication(request.getSongDedication());
        }
        if (request.getMemoryPhotoUrl() != null) {
            friend.setMemoryPhotoUrl(request.getMemoryPhotoUrl());
        }
        if (request.getMemoryText() != null) {
            friend.setMemoryText(request.getMemoryText());
        }

        Friend updatedFriend = friendRepository.save(friend);
        return mapToResponse(updatedFriend);
    }

    @Override
    @Transactional
    public void deleteFriend(Long friendId) {
        if (!friendRepository.existsById(friendId)) {
            throw new ResourceNotFoundException("Friend not found with id: " + friendId);
        }
        friendRepository.deleteById(friendId);
    }

    private FriendResponse mapToResponse(Friend friend) {
        return FriendResponse.builder()
                .id(friend.getId())
                .slamBookId(friend.getSlamBook().getId())
                .friendName(friend.getFriendName())
                .relationship(friend.getRelationship())
                .friendshipRating(friend.getFriendshipRating())
                .isBestFriend(friend.getIsBestFriend())
                .friendshipSince(friend.getFriendshipSince())
                .message(friend.getMessage())
                .songName(friend.getSongName())
                .songArtist(friend.getSongArtist())
                .songUrl(friend.getSongUrl())
                .songDedication(friend.getSongDedication())
                .memoryPhotoUrl(friend.getMemoryPhotoUrl())
                .memoryText(friend.getMemoryText())
                .createdAt(friend.getCreatedAt())
                .updatedAt(friend.getUpdatedAt())
                .build();
    }
}
