package coHelp.model.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Entity
@Data
@SuperBuilder
@Table(name = "project_avatar", schema = "cohelp1")
@NamedQuery(
        name = "findByOwner",
        query = "SELECT avatar FROM Avatar avatar WHERE avatar.owner = :user "
)
public class Avatar extends Document implements Serializable {






}
