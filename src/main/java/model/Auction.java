package model;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String description;

    private BigDecimal currentPrice;

    private BigDecimal startingPrice;

    private AuctionType auctionType;

    private ItemStatus itemStatus;

    private ItemCategory itemCategory;

    private LocalDateTime auctionEndTime;

    private LocalDateTime auctionStartTime;

}
