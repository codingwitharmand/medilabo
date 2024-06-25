package com.cwa.medialabogateway;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "app")
public class AppUsersConfig {

    private List<AppUser> users;

    @Getter
    @Setter
    public static class AppUser {
        private String username;
        private String password;
        private Role role;
    }
}
