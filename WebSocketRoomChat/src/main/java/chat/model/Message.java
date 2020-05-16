package chat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "message", schema = "chat")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    @ManyToOne(cascade = CascadeType.MERGE)
    private User sender;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Chat chat;
    private LocalDateTime time;
}
