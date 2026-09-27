package br.com.quarkus.faulttolerance.healhcheck;

import br.com.quarkus.faulttolerance.FaultToleranceResource;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.management.ConstructorParameters;
import java.util.function.Predicate;

@Readiness
public class ReadinessHealth implements HealthCheck {

    private final FaultToleranceResource faultTolerance;

    public ReadinessHealth(FaultToleranceResource faultTolerance) {
        this.faultTolerance = faultTolerance;
    }

    @Override
    public HealthCheckResponse call() {

        try {

            boolean estaPronto =
                    faultTolerance.circuitBreaker().equals(FaultToleranceResource.RETORNO_OK);

            return estaPronto ?
                    HealthCheckResponse.up("Estou pronto!") :
                    downResponse();

        } catch (InterruptedException e) {
            return downResponse();
        }
    }

    private HealthCheckResponse downResponse() {
        return HealthCheckResponse.down("Não estou pronto!");
    }
}
