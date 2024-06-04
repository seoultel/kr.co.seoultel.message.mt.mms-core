package kr.co.seoultel.message.mt.mms.core.util;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.vavr.CheckedRunnable;
import kr.co.seoultel.message.mt.mms.core.common.exceptions.TpsOverExeption;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class CommonUtil {
    public static RateLimiter getRateLimiter(int tps, String name) {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitForPeriod(tps)
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .timeoutDuration(Duration.ofMillis(1000))
                .build();

        return RateLimiterRegistry.of(config).rateLimiter(name);
    }

    public static void isTpsOver(RateLimiter rateLimiter) throws TpsOverExeption {
        try {
            CheckedRunnable.of(rateLimiter::acquirePermission).run();
        } catch (Throwable e) {
            log.error("isTpsOver throw Exception");
            throw new TpsOverExeption();
        }
    }

    public static void doThreadSleep(long milliSec) {
        try {
            Thread.sleep(milliSec);
        } catch (InterruptedException ex) {
            log.error("[SYSTEM] Failed to thread sleep[{}]", milliSec);
        }
    }
}
