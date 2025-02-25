package br.com.voxlock.controller;

import br.com.voxlock.model.Block;
import br.com.voxlock.repository.BlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blocks")
public class BlockController {

    @Autowired
    private BlockRepository blockRepository;

    @GetMapping("/{id}")
    public ResponseEntity<Block> getBlockById(@PathVariable Long id) {
        Optional<Block> block = blockRepository.findById(id);
        return block.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Block createBlock(@RequestBody Block block) {
        return blockRepository.save(block);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Block> updateBlock(@PathVariable Long id, @RequestBody Block blockDetails) {
        Optional<Block> block = blockRepository.findById(id);
        if (block.isPresent()) {
            Block updatedBlock = block.get();
            updatedBlock.setUser(blockDetails.getUser());
            updatedBlock.setIntegration(blockDetails.getIntegration());
            updatedBlock.setDtRequest(blockDetails.getDtRequest());
            updatedBlock.setDtAccept(blockDetails.getDtAccept());
            updatedBlock.setStatus(blockDetails.getStatus());
            updatedBlock.setChannel(blockDetails.getChannel());
            return ResponseEntity.ok(blockRepository.save(updatedBlock));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlock(@PathVariable Long id) {
        Optional<Block> block = blockRepository.findById(id);
        if (block.isPresent()) {
            blockRepository.delete(block.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
