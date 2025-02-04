package com.trainibit.test_notifications.request;

import lombok.Data;
import lombok.ToString;

import java.util.UUID;

@Data
@ToString
public class UserDataRequestKafka {
    private String token;
    private UUID uuid;
    private String email;
}
