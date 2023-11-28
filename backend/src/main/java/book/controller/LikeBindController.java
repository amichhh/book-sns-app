package book.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import book.model.LikeBind;
import book.service.LikeBindService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/likebind")
@AllArgsConstructor
public class LikeBindController {
    private final LikeBindService service;

    /** 自身のアカウントに紐付くいいねを検索する */
    @GetMapping("")
    public List<LikeBind> findMyAccountLikeBind() {
        return service.findMyAccountLikeBind();
    }
}
