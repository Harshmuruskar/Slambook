package com.example.slambook.service;

import com.example.slambook.dto.CreateFriendRequest;
import com.example.slambook.dto.FriendResponse;
import com.example.slambook.dto.UpdateFriendRequest;

import java.util.List;

public interface FriendService {
    FriendResponse addFriend(Long slamBookId, CreateFriendRequest request);
    List<FriendResponse> getFriendsBySlamBook(Long slamBookId);
    FriendResponse updateFriend(Long friendId, UpdateFriendRequest request);
    void deleteFriend(Long friendId);
}
