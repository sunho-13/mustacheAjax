package com.softagape.mustacheajax.stomplogin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StompLoginChatDto extends StompLoginMessageDto {
    private Long id;
    private String roomName;
}
