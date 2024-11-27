package com.Prachi.KontactHub.helpers;


import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Message {
    private String content;
    @Builder.Default
    private MessageType type=MessageType.blue;

}
