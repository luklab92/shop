package com.spring.exercises.shop.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDto {
    private String name;
    private String surname;
    private String email;
    private Date addDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(name, userDto.name) &&
                Objects.equals(surname, userDto.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }
}
