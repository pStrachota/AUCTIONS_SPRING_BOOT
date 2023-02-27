package pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.AbstractEntity;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.user.User;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.validators.PriceConstraint;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bid extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "bid-sequence-generator")
    @GenericGenerator(
            name = "bid-sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "bid_sequence"),
                    @Parameter(name = "initial_value", value = "6"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long bidId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "auction_id")
    @JsonBackReference
    private Bidding bidding;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @PriceConstraint
    private BigDecimal bidPrice;

    @NotNull
    private LocalDateTime bidTime;

}
