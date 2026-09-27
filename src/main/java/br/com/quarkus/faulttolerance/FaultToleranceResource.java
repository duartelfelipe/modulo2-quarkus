package br.com.quarkus.faulttolerance;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;

import java.time.temporal.ChronoUnit;

@Path("fault-tolerance")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class FaultToleranceResource {

    public static final String RETORNO_OK = "Retorno Ok!";
    public static final String RETORNO_FALLBACK = "Tente novamente mais tarde!";

    @GET
    @Path("ok")
    @Timeout(unit = ChronoUnit.SECONDS, value = 1)
    public String ok() {
        return RETORNO_OK;
    }

    @GET
    @Path("timeout")
    @Timeout(unit = ChronoUnit.SECONDS, value = 1)
    public String timeout() throws InterruptedException {
        Thread.sleep(2000L);
        return RETORNO_OK;
    }

    @GET
    @Path("fallback")
    @Timeout(unit = ChronoUnit.SECONDS, value = 1)
    @Fallback(fallbackMethod = "faultToleranceFallback")
    public String fallback() throws InterruptedException {
        Thread.sleep(2000L);
        return RETORNO_OK;
    }

    @GET
    @Path("circuit-breaker")
    @Timeout(unit = ChronoUnit.SECONDS, value = 1)
    @Fallback(fallbackMethod = "faultToleranceFallback")
    @CircuitBreaker(
            requestVolumeThreshold = 10,
            failureRatio = .4,
            delay = 20000L,
            successThreshold = 2
    )
    public String circuitBreaker() throws InterruptedException {
        return RETORNO_OK;
    }

    public String faultToleranceFallback() {
        return RETORNO_FALLBACK;
    }
}
