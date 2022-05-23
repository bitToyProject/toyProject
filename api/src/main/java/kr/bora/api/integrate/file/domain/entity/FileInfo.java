package kr.bora.api.integrate.file.domain.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
@Builder
@Table(name = "local_file")
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long fileId;
    private long enrolledUserId;
    private FileCategory category;
    private String originalName;
    private String savedName;
    private Long size;
    private String extension;

    public static FileInfo getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {

        private static final FileInfo INSTANCE = new FileInfo();
    }
    public String ToString(){
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
