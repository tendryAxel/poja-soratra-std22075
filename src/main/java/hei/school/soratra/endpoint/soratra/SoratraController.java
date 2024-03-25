package hei.school.soratra.endpoint.soratra;

import hei.school.soratra.repository.model.Soratra;
import hei.school.soratra.service.sary.SoratraService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/soratra")
@AllArgsConstructor
public class SoratraController {
    SoratraService soratraService;
    @PutMapping("/{id}")
    public ResponseEntity<String> uploadText(
            @PathVariable("id") String id,
            @RequestBody String text
    ){
        return soratraService.uploadSoratra(id, text);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Soratra> getUrl(
            @PathVariable("id") String id
    ){
        return soratraService.getUrl(id);
    }
}
