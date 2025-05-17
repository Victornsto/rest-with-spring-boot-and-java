package com.victornsto.restwithspringbootandjava.controller.docs;

import com.victornsto.restwithspringbootandjava.dto.v1.security.AccountCredentialsDto;
import com.victornsto.restwithspringbootandjava.dto.v1.security.TokenDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface AuthControllerDocs {
    @Operation(summary = "Authenticates an user and returns a token", description = "Authenticates an user",
            tags = {"Auth"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = TokenDto.class))
                    ),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            })
    ResponseEntity<?> singIn(@RequestBody AccountCredentialsDto credentials);

    @Operation(summary = "Refresh token for Authenticated user and returns a token", description = "Refresh token for Authenticated user",
            tags = {"Auth"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = TokenDto.class))
                    ),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            })
    ResponseEntity<?> refreshToken(@PathVariable String username,
                                   @RequestHeader("Authorization") String refreshToken);

    @Operation(summary = "Create a new user", description = "Create a new user in the database",
            tags = {"Auth"},
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200",
                            content =
                            @Content(schema = @Schema(implementation = AccountCredentialsDto.class))
                    ),
                    @ApiResponse(description = "No content", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal server error", responseCode = "500", content = @Content)
            })
    AccountCredentialsDto create(@RequestBody AccountCredentialsDto user);
}
