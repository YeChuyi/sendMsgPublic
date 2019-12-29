package com.ycy.sendmessage.controller;

import com.ycy.sendmessage.service.sendMsgService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@ApiSort(1)
@Api(value = "发送验证码接口", tags = "发送验证码接口")
@RestController
@RequestMapping("/api/sendMsg")
public class sendMsgController {

    @Autowired
    private sendMsgService sendMsgServiceImpl;

    @ApiOperation(value = "获取手机验证码")
    @ApiImplicitParams({@ApiImplicitParam(name = "phone", value = "手机号码", paramType = "form")})
    @RequestMapping(value = "/toSend", method= RequestMethod.POST)
    public String sendMsg(@RequestParam("phone") String phone,HttpServletRequest request){
        String content ="";
        String isOk = sendMsgServiceImpl.send(phone,content);
        return isOk;
    }

    @ApiOperation(value = "验证手机验证码")
    @ApiImplicitParams({@ApiImplicitParam(name = "phone", value = "手机号码", paramType = "form"),
            @ApiImplicitParam(name = "code", value = "验证码", paramType = "form")}
    )
    @PostMapping(value = "/toVerify")
    public String verifyCode(@RequestParam("phone") String phone,
                             @RequestParam("code") String code, HttpServletRequest request){
       String msg = sendMsgServiceImpl.check(phone,code);
        return msg;
    }
}
