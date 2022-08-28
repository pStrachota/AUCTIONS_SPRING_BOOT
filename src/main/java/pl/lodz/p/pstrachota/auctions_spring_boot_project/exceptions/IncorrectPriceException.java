package pl.lodz.p.pstrachota.auctions_spring_boot_project.exceptions;

public class IncorrectPriceException extends RuntimeException {

    public IncorrectPriceException(String message) {
        super(message);
    }

}
