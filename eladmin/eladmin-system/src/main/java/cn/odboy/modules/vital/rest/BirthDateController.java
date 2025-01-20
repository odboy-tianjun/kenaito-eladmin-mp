package cn.odboy.modules.vital.rest;

import cn.odboy.annotation.Log;
import cn.odboy.model.PageArgs;
import cn.odboy.model.PageResult;
import cn.odboy.modules.vital.domain.BirthDate;
import cn.odboy.modules.vital.service.BirthDateService;
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
 * 生日管理 前端控制器
 * </p>
 *
 * @author codegen
 * @since 2025-01-20
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/vital/birthDate")
public class BirthDateController {
    private final BirthDateService birthDateService;

    @ApiOperation(value = "查询生日")
    @PostMapping(value = "searchBirthDates")
    @PreAuthorize("@el.check('vital:birthDate:list')")
    public ResponseEntity<Object> searchBirthDates(@Validated @RequestBody PageArgs<BirthDate.SearchBirthDates> args) {
        Page<BirthDate> page = new Page<>(args.getCurrent(), args.getSize());
        return new ResponseEntity<>(birthDateService.searchBirthDates(args.getArgs(), page), HttpStatus.OK);
    }

    @Log("新增生日")
    @ApiOperation(value = "新增生日")
    @PostMapping(value = "createBirthDate")
    @PreAuthorize("@el.check('vital:birthDate:create')")
    public ResponseEntity<Object> createBirthDate(@Validated @RequestBody BirthDate.CreateArgs args) {
        birthDateService.createBirthDate(args);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Log("修改生日通知状态")
    @ApiOperation(value = "修改生日通知状态")
    @PostMapping(value = "modifyBirthDateNotifyStatus")
    @PreAuthorize("@el.check('vital:birthDate:update')")
    public ResponseEntity<Object> modifyBirthDateNotifyStatus(@Validated @RequestBody BirthDate.ModifyBirthDateNotifyStatusArgs args) {
        birthDateService.modifyBirthDateNotifyStatus(args);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除生日")
    @ApiOperation(value = "删除生日")
    @PostMapping(value = "deleteBirthDates")
    @PreAuthorize("@el.check('vital:birthDate:delete')")
    public ResponseEntity<Object> deleteBirthDates(@Validated @RequestBody BirthDate.DeleteArgs args) {
        birthDateService.deleteBirthDates(args);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
