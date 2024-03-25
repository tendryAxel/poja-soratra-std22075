package hei.school.soratra.service.sary;

import hei.school.soratra.file.BucketComponent;
import hei.school.soratra.repository.SoratraRepository;
import hei.school.soratra.repository.model.Soratra;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SoratraService {
    BucketComponent bucketComponent;
    SoratraRepository soratraRepository;
    String main_path = "/soratra";

    public ResponseEntity<String> uploadSoratra(String id, String soratra){
        try {
            String bucketKey = main_path + id;
            String bucketTransformKey = main_path + id;

            File originalTempFile = File.createTempFile(id, "txt");
            File upperTempFile = createUpperFile(id, soratra);

            bucketComponent.upload(originalTempFile, bucketKey);
            bucketComponent.upload(upperTempFile, bucketTransformKey);

            soratraRepository.save(
                    new Soratra(
                            id,
                            bucketComponent.presign(bucketKey, Duration.ofHours(1)).toString(),
                            bucketComponent.presign(bucketTransformKey, Duration.ofHours(1)).toString()
                    )
            );

            originalTempFile.delete();
            upperTempFile.delete();

            return ResponseEntity.ok().body("");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(e.toString());
        }
    }

    public ResponseEntity<Soratra> getUrl(String id){
        return ResponseEntity.ok(soratraRepository.getReferenceById(id));
    }

    public File createUpperFile(String id, String content) throws IOException {
        File file = File.createTempFile(id , "upper");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(content);
        return file;
    }
}
