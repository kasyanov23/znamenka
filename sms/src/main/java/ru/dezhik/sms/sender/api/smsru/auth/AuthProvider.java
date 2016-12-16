package ru.dezhik.sms.sender.api.smsru.auth;

import org.apache.http.NameValuePair;

import java.io.IOException;
import java.util.List;

/**
 * @author ilya.dezhin
 */
public interface AuthProvider {
    List<NameValuePair> provideAuthParams() throws IOException;
}
