package pl.lodz.p.pstrachota.auctions_spring_boot_project.model;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;

@MappedSuperclass
@EqualsAndHashCode(of = "uuid")
public class AbstractEntity implements Serializable {

    @NotNull
    private String uuid = UUID.randomUUID().toString();

    @Version
    private Long version;

}
