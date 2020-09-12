package com.qfetcher.qfetcher.controller;

import com.qfetcher.qfetcher.model.RequestModel;
import com.qfetcher.qfetcher.model.ResponseModel;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface FetcherController {
    //****************************************************************************
    @ApiOperation(value = "Q-Fetcher version 2.0")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = ResponseModel.class),
            @ApiResponse(code = 400, message = "Error! Bad request"),
            @ApiResponse(code = 404, message = "Error! Data not found"),
            @ApiResponse(code = 408, message = "Error! Request Timeout"),
            @ApiResponse(code = 409, message = "Error! Conflict")
    })
    @PostMapping("/api/v1/fetch")
    ResponseModel fetchQuestion(@RequestBody RequestModel requestModel);
    //****************************************************************************
}
