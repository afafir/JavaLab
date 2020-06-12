package ru.javalab.pstgrsinheritance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.boot.archive.spi.AbstractArchiveDescriptor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity
@SuperBuilder
    @Table(name = "question")
public class Question extends AbstractQuestion{

}
