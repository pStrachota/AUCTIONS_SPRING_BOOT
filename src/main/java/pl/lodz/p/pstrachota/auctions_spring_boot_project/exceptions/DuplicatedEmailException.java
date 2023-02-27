package pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions;

public class DuplicatedEmailException extends RuntimeException {

    public DuplicatedEmailException(String message) {
        super(message);
    }

}
