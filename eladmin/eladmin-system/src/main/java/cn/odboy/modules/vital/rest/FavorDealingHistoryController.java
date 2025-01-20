package cn.odboy.modules.vital.rest;

import cn.odboy.annotation.Log;
import cn.odboy.model.PageArgs;
import cn.odboy.modules.vital.domain.FavorDealingHistory;
import cn.odboy.modules.vital.service.FavorDealingHistoryService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 人情往来 前端控制器
 * </p>
 *
 * @author codegen
 * @since 2025-01-20
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vital/favorDealingHistory")
public class FavorDealingHistoryController {
    private final FavorDealingHistoryService favorDealingHistoryService;

    @ApiOperation(value = "查询人情往来记录")
    @PostMapping(value = "searchFavorDealingHistorys")
    @PreAuthorize("@el.check('vital:favorDealingHistory:list')")
    public ResponseEntity<Object> searchFavorDealingHistorys(@Validated @RequestBody PageArgs<FavorDealingHistory> args) {
        Page<FavorDealingHistory> page = new Page<>(args.getCurrent(), args.getSize());
        return new ResponseEntity<>(favorDealingHistoryService.searchFavorDealingHistorys(args.getArgs(), page), HttpStatus.OK);
    }

    @Log("新增人情往来记录")
    @ApiOperation(value = "新增人情往来记录")
    @PostMapping(value = "createFavorDealingHistory")
    @PreAuthorize("@el.check('vital:favorDealingHistory:create')")
    public ResponseEntity<Object> createFavorDealingHistory(@Validated @RequestBody FavorDealingHistory.CreateArgs args) {
        favorDealingHistoryService.createFavorDealingHistory(args);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改人情往来记录")
    @ApiOperation(value = "修改人情往来记录")
    @PostMapping(value = "updateFavorDealingHistory")
    @PreAuthorize("@el.check('vital:favorDealingHistory:update')")
    public ResponseEntity<Object> updateFavorDealingHistory(@Validated @RequestBody FavorDealingHistory.UpdateArgs args) {
        favorDealingHistoryService.updateFavorDealingHistory(args);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除人情往来记录")
    @ApiOperation(value = "删除人情往来记录")
    @PostMapping(value = "deleteFavorDealingHistorys")
    @PreAuthorize("@el.check('vital:favorDealingHistory:delete')")
    public ResponseEntity<Object> deleteFavorDealingHistorys(@Validated @RequestBody FavorDealingHistory.DeleteArgs args) {
        favorDealingHistoryService.deleteFavorDealingHistorys(args);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
