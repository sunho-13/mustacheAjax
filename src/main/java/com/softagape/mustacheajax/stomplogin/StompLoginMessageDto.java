package com.softagape.mustacheajax.stomplogin;

import com.softagape.mustacheajax.stomp.StompMessageType;
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
public class StompLoginMessageDto {
    private StompMessageType msgType;
    private Long roomId;
    private Long writerId;
    private String writer;
    private String msgTime;
    private String message;
}
