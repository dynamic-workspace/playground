package com.workspace.playground.infrastructure.kis.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ApprovalKeyResponse(
    @JsonProperty("approval_key")
     val approvalKey: String,
)