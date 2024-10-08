package project_1st_team03.dashboard.domain.comment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import project_1st_team03.dashboard.domain.comment.domain.Reply;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReplyResponse {
    private Long id;
    private String content;
    private Long commentId;
    private Long memberId;
    private String memberEmail;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd_HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd_HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime modifiedAt;

    public ReplyResponse(Reply reply) {
        this.id = reply.getId();
        this.content = reply.getContent();
        this.commentId = reply.getComment().getId();
        this.memberId = reply.getMember().getId();
        this.memberEmail = reply.getMember().getEmail();
        this.createdAt = reply.getCreatedAt();
        this.modifiedAt = reply.getModifiedAt();

    }
}
