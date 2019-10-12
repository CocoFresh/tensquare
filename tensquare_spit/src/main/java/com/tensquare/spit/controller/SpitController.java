package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Auther: xintao.feng
 * @Date: 2019/10/12 14:29
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findAll());
    }

    @RequestMapping(value = "/{spitId}", method = RequestMethod.GET)
    public Result findById(@PathVariable String spitId) {
        return new Result(true, StatusCode.OK, "查询成功", spitService.findById(spitId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Spit spit) {
        spitService.save(spit);
        return new Result(true, StatusCode.OK, "保存成功");
    }

    @RequestMapping(value = "/{spitId}", method = RequestMethod.PUT)
    public Result update(@RequestBody Spit spit, @PathVariable String spitId) {
        spit.set_id(spitId);
        spitService.updata(spit);
        return new Result(true, StatusCode.OK, "更新成功");
    }

    @RequestMapping(value = "/{spitId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String spitId) {
        spitService.deleteById(spitId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/common/{parentid}/{page}/{size}", method = RequestMethod.GET)
    public Result findByParentId(@PathVariable String parentid, @PathVariable int page, @PathVariable int size) {
        Page<Spit> pageData = spitService.findByParentId(parentid, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Spit>(pageData.getTotalElements(), pageData.getContent()));
    }

    @RequestMapping(value = "/thumbup/{spitId}", method = RequestMethod.PUT)
    public Result thumbup(@PathVariable String spitId) {
        String userId = "111";
        if (redisTemplate.opsForValue().get("thumb_" + userId) != null) {
            return new Result(false, StatusCode.ERROR, "不能重复点赞");
        }
        spitService.thumbup(spitId);
        redisTemplate.opsForValue().set("thumb_" + userId, 1);
        return new Result(true, StatusCode.OK, "点赞成功");

    }
}
