package br.com.voxlock.controller;

import br.com.voxlock.model.Integration;
import br.com.voxlock.repository.IntegrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/integrations")
public class IntegrationController {

    @Autowired
    private IntegrationRepository integrationRepository;

    @GetMapping
    public List<Integration> getAllIntegrations() {
        return integrationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Integration> getIntegrationById(@PathVariable Long id) {
        Optional<Integration> integration = integrationRepository.findById(id);
		return integration.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Integration createIntegration(@RequestBody Integration integration) {
        return integrationRepository.save(integration);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Integration> updateIntegration(@PathVariable Long id, @RequestBody Integration integrationDetails) {
        Optional<Integration> integration = integrationRepository.findById(id);
        if (integration.isPresent()) {
            Integration updatedIntegration = integration.get();
            updatedIntegration.setName(integrationDetails.getName());
            updatedIntegration.setIcon(integrationDetails.getIcon());
            updatedIntegration.setEndpointRegister(integrationDetails.getEndpointRegister());
            updatedIntegration.setEndpointBlock(integrationDetails.getEndpointBlock());
            return ResponseEntity.ok(integrationRepository.save(updatedIntegration));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIntegration(@PathVariable Long id) {
        Optional<Integration> integration = integrationRepository.findById(id);
        if (integration.isPresent()) {
            integrationRepository.delete(integration.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
