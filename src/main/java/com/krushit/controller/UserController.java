package com.krushit.controller;

import com.krushit.common.Message;
import com.krushit.common.annotations.CustomerOrDriver;
import com.krushit.common.enums.RoleType;
import com.krushit.common.exception.ApplicationException;
import com.krushit.common.exception.ValidationException;
import com.krushit.common.mapper.Mapper;
import com.krushit.common.utils.AuthUtils;
import com.krushit.common.utils.JwtUtils;
import com.krushit.controller.validator.UserLoginValidator;
import com.krushit.controller.validator.UserSignUpValidator;
import com.krushit.dto.ApiResponseDTO;
import com.krushit.dto.ChangePasswordDTO;
import com.krushit.dto.UpdateUserDTO;
import com.krushit.dto.UserDTO;
import com.krushit.entity.Role;
import com.krushit.entity.User;
import com.krushit.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserSignUpValidator userSignUpValidator;
    @Autowired
    private UserLoginValidator userLoginValidator;
    @Autowired
    private Mapper mapper;
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private JwtUtils jwtUtils;

    private static Role mapRole(RoleType roleType) {
        switch (roleType) {
            case ROLE_SUPER_ADMIN:
                return new Role(1, RoleType.ROLE_SUPER_ADMIN);
            case ROLE_CUSTOMER:
                return new Role(2, RoleType.ROLE_CUSTOMER);
            case ROLE_DRIVER:
                return new Role(3, RoleType.ROLE_DRIVER);
            default:
                throw new IllegalArgumentException("Unknown role: " + roleType);
        }
    }

    @PostMapping
    public ResponseEntity<?> loginUser(@RequestBody UserDTO loginUser) throws ApplicationException {
        User user = mapper.convertToEntity(loginUser);
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(user, "user");
        userLoginValidator.validate(user, errors);
        if (errors.hasErrors()) {
            throw new ValidationException(errors.getAllErrors().get(0).getDefaultMessage());
        }
        UserDTO authenticatedUser = userService.userLogin(user.getEmailId(), user.getPassword());
        User authenticatedEntity = mapper.convertToEntity(authenticatedUser);
        String jwtToken = jwtUtils.generateToken(authenticatedEntity);
        return ResponseEntity.ok()
                .body(new ApiResponseDTO(
                        Message.User.LOGIN_SUCCESSFUL,
                        Map.of(
                                "token", jwtToken
                        )
                ));
    }


    @PostMapping("/customer")
    public ResponseEntity<?> registerCustomer(@RequestBody UserDTO userSignUpDTO, Locale locale) throws ApplicationException {
        return registerUserWithRole(userSignUpDTO, RoleType.ROLE_CUSTOMER, locale);
    }

    @PostMapping("/driver")
    public ResponseEntity<?> registerDriver(@RequestBody UserDTO userSignUpDTO, Locale locale) throws ApplicationException {
        return registerUserWithRole(userSignUpDTO, RoleType.ROLE_DRIVER, locale);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logoutUser(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok(new ApiResponseDTO(Message.User.USER_LOGOUT_SUCCESSFULLY));
    }

    @PatchMapping("/update")
    @CustomerOrDriver
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO updatedUser, HttpServletRequest request) throws ApplicationException {
        User user = (User) request.getAttribute("user");
        AuthUtils.validateUser(user, RoleType.ROLE_CUSTOMER);
        userService.updateUser(updatedUser, user.getUserId());
        return ResponseEntity.ok(new ApiResponseDTO(Message.User.DETAILS_UPDATED_SUCCESSFULLY));
    }

    @PatchMapping("/password")
    @CustomerOrDriver
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO) throws ApplicationException {
        userService.updatePassword(changePasswordDTO.getEmailId(),
                changePasswordDTO.getOldPassword(), changePasswordDTO.getNewPassword());
        return ResponseEntity.ok(new ApiResponseDTO(Message.User.PASSWORD_CHANGED_SUCCESSFULLY));
    }

    private ResponseEntity<?> registerUserWithRole(UserDTO userSignUpDTO, RoleType roleType, Locale locale) throws ApplicationException {
        User user = convertToEntity(userSignUpDTO, roleType);
        BeanPropertyBindingResult errors = new BeanPropertyBindingResult(user, "user");
        userSignUpValidator.validate(user, errors);
        if (errors.hasErrors()) {
            String errorMessage = messageSource.getMessage(errors.getAllErrors().get(0), locale);
            throw new ApplicationException(errorMessage);
        }
        userService.registerUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseDTO(Message.User.USER_REGISTERED_SUCCESSFULLY));
    }

    private User convertToEntity(UserDTO dto, RoleType roleType) {
        Role role = mapRole(roleType);
        return new User.UserBuilder()
                .setFirstName(dto.getFirstName())
                .setLastName(dto.getLastName())
                .setPhoneNo(dto.getPhoneNo())
                .setEmailId(dto.getEmailId())
                .setPassword(dto.getPassword())
                .setRole(role)
                .setActive(true)
                .build();
    }
}