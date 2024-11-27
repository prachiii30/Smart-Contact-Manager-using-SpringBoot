package com.Prachi.KontactHub.forms;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class UserForm {

    @NotBlank(message = "Required")
    private String name;

    @Email(message="Invalid email")
    @NotBlank(message = "Can't be Empty")
    private String email;

    @NotBlank(message = "Required")
    @Size(min=6,message = "Minimum 6 digits")
    private String password;
//    private String about;
    @Size(min=10,max=12,message = "Invalid Contact")
    private String phoneNumber;
}
