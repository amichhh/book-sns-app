package book.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import book.model.Record;
import book.service.RecordService;
import book.service.form.record.EditRecord;
import book.service.form.record.IdentifyRecord;
import book.service.form.record.RegisterRecord;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/record")
@AllArgsConstructor
public class RecordContrloller {
    private final RecordService service;

    /** 自身の読書記録を検索する */
    @GetMapping("")
    public List<Record> findRecord() {
        return service.findRecord();
    }

    /** 公開されている読書記録を検索する */
    @GetMapping("/public")
    public List<Record> findPublicRecord() {
        return service.findPublicRecord();
    }

    /** 自身がいいねした読書記録を検索する */
    @GetMapping("/like")
    public List<Record> findLikeRecord() {
        return service.findLikeRecord();
    }

    /** 読書記録を登録する */
    @PostMapping("")
    public Record registerRecord(@RequestBody @Valid RegisterRecord param) {
        return service.registerRecord(param);
    }

    /** 自身の読書記録を編集する */
    @PostMapping("/edit")
    public Record editRecord(@RequestBody @Valid EditRecord param) {
        return service.editRecord(param);
    }

    /** 自身の読書記録を削除する */
    @PostMapping("/delete")
    public void deleteRecord(@RequestBody @Valid IdentifyRecord param) {
        service.deleteRecord(param.getId());
    }

    /** 読書記録にいいねする */
    @PostMapping("/like")
    public void likeRecord(@RequestBody @Valid IdentifyRecord param) {
        service.likeRecord(param.getId());
    }

    /** 読書記録のいいねを解除する */
    @PostMapping("/unlike")
    public void unlikeRecord(@RequestBody @Valid IdentifyRecord param) {
        service.unlikeRecord(param.getId());
    }

    /** 自身の読書記録を公開する */
    @PostMapping("/public")
    public void publishRecord(@RequestBody @Valid IdentifyRecord param) {
        service.publishRecord(param.getId());
    }

    /** 自身の読書記録を非公開にする */
    @PostMapping("/private")
    public void secretRecord(@RequestBody @Valid IdentifyRecord param) {
        service.secretRecord(param.getId());
    }

}
