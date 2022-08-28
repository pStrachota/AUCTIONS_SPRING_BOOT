package pl.lodz.p.pstrachota.auctions_spring_boot_project.events;

import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.interfaces.EmailSenderService;

@Component
@AllArgsConstructor
public class MailSenderListener {

    private final EmailSenderService emailSenderServiceImpl;

    @EventListener
    public void handle(MailNewBidEvent event) {
        event.getEmails().forEach(email -> emailSenderServiceImpl
                .sendEmail(email, "New bid!", "New bid for offer id: " + event.getOfferId() +
                        "\nNow the price is: " + event.getBidPrice()));
    }

}


