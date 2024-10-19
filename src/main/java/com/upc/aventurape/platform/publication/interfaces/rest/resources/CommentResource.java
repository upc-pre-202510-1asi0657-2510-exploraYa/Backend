package com.upc.aventurape.platform.publication.interfaces.rest.resources;

public record CommentResource(
        Long id,
        Long AdventureId,
        String content,
        Short rating
) {
}
