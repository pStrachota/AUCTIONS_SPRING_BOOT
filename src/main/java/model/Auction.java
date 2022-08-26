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
import service.converter.AuctionTypeConverter;
import service.converter.ItemCategoryConverter;
import service.converter.ItemStatusConverter;

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

    @Convert(converter = AuctionTypeConverter.class)
    private AuctionType auctionType;

    @Convert(converter = ItemStatusConverter.class)
    private ItemStatus itemStatus;

    @Convert(converter = ItemCategoryConverter.class)
    private ItemCategory itemCategory;

    private LocalDateTime auctionEndTime;

    private LocalDateTime auctionStartTime;

}
