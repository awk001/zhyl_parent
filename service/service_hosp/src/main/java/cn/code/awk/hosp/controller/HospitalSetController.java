package cn.code.awk.hosp.controller;

import cn.code.awk.hosp.service.HospitalSetService;
import cn.code.awk.model.hosp.HospitalSet;
import cn.code.awk.result.R;
import cn.code.awk.utils.MD5;
import cn.code.awk.vo.hosp.HospitalQueryVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.poi.ss.formula.functions.T;
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
@Api(tags = "医院设置管理")
public class HospitalSetController {

    @Autowired
    @Qualifier("hospital")
    private HospitalSetService service;

    @GetMapping("list")
    @ApiOperation(value = "获取医院设置列表数据")
    public R<List<HospitalSet>> findAll() {
        return R.ok(service.list());
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "逻辑删除医院设置数据")
    public R<Boolean> removeById(
            @ApiParam(value = "id", required = true)
            @PathVariable("id") Long id) {
        return service.removeById(id) ? R.ok(true) : R.fail(false);
    }

    // 条件查询带分页
    @PostMapping("page/{current}/{limit}")
    @ApiOperation(value = "条件分页查询")
    public R<Page<HospitalSet>> findByPage(
            @RequestBody HospitalQueryVo queryVo,
            @PathVariable("current") Long current,
            @PathVariable("limit") Long limit) {
        Page<HospitalSet> page = new Page<>(current, limit);
        // 构建条件: 根据医院名称和编号进行查询
        QueryWrapper<HospitalSet> queryWrapper = new QueryWrapper<>();
        String hosname = queryVo.getHosname();
        String hoscode = queryVo.getHoscode();
        if (!StringUtils.isEmpty(hosname)) {
            queryWrapper.like("hosname", hosname);
        }
        if (!StringUtils.isEmpty(hoscode)) {
            queryWrapper.eq("hoscode", hoscode);
        }
        return R.ok(service.page(page, queryWrapper));
    }

    // 添加医院设置
    @PostMapping
    @ApiOperation(value = "添加医院设置信息")
    public R<Boolean> saveHospSet(@RequestBody HospitalSet hospitalSet) {
        // 设置状态  1：使用  0：未使用
        hospitalSet.setStatus(1);
        // 签名密钥
        hospitalSet.setSignKey(MD5.encrypt(
                String.valueOf(System.currentTimeMillis()).trim() + new Random().nextInt(1000)));
        return service.save(hospitalSet) ? R.ok(true) : R.fail(false);
    }

    @GetMapping("{id}")
    @ApiOperation(value = "根据ID获取医院设置对象数据")
    public R<HospitalSet> getHospSet(@PathVariable("id") long id) {
        // int i = 1/0;
        return R.ok(service.getById(id));
    }

    @PostMapping("restore")
    @ApiOperation(value = "更新医院设置数据")
    public R<Boolean> updateHospSet(@RequestBody HospitalSet hospitalSet) {
        return service.updateById(hospitalSet) ? R.ok(true) : R.fail(false);
    }

    // 批量删除
    @DeleteMapping("batch")
    @ApiOperation(value = "批量删除")
    public R<Boolean> batchDelete(@RequestBody List<Long> idList) {
        return service.removeByIds(idList) ? R.ok(true) : R.fail(false);
    }

    // 医院设置锁定和解锁
    @PostMapping("lock/{id}/{status}")
    @ApiOperation(value = "设置锁定或解锁")
    public R<T> statusLock(@PathVariable Long id, @PathVariable Integer status){
        service.getById(id).setStatus(status);
        return R.ok();
    }

    // 发送签名密钥
    @PostMapping("key/{id}")
    @ApiOperation(value = "发送签名密钥")
    public R<T> sendKey(@PathVariable Long id){
        HospitalSet hospitalSet = service.getById(id);
        String code = hospitalSet.getHoscode();
        String name = hospitalSet.getHosname();
        // 发送短信 TODO
        return R.ok();
    }



}