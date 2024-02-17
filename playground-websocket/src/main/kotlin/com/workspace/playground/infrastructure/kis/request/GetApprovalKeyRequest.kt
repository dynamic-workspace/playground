package com.workspace.playground.infrastructure.kis.request

import com.fasterxml.jackson.annotation.JsonProperty

data class GetApprovalKeyRequest(
    @JsonProperty("grant_type")
    val grantType: String = "client_credentials",
    @JsonProperty("appkey")
    val appKey: String,
    @JsonProperty("secretkey")
    val secretKey: String,
)
