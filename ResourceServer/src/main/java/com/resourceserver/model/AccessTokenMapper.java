package com.resourceserver.model;

public class AccessTokenMapper {
    private String accessToken;
//     "-----BEGIN PUBLIC KEY-----\n" +
//            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxzgnlwat+JQMsncWmWIR\n" +
//            "L6NH7OLHU4xcRonrQj7uEbqSRakSYIdWcyDykKzwxYN8fPfgG7bwBpc1DTV2Oq7S\n" +
//            "gqI+3LRrrfgSMJbVKBNlhA1jUsvtX0QNXBv9vExyKiZa3iqhY7dpgmFmgSk/B2lF\n" +
//            "aXeaL/GTyzgSZ03wlD3aWkpJyq3gO3AcJ1lTq5BIynRJnO3z+9pAlJ/oGArSB8iQ\n" +
//            "xaJO9+ni9hbIGAZDzDAEjPabDYCsWRaieabhiv0RxVjFbiSQqBNhMUcSGKTd2Alj\n" +
//            "L5GenI0gTJ7ysFkinsrDegeNGR8AXMDaueOHE9dRg7D8ylfcjpOdp/RZMyl3Qd9H\n" +
//            "WwIDAQAB\n" +
//            "-----END PUBLIC KEY-----";
    private String id;
    private String username;
    private String name;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
