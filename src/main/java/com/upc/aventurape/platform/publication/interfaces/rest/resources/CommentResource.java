package com.upc.aventurape.platform.publication.interfaces.rest.resources;

public record CommentResource(
        Long id,
        String content,
        Short rating
) {
}
