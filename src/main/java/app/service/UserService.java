package app.service;

import app.domain.Role;
import app.dto.UserRequestDto;

public interface UserService {
    /**
     * Сохранение нового пользователя
     * @param userDto dto юзера
     */
    void add(UserRequestDto userDto);

    void changeRole(Long userId, Role role);
}
