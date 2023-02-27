package pl.lodz.p.pstrachota.auctions_spring_boot_project.model.auction;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Item status")
public enum ItemStatus {
    NEW,
    USED
}
