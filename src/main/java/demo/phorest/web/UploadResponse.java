package demo.phorest.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UploadResponse {
    public int recordsLoaded;
    public int totalRecordsNumber;
}
