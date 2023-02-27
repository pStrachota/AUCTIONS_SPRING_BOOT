package pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions;

public class DuplicatedLoginException extends RuntimeException {

    public DuplicatedLoginException(String message) {
        super(message);
    }

}
