package com.resourceserver.controller;

import com.resourceserver.model.AccessTokenMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/note")
public class NoteController {

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public String createNote() {
        AccessTokenMapper accessTokenMapper = (AccessTokenMapper) ((OAuth2AuthenticationDetails) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getDetails()).getDecodedDetails();
        System.out.println("username: " + accessTokenMapper.getUsername());
        return "Note has  been create successfully";
    }

    @PutMapping
    @PreAuthorize("hasRole('USER')")
    public String updateNote() {
        return "Note has  been updated successfully";
    }

    @DeleteMapping
    @PreAuthorize("hasRole('USER')")
    public String deleteNote() {
        return "Note has  been deleted successfully";
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public String viewAllNote() {
        return "Note all showing successfully";
    }

    @GetMapping(path = "{id}")
    @PreAuthorize("hasRole('USER')")
    public String viewNoteNote(@PathVariable int id) {
        return "Note showing by id successfully" + id;
    }
}
