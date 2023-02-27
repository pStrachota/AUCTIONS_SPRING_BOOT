package pl.lodz.p.pstrachota.auctions_spring_boot_project.model.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.model.AbstractEntity;
import pl.lodz.p.pstrachota.auctions_spring_boot_project.service.converter.RoleEnumConverter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Builder
@AllArgsConstructor
@Table(name = "_user")
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "user-sequence-generator")
    @GenericGenerator(
            name = "user-sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @Parameter(name = "sequence_name", value = "user_sequence"),
                    @Parameter(name = "initial_value", value = "5"),
                    @Parameter(name = "increment_size", value = "1")
            }
    )
    private Long userId;

    @Email(regexp = "[^@]+@[^@]+\\.[^@.]+", message = "Email is not valid")
    @Column(unique = true)
    private String email;

    @JsonIgnore
    @NotBlank
    private String password;

    @NotBlank
    @Column(unique = true)
    private String username;

    @Convert(converter = RoleEnumConverter.class)
    private RoleEnum roleName;

    public User(String email, String password, String username) {
        this.email = email;
        this.password = password;
        this.username = username;
    }
}
