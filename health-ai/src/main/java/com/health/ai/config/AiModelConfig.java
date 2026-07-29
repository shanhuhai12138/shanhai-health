package com.health.ai.config;

import java.time.Duration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;

/**
 * AI 模型工厂
 *
 * 根据 ai.model.provider 自动创建对应的 StreamingChatLanguageModel Bean。
 * 切换模型只需修改 application.yml，无需改动任何业务代码。
 *
 * 支持的 provider：
 *   dashscope  —— 阿里云通义千问（OpenAI 兼容接口）
 *   openai     —— OpenAI 官方
 *   deepseek   —— DeepSeek（OpenAI 兼容接口）
 *   ollama     —— 本地 Ollama（完全免费）
 *
 * @author ruoyi
 */
@Configuration
public class AiModelConfig
{
    private static final Logger log = LoggerFactory.getLogger(AiModelConfig.class);

    // DashScope OpenAI 兼容接口地址
    private static final String DASHSCOPE_BASE_URL = "https://dashscope.aliyuncs.com/compatible-mode/v1";

    // DeepSeek OpenAI 兼容接口地址
    private static final String DEEPSEEK_BASE_URL = "https://api.deepseek.com/v1";

    @Autowired
    private AiModelProperties props;

    @Bean
    public StreamingChatLanguageModel streamingChatLanguageModel()
    {
        String provider = props.getProvider();
        String apiKey = props.getApiKey();
        if (apiKey == null || apiKey.isEmpty() || apiKey.startsWith("${AI"))
        {
            log.warn(">>> AI 流式模型未配置 API Key，AI 对话功能不可用");
            return null;
        }
        log.info(">>> 初始化 AI 流式模型，provider={}, model={}", provider, props.getModelName());
        return buildStreamingModel(provider);
    }

    @Bean
    @Lazy
    public ChatLanguageModel chatLanguageModel()
    {
        String provider = props.getProvider();
        String apiKey = props.getApiKey();
        if (apiKey == null || apiKey.isEmpty() || apiKey.startsWith("${AI"))
        {
            log.warn(">>> AI 同步模型未配置 API Key，将使用 Mock 数据");
            return null;
        }
        log.info(">>> 初始化 AI 同步模型，provider={}, model={}", provider, props.getModelName());
        return buildChatModel(provider);
    }

    private StreamingChatLanguageModel buildStreamingModel(String provider)
    {
        switch (provider.toLowerCase()) {

            // ---- 阿里云通义千问 ----
            case "dashscope":
                return OpenAiStreamingChatModel.builder()
                        .baseUrl(DASHSCOPE_BASE_URL)
                        .apiKey(props.getApiKey())
                        .modelName(props.getModelName())
                        .maxTokens(props.getMaxTokens())
                        .temperature(props.getTemperature())
                        .timeout(Duration.ofSeconds(120))
                        .build();


            // ---- DeepSeek ----
            case "deepseek":
                return OpenAiStreamingChatModel.builder()
                        .baseUrl(DEEPSEEK_BASE_URL)
                        .apiKey(props.getApiKey())
                        .modelName(props.getModelName())
                        .maxTokens(props.getMaxTokens())
                        .temperature(props.getTemperature())
                        .timeout(Duration.ofSeconds(120))
                        .build();

            // ---- Ollama（本地）----
            case "ollama":
                return OpenAiStreamingChatModel.builder()
                        .baseUrl("http://localhost:11434/v1")
                        .apiKey(props.getApiKey())
                        .modelName(props.getModelName())
                        .maxTokens(props.getMaxTokens())
                        .temperature(props.getTemperature())
                        .timeout(Duration.ofSeconds(180))
                        .build();

            default:
                throw new IllegalArgumentException(
                        "不支持的 AI provider: " + provider
                        + "，可选值: dashscope / deepseek / ollama");
        }
    }

    private ChatLanguageModel buildChatModel(String provider)
    {
        switch (provider.toLowerCase()) {
            case "dashscope":
                return OpenAiChatModel.builder()
                        .baseUrl(DASHSCOPE_BASE_URL)
                        .apiKey(props.getApiKey())
                        .modelName(props.getModelName())
                        .maxTokens(props.getMaxTokens())
                        .temperature(props.getTemperature())
                        .timeout(Duration.ofSeconds(120))
                        .build();
            case "deepseek":
                return OpenAiChatModel.builder()
                        .baseUrl(DEEPSEEK_BASE_URL)
                        .apiKey(props.getApiKey())
                        .modelName(props.getModelName())
                        .maxTokens(props.getMaxTokens())
                        .temperature(props.getTemperature())
                        .timeout(Duration.ofSeconds(120))
                        .build();
            case "ollama":
                return OpenAiChatModel.builder()
                        .baseUrl("http://localhost:11434/v1")
                        .apiKey(props.getApiKey())
                        .modelName(props.getModelName())
                        .maxTokens(props.getMaxTokens())
                        .temperature(props.getTemperature())
                        .timeout(Duration.ofSeconds(180))
                        .build();
            default:
                throw new IllegalArgumentException(
                        "不支持的 AI provider: " + provider
                        + "，可选值: dashscope / deepseek / ollama");
        }
    }
}
