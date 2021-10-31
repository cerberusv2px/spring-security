package com.example.springsecurity.properties;

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

            private String base64Secret;

            public Jwt() {
                this.base64Secret = null;
            }


            public String getBase64Secret() {
                return base64Secret;
            }

            public void setBase64Secret(String base64Secret) {
                this.base64Secret = base64Secret;
            }

        }
    }
}
