package cn.code.awk.hosp.controller;

import cn.code.awk.hosp.service.HospitalSetService;
import cn.code.awk.model.hosp.HospitalSet;
import cn.code.awk.result.Result;
import cn.code.awk.utils.MD5;
import cn.code.awk.vo.hosp.HospitalQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

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
    @ApiOperation(value = "获取医院设置列表数据")
    public Result<List<HospitalSet>> findAll() {
        return Result.ok(service.list());
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "逻辑删除医院设置数据")
    public Result<Boolean> removeById(
            @ApiParam(value = "id", required = true)
            @PathVariable("id") Long id) {
        return service.removeById(id) ? Result.ok(true) : Result.fail(false);
    }

    // 条件查询带分页
    @PostMapping("page/{current}/{limit}")
    @ApiOperation(value = "条件分页查询")
    public Result<Page<HospitalSet>> findByPage(
            @RequestBody HospitalQueryVo queryVo,
            @PathVariable("current") Long current,
            @PathVariable("limit") Long limit) {
        Page<HospitalSet> page = new Page<>(current, limit);
        // 构建条件: 根据医院名称和编号进行查询
        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(queryVo.getHosname())) {
            queryWrapper.like("hosname", queryVo.getHosname());
        }
        if (!StringUtils.isEmpty(queryVo.getHoscode())) {
            queryWrapper.eq("hoscode", queryVo.getHoscode());
        }
        return Result.ok(service.page(page, queryWrapper));
    }

    // 添加医院设置
    @PostMapping
    @ApiOperation(value = "添加医院设置信息")
    public Result<Boolean> saveHospSet(@RequestBody HospitalSet hospitalSet) {
        // 设置状态  1：使用  0：未使用
        hospitalSet.setStatus(1);
        // 签名密钥
        hospitalSet.setSignKey(MD5.encrypt(System.currentTimeMillis() + "" + new Random().nextInt(1000)));
        return service.save(hospitalSet) ? Result.ok(true) : Result.fail(false);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据ID获取医院设置对象数据")
    public Result<HospitalSet> getHospSet(@PathVariable("id") long id) {
        return Result.ok(service.getById(id));
    }

    @PostMapping("restore")
    @ApiOperation(value = "更新医院设置数据")
    public Result<Boolean> updateHospSet(@RequestBody HospitalSet hospitalSet) {
        return service.updateById(hospitalSet) ? Result.ok(true) : Result.fail(false);
    }

}