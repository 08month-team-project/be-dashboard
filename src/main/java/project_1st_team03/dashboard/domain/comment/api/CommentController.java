package project_1st_team03.dashboard.domain.comment.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import project_1st_team03.dashboard.domain.comment.application.CommentService;
import project_1st_team03.dashboard.domain.comment.dto.CommentsRequest;
import project_1st_team03.dashboard.domain.comment.dto.CommentsResponse;
import project_1st_team03.dashboard.global.security.MemberDetails;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
@Slf4j
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<Void> createComment(
            @AuthenticationPrincipal MemberDetails memberDetails,
            @Valid @RequestBody CommentsRequest request) {

        commentService.createComments(memberDetails,request);
        return ResponseEntity.ok().build();
    }


    /**
     * list로 반환 시 key가 0부터시작해서 자동으로 넣어졌다.
     * react 연동 시 key값을 comments로 받기 때문에 map으로 재반환해주었다.
     */
    @GetMapping("/comments")
    public ResponseEntity<Map<String,Object>> getAllComments() {

        List<CommentsResponse> comments = commentService.getAllComment();
        Map<String, Object> response = new HashMap<>();
        response.put("comments", comments);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/comments/{comment_id}")
    public ResponseEntity<Void> deleteCommentByPathId(
            @AuthenticationPrincipal MemberDetails memberDetails,
            @PathVariable("comment_id") Long id){

        commentService.deleteComment(memberDetails,id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/comments/{comment_id}")
    public ResponseEntity<Void> updateComment(
            @AuthenticationPrincipal MemberDetails memberDetails,
            @PathVariable("comment_id") Long id,
            @RequestBody CommentsRequest commentRequest){
    
        commentService.updateComment(memberDetails,id,commentRequest);
        return ResponseEntity.ok().build();
    }
}
