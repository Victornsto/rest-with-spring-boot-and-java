package com.victornsto.restwithspringbootandjava.controller.docs;

import com.victornsto.restwithspringbootandjava.dto.v1.request.EmailRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface EmailControlerDocs {
    @Operation(summary = "Send an e-mail", description = "Sends an e-mail by provaiding details, subject and body",
            tags = {"e-Mail"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            })
    ResponseEntity<String> sendEmail(EmailRequestDto emailRequestDto);

    @Operation(summary = "Send an e-mail with a Attachment", description = "Sends an e-mail a Attachment by provaiding details, subject and body",
            tags = {"e-Mail"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = @Content),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            })
    ResponseEntity<String> sendEmailWithAttachment(String emailRequestJson, MultipartFile multipartFile);
}
