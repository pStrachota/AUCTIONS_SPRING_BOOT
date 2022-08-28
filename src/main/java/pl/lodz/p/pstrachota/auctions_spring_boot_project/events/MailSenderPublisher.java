package pl.lodz.p.pstrachota.auctions_spring_boot_project.events;

import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailSenderPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishNewBid(List<String> emails, long offerId, BigDecimal bidPrice) {
        applicationEventPublisher.publishEvent(new MailNewBidEvent(emails, offerId, bidPrice));
    }
}
