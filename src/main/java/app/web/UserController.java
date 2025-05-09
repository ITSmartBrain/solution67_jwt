package app.web;

import app.domain.Role;
import app.dto.UserRequestDto;
import app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    /**
     * Регистрация пользователя
     * @param userDto dto нового пользователя
     */
    @PostMapping("/add")
    public void addUser(@RequestBody UserRequestDto userDto){
        //пользователь может быть зарегистрирован только с ролью USER
        if(!userDto.getRole().toUpperCase().equals(Role.USER.name())){
            throw new RuntimeException("Invalid role");
        }
        userService.add(userDto);
    }

    /**
     * Смена роли
     * @param id id юзера, у которого меняется роль
     * @param role новая роль
     */
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{userId}/role/{role}")
    public void changeRole(@PathVariable Long id, @PathVariable Role role){
        userService.changeRole(id, role);
    }
}
