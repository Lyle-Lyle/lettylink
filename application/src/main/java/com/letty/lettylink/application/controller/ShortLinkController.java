package com.letty.lettylink.application.controller;

import com.letty.lettylink.application.common.convention.result.Result;
import com.letty.lettylink.application.common.convention.result.Results;
import com.letty.lettylink.application.dto.req.ShortLinkCreateReqDTO;
import com.letty.lettylink.application.dto.resp.ShortLinkCreateRespDTO;
import com.letty.lettylink.application.service.ShortLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShortLinkController {

    // lombok的构造器注入
    private final ShortLinkService shortLinkService;
    @PostMapping("/api/short-link/v1/create")
    public Result<ShortLinkCreateRespDTO> createShortLink(@RequestBody ShortLinkCreateReqDTO requestParam) {
        return Results.success(shortLinkService.createShortLink(requestParam));
    }
}
