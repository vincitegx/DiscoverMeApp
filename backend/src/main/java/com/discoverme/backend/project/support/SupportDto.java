package com.discoverme.backend.project.support;

import com.discoverme.backend.user.UserDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SupportDto {

    List<UserDto> users;
}
