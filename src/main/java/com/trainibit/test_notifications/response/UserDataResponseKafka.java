package com.trainibit.test_notifications.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

//@Data
@Setter
@Getter
public class UserDataResponseKafka {
    private String token;
    private UUID uuid;
    private String email;
    private UUID templateUuid;
}
