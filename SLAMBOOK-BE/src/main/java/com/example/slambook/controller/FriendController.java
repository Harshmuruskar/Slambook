package com.example.slambook.controller;

import com.example.slambook.dto.ApiResponse;
import com.example.slambook.dto.CreateFriendRequest;
import com.example.slambook.dto.FriendResponse;
import com.example.slambook.dto.UpdateFriendRequest;
import com.example.slambook.service.FriendService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;

    @PostMapping("/slam/{slamId}/friends")
    public ResponseEntity<ApiResponse<FriendResponse>> addFriend(
            @PathVariable Long slamId,
            @Valid @RequestBody CreateFriendRequest request) {
        FriendResponse response = friendService.addFriend(slamId, request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Friend added successfully", response));
    }

    @GetMapping("/slam/{slamId}/friends")
    public ResponseEntity<ApiResponse<List<FriendResponse>>> getFriendsBySlamBook(@PathVariable Long slamId) {
        List<FriendResponse> response = friendService.getFriendsBySlamBook(slamId);
        return ResponseEntity.ok(ApiResponse.success("Friends retrieved successfully", response));
    }

    @PutMapping("/friends/{friendId}")
    public ResponseEntity<ApiResponse<FriendResponse>> updateFriend(
            @PathVariable Long friendId,
            @Valid @RequestBody UpdateFriendRequest request) {
        FriendResponse response = friendService.updateFriend(friendId, request);
        return ResponseEntity.ok(ApiResponse.success("Friend updated successfully", response));
    }

    @DeleteMapping("/friends/{friendId}")
    public ResponseEntity<ApiResponse<Void>> deleteFriend(@PathVariable Long friendId) {
        friendService.deleteFriend(friendId);
        return ResponseEntity.ok(ApiResponse.success("Friend deleted successfully", null));
    }
}
