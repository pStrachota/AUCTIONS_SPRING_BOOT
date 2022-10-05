package pl.lodz.p.pstrachota.auctions_spring_boot_project.model;


import io.swagger.v3.oas.annotations.media.Schema;
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
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.converter.AuctionTypeConverter;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.converter.ItemCategoryConverter;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.converter.ItemStatusConverter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Schema
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String email;

    private String description;

    private BigDecimal currentPrice;

    private BigDecimal startingPrice;

    @Schema(description = "Auction type")
    @Convert(converter = AuctionTypeConverter.class)
    private AuctionType auctionType;

    @Schema(description = "Item status")
    @Convert(converter = ItemStatusConverter.class)
    private ItemStatus itemStatus;

    @Schema(description = "Item category")
    @Convert(converter = ItemCategoryConverter.class)
    private ItemCategory itemCategory;

    private LocalDateTime auctionEndTime;

    private LocalDateTime auctionStartTime;

}
