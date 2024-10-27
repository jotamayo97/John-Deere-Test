package com.example.johndeere.application.session;

import com.example.johndeere.domain.model.Session;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SessionService {


    @Transactional
    public void newSession(Session session) {
        throw new UnsupportedOperationException("Not Implemented yet");
    }

}
