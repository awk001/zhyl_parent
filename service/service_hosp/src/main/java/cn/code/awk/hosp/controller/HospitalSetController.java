package cn.code.awk.hosp.controller;

import cn.code.awk.hosp.service.HospitalSetService;
import cn.code.awk.model.hosp.HospitalSet;
import cn.code.awk.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author anwenkang
 * @version 1.0.0
 * @ClassName HospitalSetController.java
 * @Description TODO
 * @createTime 2021年09月11日 16:48:00
 */
@RestController
@RequestMapping("/admin/hosp/settings")
@Api("医院设置管理")
public class HospitalSetController {

    @Autowired
    @Qualifier("hospital")
    private HospitalSetService service;

    @GetMapping("list")
    public Result<List<HospitalSet>> findAll() {
        return Result.ok(service.list());
    }

    @GetMapping("{id}")
    public Result removeById(
            @ApiParam(value = "id",required = true)
            @PathVariable("id") Long id){
        return Result.ok(service.removeById(id));
    }

}