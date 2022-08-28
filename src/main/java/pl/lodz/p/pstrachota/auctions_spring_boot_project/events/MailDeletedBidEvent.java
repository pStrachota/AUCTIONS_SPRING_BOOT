package pl.lodz.p.pstrachota.auctions_spring_boot_project.events;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MailDeletedBidEvent {

    private List<String> emails;
    private long offerId;
    private BigDecimal bidPrice;

}
