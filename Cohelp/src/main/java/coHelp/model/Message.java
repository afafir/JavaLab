package coHelp.model;

import coHelp.model.user.User;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "project_message", schema = "cohelp1")
@ToString(exclude = "chat")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String message;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name ="chat_id")
    private Chat chat;




}
