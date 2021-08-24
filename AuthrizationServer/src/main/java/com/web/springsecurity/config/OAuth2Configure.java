package com.web.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class OAuth2Configure extends AuthorizationServerConfigurerAdapter {
    private String clientId = "test";
    private String clientSecret = "test";
    private String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" +
            "MIIEpAIBAAKCAQEAxzgnlwat+JQMsncWmWIRL6NH7OLHU4xcRonrQj7uEbqSRakS\n" +
            "YIdWcyDykKzwxYN8fPfgG7bwBpc1DTV2Oq7SgqI+3LRrrfgSMJbVKBNlhA1jUsvt\n" +
            "X0QNXBv9vExyKiZa3iqhY7dpgmFmgSk/B2lFaXeaL/GTyzgSZ03wlD3aWkpJyq3g\n" +
            "O3AcJ1lTq5BIynRJnO3z+9pAlJ/oGArSB8iQxaJO9+ni9hbIGAZDzDAEjPabDYCs\n" +
            "WRaieabhiv0RxVjFbiSQqBNhMUcSGKTd2AljL5GenI0gTJ7ysFkinsrDegeNGR8A\n" +
            "XMDaueOHE9dRg7D8ylfcjpOdp/RZMyl3Qd9HWwIDAQABAoIBAQCRPRRH27vLpQvY\n" +
            "tE+KhV9oLo8KWY7eD09ascQNKNnhnPZ0yL04GJLjHlsZNKvYI3MHnKMLMYSuGWC4\n" +
            "/4fxYnDggi54MyTp0RhZyi0eqX1yF1yZCUlGEdP3mgSTWdxFdG7813GPxDCsuBvG\n" +
            "AG8OFSZgrKyBVYrdNkTcgYrqWGmvNn4BVSrjp9zUMlsGZDFCKNrmRVfDcv6VzOgI\n" +
            "NxcOToxbxqDFx82klSojm9sTUX85hpydLMXpdY+i+/Pb/hqR1+d0a9RCns4Ln7lU\n" +
            "dMEZIsL6MH4jPTVZ3BYQ/Tp7hI/LFEo4VMyH2VDYEezOKndax5dDwg+lgXnemIyb\n" +
            "0lcLqykRAoGBAO1niKTnvQbHRGbx3N/TvsU9ycjw7YktzuCR8nJP7vfqoDHuvL/3\n" +
            "YTkkQ2LXEMw2SBN8BNSiJeop4z1vHPO3RqQJrHNlNUTn7hvVqCPstCYW6OJYBJI+\n" +
            "VM7MadKrd70h1UNrCP39SwnGK9wI/xrK9uIuNZ53H48Du3+IXUHHx8iTAoGBANbS\n" +
            "7bNBjaZfjkAIcNwyRfquV4u7+y1vJUTQN5fxYucuselyYgoSpFr4VkYSotjiuufi\n" +
            "iD2XJhRtxW/TZz+iQ+j4kBfTZj44yCQIvAv9Bp1egkYgDIZS6D6Zf/cSw8xg0ocq\n" +
            "Rg4itkZOaIJ599V6VrO5QhPfdMoK5+wBcTWUJCsZAoGASair/u5f+fBtyrsUWuYp\n" +
            "9KzdJNmL+doarBbccJdYf9duaM+4BJ/OrJoRxmMQNSxKZLFAFnFQ7jyc4vzMsNmW\n" +
            "s2LE/PDT/FMxz7UU/EB/DKpjC4dfs7hfO+K0msbU2fvbXZWiLpweYiP4i8bG0BtV\n" +
            "Sown2sj/I7J2xHUc6kIAPL0CgYAL5hcAbMsZwgUxCsali6tQnUOAG+NnutZHn3jU\n" +
            "06v0viNgmNNRV4L+/u5YZ0PZ24UavvNo5exYCVR8Qyte3PACAXKJv03UeHStekZg\n" +
            "K52oLcntdGz9K65teTcbSlDKCzrd/btvoQCTmFg1kefmTSZsQgCk9SWU41HBQrsw\n" +
            "iooOUQKBgQCm+/nybViMkOJOLuAsAXMUSkuBUrUIZUEH+Qe6J6jdDRLdm+iX88uc\n" +
            "r31WSUnLFQspiAZWADu+qTXVg1sqfxbpH0wZ8oM8wDnX86iQsO0BP2YwvPh5vjn9\n" +
            "5eFpjltB5Wre6XuqenbdDUvlPWCgQ5onGH3dnTt1yvrzRTYv9t2h8w==\n" +
            "-----END RSA PRIVATE KEY-----";
    private String publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxzgnlwat+JQMsncWmWIR\n" +
            "L6NH7OLHU4xcRonrQj7uEbqSRakSYIdWcyDykKzwxYN8fPfgG7bwBpc1DTV2Oq7S\n" +
            "gqI+3LRrrfgSMJbVKBNlhA1jUsvtX0QNXBv9vExyKiZa3iqhY7dpgmFmgSk/B2lF\n" +
            "aXeaL/GTyzgSZ03wlD3aWkpJyq3gO3AcJ1lTq5BIynRJnO3z+9pAlJ/oGArSB8iQ\n" +
            "xaJO9+ni9hbIGAZDzDAEjPabDYCsWRaieabhiv0RxVjFbiSQqBNhMUcSGKTd2Alj\n" +
            "L5GenI0gTJ7ysFkinsrDegeNGR8AXMDaueOHE9dRg7D8ylfcjpOdp/RZMyl3Qd9H\n" +
            "WwIDAQAB\n" +
            "-----END PUBLIC KEY-----";

    @Autowired
    @Qualifier("authenticationManagerBean")
    private  AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public OAuth2Configure(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }

    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(privateKey);
        converter.setVerifierKey(publicKey);
        return converter;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .accessTokenConverter((tokenEnhancer()));
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient(clientId)
                .secret(passwordEncoder.encode(clientSecret))
                .scopes("read", "write")
                .authorizedGrantTypes("code", "refresh_token")
                .accessTokenValiditySeconds(3600)
                .refreshTokenValiditySeconds(18000);
    }
}
