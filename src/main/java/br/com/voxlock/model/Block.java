package br.com.voxlock.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Block {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "integration_id", nullable = false)
    private Integration integration;

    private LocalDateTime dtRequest;
    private LocalDateTime dtAccept;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Channel channel;

    public enum Status {
        WAITING,
        NOT_ACCEPTED,
        ACCEPTED
    }

    public enum Channel {
        PHONE,
        WEB
    }
}
