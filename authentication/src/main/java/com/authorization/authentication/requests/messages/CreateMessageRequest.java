package com.authorization.authentication.requests.messages;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateMessageRequest implements Serializable{
    
    /**
	 *
	 */
	private static final long serialVersionUID = 9030866369032009654L;

	@NotNull(message = "Messages must be provided")
    private String message;

    @NotNull(message = "Sender must be provided")
    private Long sender;

    @NotNull(message = "Receiver must be provided")
    private Long receiver;

    @NotNull(message = "ReadSender must be provided")
    private Boolean readSender;

    @NotNull(message = "ReadReceiver must be provided")
    private Boolean readReceiver;

    @NotNull(message = "ChatId must be provided")
    private Long chatId;

    public CreateMessageRequest(String message, Long sender, Long receiver, Boolean readSender, Boolean readReceiver, Long chatId) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.readSender = readSender;
        this.readReceiver = readReceiver;
        this.chatId = chatId;
    }
}