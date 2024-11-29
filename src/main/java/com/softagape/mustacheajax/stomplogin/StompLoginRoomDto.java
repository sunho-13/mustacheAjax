package com.softagape.mustacheajax.stomplogin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StompLoginRoomDto {
    private Long id;
    private Long writerId;
    private String writer;
    private String roomName;
    private Boolean deleteFlag;
    private Integer count;
}
