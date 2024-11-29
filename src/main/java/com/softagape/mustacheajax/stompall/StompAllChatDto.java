package com.softagape.mustacheajax.stompall;

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
public class StompAllChatDto extends StompAllMessageDto {
    private Long id;
    private String roomName;
}
