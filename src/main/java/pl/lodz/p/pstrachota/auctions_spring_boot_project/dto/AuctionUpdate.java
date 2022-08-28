package pl.lodz.p.pstrachota.auctions_spring_boot_project.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuctionUpdate {

    private String email;

    private String description;
}
