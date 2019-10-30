package com.k001.selfgroth.registrationactivate.security;

public interface ISecurityUserService {

    String validatePasswordResetToken(long id, String token);

}
