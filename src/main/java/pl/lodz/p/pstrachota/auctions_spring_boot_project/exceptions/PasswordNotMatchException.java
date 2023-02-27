package pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions;

public class PasswordNotMatchException extends RuntimeException {

    public PasswordNotMatchException(String message) {
        super(message);
    }

}
