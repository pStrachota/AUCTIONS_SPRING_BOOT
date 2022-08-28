package pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public class ApiException {

    private final String exceptionMessage;
    private final HttpStatus httpStatus;
    private final List<String> errors;
    private final LocalDateTime timestamp;
}
