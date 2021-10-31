package com.example.springsecurity.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Configuration
@ConfigurationProperties(prefix = "myapp")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppProperties {

    private Security security = new AppProperties.Security();

    public static class Security {

        private Jwt jwt = new Jwt();

        public Jwt getJwt() {
            return jwt;
        }

        public static class Jwt {

            private String secret;
            private String base64Secret;
            private long tokenValidityInSeconds;
            private long tokenValidityInSecondsForRememberMe;

            public Jwt() {
                this.secret = null;
                this.base64Secret = null;
                this.tokenValidityInSeconds = 1800L;
                this.tokenValidityInSecondsForRememberMe = 2592000L;
            }

            public String getSecret() {
                return secret;
            }

            public void setSecret(String secret) {
                this.secret = secret;
            }

            public String getBase64Secret() {
                return base64Secret;
            }

            public void setBase64Secret(String base64Secret) {
                this.base64Secret = base64Secret;
            }

            public long getTokenValidityInSeconds() {
                return tokenValidityInSeconds;
            }

            public void setTokenValidityInSeconds(long tokenValidityInSeconds) {
                this.tokenValidityInSeconds = tokenValidityInSeconds;
            }

            public long getTokenValidityInSecondsForRememberMe() {
                return tokenValidityInSecondsForRememberMe;
            }

            public void setTokenValidityInSecondsForRememberMe(
                long tokenValidityInSecondsForRememberMe) {
                this.tokenValidityInSecondsForRememberMe = tokenValidityInSecondsForRememberMe;
            }
        }
    }

}

