package pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces;

public interface EmailSenderService {
    void sendEmail(String email, String subject, String text);
}
