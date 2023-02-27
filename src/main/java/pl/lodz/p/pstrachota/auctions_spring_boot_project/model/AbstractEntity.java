package pl.lodz.p.pstrachota.auctions_spring_boot_project.model;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.UUID;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@EqualsAndHashCode(of = "uuid")
public class AbstractEntity implements Serializable {

    @NotNull
    private String uuid = UUID.randomUUID().toString();

    @Version
    private Long version;

}
