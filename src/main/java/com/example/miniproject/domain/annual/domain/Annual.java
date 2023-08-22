package com.example.miniproject.domain.annual.domain;

import com.example.miniproject.domain.annual.dto.AnnualRequestDto;
import com.example.miniproject.global.constant.Category;
import com.example.miniproject.global.constant.Reason;
import com.example.miniproject.global.constant.Status;
import com.example.miniproject.domain.member.domain.Member;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reg")
public class Annual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Category category;

    @Column(nullable = false, length = 40)
    private String title;
    @Column(nullable = false)
    private LocalDate startedAt;
    @Column(nullable = false)
    private LocalDate lastedAt;

    @Enumerated(EnumType.STRING)
    private Reason reason;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime modifiedAt;

    public void updateData(AnnualRequestDto.UpdateDto updateDto) {
        this.title = updateDto.getTitle();
        this.startedAt = LocalDate.parse(updateDto.getStartDate());
        this.lastedAt = LocalDate.parse(updateDto.getEndDate());
        this.reason = Reason.findByName(updateDto.getReason());
    }
}
