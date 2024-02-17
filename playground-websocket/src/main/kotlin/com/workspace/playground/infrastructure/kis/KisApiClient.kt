package com.workspace.playground.infrastructure.kis

import com.workspace.playground.infrastructure.kis.request.GetApprovalKeyRequest
import com.workspace.playground.infrastructure.kis.response.ApprovalKeyResponse
import feign.Headers
import feign.RequestLine

@Headers("Content-Type: application/json")
interface KisApiClient {
    @RequestLine("POST /oauth2/Approval")
    fun getWebSocketApprovalKey(
        request: GetApprovalKeyRequest,
    ): ApprovalKeyResponse
}
