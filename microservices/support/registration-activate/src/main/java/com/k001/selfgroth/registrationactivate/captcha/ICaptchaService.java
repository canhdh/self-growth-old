package com.k001.selfgroth.registrationactivate.captcha;


import com.k001.selfgroth.registrationactivate.web.error.ReCaptchaInvalidException;

public interface ICaptchaService {
    void processResponse(final String response) throws ReCaptchaInvalidException;

    String getReCaptchaSite();

    String getReCaptchaSecret();
}
