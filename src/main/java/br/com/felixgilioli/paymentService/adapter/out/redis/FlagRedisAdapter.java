package br.com.felixgilioli.paymentService.adapter.out.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class FlagRedisAdapter {

    private static final Logger log = LoggerFactory.getLogger(FlagRedisAdapter.class);

    private final RedisTemplate<String, String> redisTemplate;
    private static final String ANTIFRAUD_FLAG_KEY = "antifraud-flag";

    public FlagRedisAdapter(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean getOrCreateAntifraudFlag() {
        log.info("Consultando flag de antifraud no Redis...");

        String value = redisTemplate.opsForValue().get(ANTIFRAUD_FLAG_KEY);

        if (value == null) {
            log.info("Flag não encontrada. Criando flag de antifraud com TTL de 10 minutos.");
            redisTemplate.opsForValue().set(ANTIFRAUD_FLAG_KEY, "true", 10, TimeUnit.MINUTES);
            log.info("Flag criada com valor: true");
            return true;
        }

        log.info("Flag existente encontrada no Redis: {}", value);
        return Boolean.parseBoolean(value);
    }
}
