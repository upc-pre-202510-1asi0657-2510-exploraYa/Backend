package com.upc.aventurape.platform.profiles.interfaces.rest.resources;

public record ProfileResource(Long id,
                              String name,String lastName,
                              String birthDate,String gender,
                              String location,String category) {
}
