package com.example.motarjimapp.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.identitystore.IdentityStore;
import jakarta.security.enterprise.credential.Credential;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;

import java.util.Set;

@ApplicationScoped
public class InMemoryIdentityStore implements IdentityStore {

    @Override
    public CredentialValidationResult validate(Credential credential) {

        if (!(credential instanceof UsernamePasswordCredential userCredential)) {
            return CredentialValidationResult.INVALID_RESULT;
        }

        String username = userCredential.getCaller();
        String password = userCredential.getPasswordAsString();

        if ("user".equals(username) && "1234".equals(password)) {
            return new CredentialValidationResult(username, Set.of("USER"));
        }

        return CredentialValidationResult.INVALID_RESULT;
    }
}