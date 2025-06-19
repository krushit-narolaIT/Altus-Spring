package com.krushit.common.utils;

import com.krushit.common.Message;
import com.krushit.common.exception.ApplicationException;
import com.krushit.common.mapper.Mapper;
import com.krushit.dto.UserDTO;
import com.krushit.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
public class SessionUtils {
    private final Mapper mapper;

    public SessionUtils(Mapper mapper) {
        this.mapper = mapper;
    }

    public User validateSession(HttpServletRequest request) throws ApplicationException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            throw new ApplicationException(Message.Auth.PLEASE_LOGIN);
        }

        UserDTO userDTO = (UserDTO) session.getAttribute("user");
        if (userDTO == null) {
            throw new ApplicationException(Message.Auth.PLEASE_LOGIN);
        }

        return mapper.convertToEntity(userDTO);
    }
}
