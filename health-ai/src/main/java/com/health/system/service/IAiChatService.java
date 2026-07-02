package com.health.system.service;

import com.health.system.domain.AiMessage;
import com.health.system.domain.AiConversation;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.util.List;

/**
 * AI 对话 Service 接口
 * 定义会话管理和流式对话的核心业务方法
 * 具体实现见 AiChatServiceImpl，底层模型由 AiModelConfig 工厂注入
 *
 * @author ruoyi
 */
public interface IAiChatService
{
    AiConversation createConversation(Long userId, String model);
    List<AiConversation> listConversations(Long userId);
    List<AiMessage> listMessages(Long conversationId, Long userId);
    int renameConversation(Long id, String title, Long userId);
    int deleteConversation(Long id, Long userId);
    void chat(Long conversationId, String userMessage, Long userId, SseEmitter emitter);
}
