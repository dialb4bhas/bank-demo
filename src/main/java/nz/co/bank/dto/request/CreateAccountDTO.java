package nz.co.bank.dto.request;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;

public record CreateAccountDTO(@Nullable @Size(min = 5, max = 30, message = "Nick name cannot be less than 5 or greater than 30") String nickName) {

}
